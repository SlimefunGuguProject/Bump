package io.github.slimefunguguproject.bump.core.services;

import javax.annotation.Nonnull;

import io.github.slimefunguguproject.bump.utils.ConfigUtils;

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
            ConfigUtils.addMissingOptions(config);
            config.set("version", CURRENT_VERSION);
            config.save();
        }
    }
}
