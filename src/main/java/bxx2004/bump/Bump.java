package bxx2004.bump;

import bxx2004.bump.listeners.BowShootListener;
import bxx2004.bump.setup.ItemsSetup;
import net.guizhanss.guizhanlib.slimefun.addon.AbstractAddon;

import java.util.logging.Level;

public final class Bump extends AbstractAddon {

    private BumpLocalization localization;

    public Bump() {
        super("SlimefunGuguProject", "Bump", "main", "options.auto-update");
        setupMetrics(14870);
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
        sendConsole("&a&l     - Slimefun4 RC-27+ - Powered By bxx2004");
        sendConsole("&a&l     - Website https://github.com/SlimefunGuguProject/Slimefun-Bump");

        // localization
        String lang = getConfig().getString("lang", "zh-CN");
        localization = new BumpLocalization(this);
        localization.addLanguage(lang);
        sendConsole("&eLoaded language {0}", lang);

        ItemsSetup.setup();

        getServer().getPluginManager().registerEvents(new BowShootListener(), this);
    }

    @Override
    public void disable() {
    }

    public static BumpLocalization getLocalization() {
        return ((Bump) getInstance()).localization;
    }
}
