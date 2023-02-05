package io.github.slimefunguguproject.bump.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import org.bukkit.plugin.Plugin;

import io.github.slimefunguguproject.bump.implementation.Bump;

import lombok.experimental.UtilityClass;

/**
 * These utility methods are responsible to set up the wiki pages for Simplified Chinese Slimefun.
 *
 * @author ybw0014
 */
@UtilityClass
public final class WikiUtils {
    public static void setupJson() {
        try {
            Class<?> clazz = Class.forName("net.guizhanss.slimefun4.utils.WikiUtils");
            clazz.getMethod("setupJson", Plugin.class).invoke(null, Bump.getInstance());
        } catch (ClassNotFoundException | NoSuchMethodException | NullPointerException
                 | IllegalAccessException | InvocationTargetException e) {
            Bump.log(Level.WARNING, "Cannot load wiki pages. You can ignore this message if you are using Slimefun DEV/RC version.");
        }
    }
}
