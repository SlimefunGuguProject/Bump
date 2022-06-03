package org.slimefunguguproject.bump.api.appraise;

import org.apache.commons.lang.Validate;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * An {@link AppraiseResult} represents an appraisal result.
 *
 * @author ybw0014
 */
public final class AppraiseResult {
    private final Map<AppraiseAttribute, Double> result = new LinkedHashMap<>();
    private double overallPercentage = 0;

    /**
     * This method adds an appraised attribute to result
     *
     * @param attribute The {@link AppraiseAttribute}
     * @param value The value of the attribute
     * @param percentage The weight of the attribute
     *
     * @return The {@link AppraiseResult}
     */
    @Nonnull
    public AppraiseResult add(@Nonnull AppraiseAttribute attribute, double value, double percentage) {
        if (result.containsKey(attribute)) {
            return this;
        }

        result.put(attribute, value);
        overallPercentage += attribute.getPercent(value) * percentage / 100.0D;
        return this;
    }

    /**
     * This method returns the stars of appraisal result
     * calculated by overall percentage.
     *
     * @return The number of stars of the result
     */
    public int getStarts() {
        if (overallPercentage >= 100) {
            return 20;
        } else if (overallPercentage >= 99) {
            return 10;
        } else if (overallPercentage >= 97) {
            return 9;
        } else if (overallPercentage >= 95) {
            return 8;
        } else if (overallPercentage >= 92) {
            return 7;
        } else if (overallPercentage >= 88) {
            return 6;
        } else if (overallPercentage >= 84) {
            return 5;
        } else if (overallPercentage >= 78) {
            return 4;
        } else if (overallPercentage >= 66) {
            return 3;
        } else if (overallPercentage >= 40) {
            return 2;
        } else if (overallPercentage >= 10) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * This method applies the appraisal result to given {@link ItemMeta}
     *
     * @param meta The {@link ItemMeta}
     */
    public void apply(@Nonnull ItemMeta meta) {
        Validate.notNull(meta, "ItemMeta cannot be null");

        for (Map.Entry<AppraiseAttribute, Double> entry : result.entrySet()) {
            AppraiseAttribute attr = entry.getKey();
        }
    }
}
