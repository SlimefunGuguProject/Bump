package org.slimefunguguproject.bump.implementation;

import net.guizhanss.guizhanlib.bstats.bukkit.Metrics;
import net.guizhanss.guizhanlib.bstats.charts.SimplePie;
import net.guizhanss.guizhanlib.slimefun.addon.AbstractAddon;
import org.slimefunguguproject.bump.core.services.LocalizationService;
import org.slimefunguguproject.bump.implementation.appraise.AppraiseManager;
import org.slimefunguguproject.bump.implementation.listeners.BowShootListener;
import org.slimefunguguproject.bump.implementation.setup.ItemsSetup;
import org.slimefunguguproject.bump.implementation.setup.ResearchSetup;

public final class Bump extends AbstractAddon {

    // localization
    private LocalizationService localization;
    private String lang;

    public Bump() {
        super("SlimefunGuguProject", "Bump", "main", "options.auto-update", "options.lang");
        enableMetrics(14870);
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

        // localization
        lang = getConfig().getString("options.lang", "en-US");
        localization = new LocalizationService(this);
        localization.addLanguage(lang);
        if (!lang.equals("en-US")) {
            localization.addLanguage("en-US");
        }
        sendConsole("&eLoaded language {0}", lang);

        // items setup
        ItemsSetup.setup();

        // researches setup
        if (getConfig().getBoolean("options.enable-research")) {
            ResearchSetup.setup();
        }

        // appraise setup
        new AppraiseManager();

        // listeners
        getServer().getPluginManager().registerEvents(new BowShootListener(), this);
    }

    @Override
    public void disable() {
    }

    @Override
    public void setupMetrics(Metrics metrics) {
        metrics.addCustomChart(new SimplePie("Language", () -> lang));
    }

    public static LocalizationService getLocalization() {
        return ((Bump) getInstance()).localization;
    }
}
