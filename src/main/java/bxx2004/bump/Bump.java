package bxx2004.bump;

import bxx2004.bump.event.FoodEvent;
import bxx2004.bump.event.MachineEvent;
import bxx2004.bump.event.ToolsEvent;
import bxx2004.bump.event.WeaponEvent;
import bxx2004.bump.util.Utils;
import net.guizhanss.guizhanlib.slimefun.addon.AbstractAddon;
import org.bukkit.Bukkit;

import java.text.MessageFormat;
import java.util.logging.Level;

public final class Bump extends AbstractAddon {

    private BumpLocalization localization;

    public Bump() {
        super("SlimefunGuguProject", "Bump", "main", "options.auto-update");
    }

    @Override
    public void enable() {

        Utils.log(Level.INFO, "&6&l  _____");
        Utils.log(Level.INFO, "&6&l /\\   _`\\             ");
        Utils.log(Level.INFO, "&6&l \\ \\ \\L\\ \\  __  __    ___ ___   _____   ");
        Utils.log(Level.INFO, "&6&l  \\ \\  _ <'/\\ \\/\\ \\ /' __` __`\\/\\ '__`\\ ");
        Utils.log(Level.INFO, "&6&l   \\ \\ \\L\\ \\ \\ \\_\\ \\/\\ \\/\\ \\/\\ \\ \\ \\L\\ \\");
        Utils.log(Level.INFO, "&6&l    \\ \\____/\\ \\____/\\ \\_\\ \\_\\ \\_\\ \\ ,__/");
        Utils.log(Level.INFO, "&6&l     \\/___/  \\/___/  \\/_/\\/_/\\/_/\\ \\ \\/ ");
        Utils.log(Level.INFO, "&6&l                                  \\ \\_\\ ");
        Utils.log(Level.INFO, "&6&l                                   \\/_/ ");
        Utils.log(Level.INFO, "&a&l     - Slimefun4 RC-27+ - Powered By bxx2004 | Website https://github.com/SlimefunGuguProject/Slimefun-Bump");

        // setup
        String lang = getConfig().getString("lang", "zh-CN");
        localization = new BumpLocalization(this);
        localization.addLanguage(lang);
        getLogger().log(Level.INFO, MessageFormat.format("&eLoaded language {0}", lang));

        // register events
        Bukkit.getPluginManager().registerEvents(new WeaponEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MachineEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ToolsEvent(), this);
        Bukkit.getPluginManager().registerEvents(new FoodEvent(), this);
    }

    @Override
    public void disable() {
    }

    public static BumpLocalization getLocalization() {
        return ((Bump) getInstance()).localization;
    }
}
