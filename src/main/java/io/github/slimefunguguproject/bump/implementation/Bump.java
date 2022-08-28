package io.github.slimefunguguproject.bump.implementation;

import java.util.logging.Level;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;

import io.github.slimefunguguproject.bump.core.BumpRegistry;
import io.github.slimefunguguproject.bump.core.services.ConfigUpdateService;
import io.github.slimefunguguproject.bump.core.services.LocalizationService;
import io.github.slimefunguguproject.bump.core.services.sounds.SoundService;
import io.github.slimefunguguproject.bump.implementation.setup.AppraiseSetup;
import io.github.slimefunguguproject.bump.implementation.setup.ItemGroupsSetup;
import io.github.slimefunguguproject.bump.implementation.setup.ItemsSetup;
import io.github.slimefunguguproject.bump.implementation.setup.ListenerSetup;
import io.github.slimefunguguproject.bump.implementation.setup.ResearchSetup;
import io.github.slimefunguguproject.bump.implementation.tasks.WeaponProjectileTask;
import io.github.slimefunguguproject.bump.utils.WikiUtils;
import io.github.slimefunguguproject.bump.utils.tags.BumpTag;

import net.guizhanss.guizhanlib.slimefun.addon.AbstractAddon;
import net.guizhanss.guizhanlib.slimefun.addon.AddonConfig;

import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;

/**
 * Main class for {@link Bump}.
 *
 * @author ybw0014
 */
public final class Bump extends AbstractAddon {

    private static final String DEFAULT_LANG = "en-US";

    // localization
    private LocalizationService localization;

    // registry
    private BumpRegistry registry;

    // services
    private SoundService soundService;

    public Bump() {
        super("SlimefunGuguProject", "Bump", "main", "options.auto-update");
    }

    @Nonnull
    public static LocalizationService getLocalization() {
        return ((Bump) getInstance()).localization;
    }

    @Nonnull
    public static BumpRegistry getRegistry() {
        return ((Bump) getInstance()).registry;
    }

    @Nonnull
    public static SoundService getSoundService() {
        return ((Bump) getInstance()).soundService;
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
        sendConsole("&a&l  Powered By bxx2004, SlimefunGuguProject");
        sendConsole("&a&l  GitHub: https://github.com/SlimefunGuguProject/Bump");
        sendConsole("&a&l  Issues: https://github.com/SlimefunGuguProject/Bump/issues");

        // config
        AddonConfig config = getAddonConfig();
        new ConfigUpdateService(config);

        // registry
        registry = new BumpRegistry(this, config);

        // localization
        log(Level.INFO, "Loading language...");
        String lang = getRegistry().getConfig().getString("options.lang", DEFAULT_LANG);
        localization = new LocalizationService(this);
        localization.addLanguage(lang);
        getRegistry().setLanguage(lang);
        if (!lang.equals(DEFAULT_LANG)) {
            localization.addLanguage(DEFAULT_LANG);
        }
        log(Level.INFO, "Loaded language {0}", lang);

        // tags
        BumpTag.reloadAll();

        // sound service
        soundService = new SoundService(new AddonConfig("sounds.yml"));
        soundService.load(true);

        // appraise setup
        AppraiseSetup.setupTypes();
        AppraiseSetup.setupStars();

        // item groups setup
        ItemGroupsSetup.setup(this);

        // items setup
        ItemsSetup.setup(this);

        // researches setup
        boolean enableResearch = getRegistry().getConfig().getBoolean("options.enable-research", true);
        if (enableResearch) {
            ResearchSetup.setup();
        }

        // wiki setup
        WikiUtils.setupJson();

        // listeners
        ListenerSetup.setup(this);

        // tasks
        WeaponProjectileTask.start();

        // Metrics setup
        final Metrics metrics = new Metrics(this, 14870);
        metrics.addCustomChart(new SimplePie("server_language", () -> lang));
        metrics.addCustomChart(new SimplePie("enable_research", () -> enableResearch ? "enabled" : "disabled"));
    }

    @Override
    public void disable() {
        Bukkit.getScheduler().cancelTasks(this);
    }

    @Nonnull
    public String getWikiURL() {
        return "https://slimefun-addons-wiki.guizhanss.cn/bump/{0}";
    }
}
