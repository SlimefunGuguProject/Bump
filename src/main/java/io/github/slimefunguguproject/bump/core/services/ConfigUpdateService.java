package io.github.slimefunguguproject.bump.core.services;

import javax.annotation.Nonnull;

import org.bukkit.configuration.Configuration;

import net.guizhanss.guizhanlib.slimefun.addon.AddonConfig;

/**
 * This service will update config when there is a new version.
 *
 * @author ybw0014
 */
public final class ConfigUpdateService {
    private static final int CURRENT_VERSION = 4;

    public ConfigUpdateService(@Nonnull AddonConfig config) {
        if (config.getInt("version", 1) < CURRENT_VERSION) {
            Configuration defaultConfig = config.getDefaults();
            for (String key : defaultConfig.getKeys(true)) {
                if (!config.contains(key)) {
                    config.set(key, defaultConfig.get(key));
                }
            }
            config.set("version", CURRENT_VERSION);
            config.save();
        }
    }
}
