package io.github.slimefunguguproject.bump.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.slimefunguguproject.bump.api.appraise.AppraiseType;
import io.github.slimefunguguproject.bump.api.appraise.AppraiseTypes;

import net.guizhanss.guizhanlib.slimefun.addon.AddonConfig;

import lombok.Getter;

/**
 * This class holds {@link Map Maps} and {@link List Lists} related to Bump.
 *
 * @author ybw0014
 */
public final class BumpRegistry {
    // config
    @Getter
    private final AddonConfig config;

    // appraise
    @Getter
    private final Map<String, AppraiseType> appraiseTypeIds = new HashMap<>();
    @Getter
    private final List<AppraiseType> appraiseTypes = new ArrayList<>();

    @ParametersAreNonnullByDefault
    public BumpRegistry(JavaPlugin plugin, AddonConfig config) {
        this.config = config;
    }
}
