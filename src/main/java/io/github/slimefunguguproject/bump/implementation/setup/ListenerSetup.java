package io.github.slimefunguguproject.bump.implementation.setup;

import javax.annotation.Nonnull;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.slimefunguguproject.bump.implementation.listeners.BowShootListener;
import io.github.slimefunguguproject.bump.implementation.listeners.DragonBreathListener;
import io.github.slimefunguguproject.bump.implementation.listeners.SkySwordListener;

import lombok.experimental.UtilityClass;

/**
 * This class setup all Listeners.
 *
 * @author ybw0014
 */
@UtilityClass
public final class ListenerSetup {
    public static void setup(@Nonnull JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new BowShootListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new DragonBreathListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new SkySwordListener(), plugin);
    }
}
