package io.github.slimefunguguproject.bump.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.slimefunguguproject.bump.api.appraise.AppraiseType;

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
    @Getter
    private final Map<Byte, Byte> starThresholds = new LinkedHashMap<>();

    @ParametersAreNonnullByDefault
    public BumpRegistry(JavaPlugin plugin, AddonConfig config) {
        this.config = config;
    }
}
