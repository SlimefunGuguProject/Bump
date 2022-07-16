package io.github.slimefunguguproject.bump.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.slimefunguguproject.bump.implementation.appraise.AppraiseType;

import net.guizhanss.guizhanlib.slimefun.addon.AddonConfig;

/**
 * This class holds {@link Map Maps} and {@link List Lists} related to Bump.
 *
 * @author ybw0014
 */
public final class BumpRegistry {
    // config
    private final AddonConfig config;

    // appraise
    private final Map<String, AppraiseType> appraiseTypeIds = new HashMap<>();
    private final List<AppraiseType> appraiseTypes = new ArrayList<>();

    @ParametersAreNonnullByDefault
    public BumpRegistry(JavaPlugin plugin, AddonConfig config) {
        this.config = config;
    }

    @Nonnull
    public AddonConfig getConfig() {
        return config;
    }

    @Nonnull
    public Map<String, AppraiseType> getAppraiseTypeIds() {
        return appraiseTypeIds;
    }

    @Nonnull
    public List<AppraiseType> getAllAppraiseTypes() {
        return appraiseTypes;
    }
}
