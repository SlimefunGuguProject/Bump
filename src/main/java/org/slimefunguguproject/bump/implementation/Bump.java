package org.slimefunguguproject.bump.implementation;

import javax.annotation.Nonnull;

import net.guizhanss.guizhanlib.bstats.charts.SimplePie;
import net.guizhanss.guizhanlib.slimefun.addon.AbstractAddon;
import net.guizhanss.guizhanlib.slimefun.addon.AddonConfig;

import org.slimefunguguproject.bump.core.BumpRegistry;
import org.slimefunguguproject.bump.core.services.ConfigUpdateService;
import org.slimefunguguproject.bump.core.services.LocalizationService;
import org.slimefunguguproject.bump.implementation.appraise.AppraiseManager;
import org.slimefunguguproject.bump.implementation.listeners.BowShootListener;
import org.slimefunguguproject.bump.implementation.listeners.SkySwordListener;
import org.slimefunguguproject.bump.implementation.setup.ItemsSetup;
import org.slimefunguguproject.bump.implementation.setup.ResearchSetup;
import org.slimefunguguproject.bump.implementation.tasks.WeaponProjectileTask;

/**
 * Main class for {@link Bump}.
 *
 * @author ybw0014
 */
public final class Bump extends AbstractAddon {

    private static final String DEFAULT_LANG = "en-US";

    // localization
    private LocalizationService localization;

    // appraise
    private AppraiseManager appraiseManager;

    private BumpRegistry registry;

    public Bump() {
        super("SlimefunGuguProject", "Bump", "main", "options.auto-update", "options.lang");
        enableMetrics(14870);
    }

    @Nonnull
    private static Bump inst() {
        return getInstance();
    }

    @Nonnull
    public static LocalizationService getLocalization() {
        return inst().localization;
    }

    @Nonnull
    public static AppraiseManager getAppraiseManager() {
        return inst().appraiseManager;
    }

    @Nonnull
    public static BumpRegistry getRegistry() {
        return inst().registry;
    }

    @Override
    public void enable() {
        sendConsole("&6&l  _____");
        sendConsole("&6&l /\\   _`\\             ");
        sendConsole("&6&l \\ \\ \\L\\ \\  __  __    ___ ___   _____   ");
        sendConsole("&6&l  \\ \\  _ <'/\\ \\/\\ \\ /' __` __`\\/\\ '__`\\ ");
        sendConsole("&6&l   \\ \\ \\L\\ \\ \\ \\_\\ \\/\\ \\/\\ \\/\\ \\ \\ \\L\\ \\");
        sendConsole("&6&l    \\ \\____/\\ \\____/\\ \\_\\ \\_\\ \\_\\ \\ ,__/");
        sendConsole("&6&l     \\/___/  \\/___/  \\/_/\\/_/\\/_/\\ \\ \\/ ");
        sendConsole("&6&l                                  \\ \\_\\ ");
        sendConsole("&6&l                                   \\/_/ ");
        sendConsole("&a&l  Bump 2 for Slimefun4 RC-30+");
        sendConsole("&a&l  Powered By bxx2004");
        sendConsole("&a&l  GitHub: https://github.com/SlimefunGuguProject/Bump");
        sendConsole("&a&l  Issues: https://github.com/SlimefunGuguProject/Bump/issues");

        // config
        AddonConfig config = getAddonConfig();
        new ConfigUpdateService(config);

        // registry
        registry = new BumpRegistry(this, config);

        // localization
        String lang = getRegistry().getConfig().getString("options.lang", DEFAULT_LANG);
        localization = new LocalizationService(this);
        localization.addLanguage(lang);
        if (!lang.equals(DEFAULT_LANG)) {
            localization.addLanguage(DEFAULT_LANG);
        }
        sendConsole("&eLoaded language {0}", lang);

        // items setup
        ItemsSetup.setup();

        // researches setup
        boolean enableResearch = getRegistry().getConfig().getBoolean("options.enable-research", true);
        if (enableResearch) {
            ResearchSetup.setup();
        }

        // appraise setup
        appraiseManager = new AppraiseManager();

        // listeners
        getServer().getPluginManager().registerEvents(new BowShootListener(), this);
        getServer().getPluginManager().registerEvents(new SkySwordListener(), this);

        // tasks
        WeaponProjectileTask.start();

        // Metrics setup
        getMetrics().addCustomChart(new SimplePie("server_language", () -> lang));
        getMetrics().addCustomChart(new SimplePie("enable_research", () -> enableResearch ? "enabled" : "disabled"));
    }

    @Override
    public void disable() {
        // does nothing yet.
    }
}
