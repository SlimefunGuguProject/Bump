package org.slimefunguguproject.bump.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.slimefunguguproject.bump.implementation.appraise.AppraiseType;

/**
 * This class holds {@link Map Maps} and {@link List Lists} related to Bump.
 *
 * @author ybw0014
 */
public final class BumpRegistry {
    private final Map<String, AppraiseType> appraiseTypeIds = new HashMap<>();
    private final List<AppraiseType> appraiseTypes = new ArrayList<>();

    @Nonnull
    public Map<String, AppraiseType> getAppraiseTypeIds() {
        return appraiseTypeIds;
    }

    @Nonnull
    public List<AppraiseType> getAllAppraiseTypes() {
        return appraiseTypes;
    }
}
