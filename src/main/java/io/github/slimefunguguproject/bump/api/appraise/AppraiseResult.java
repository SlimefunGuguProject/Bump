package io.github.slimefunguguproject.bump.api.appraise;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.base.Preconditions;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.slimefunguguproject.bump.utils.AppraiseUtils;

/**
 * An {@link AppraiseResult} represents an appraisal result.
 *
 * @author ybw0014
 */
public final class AppraiseResult {
    // TODO: configurable
    private static final Map<Integer, Integer> starThreshold = new LinkedHashMap<>();

    static {
        starThreshold.put(100, 20);
        starThreshold.put(98, 10);
        starThreshold.put(96, 9);
        starThreshold.put(92, 8);
        starThreshold.put(88, 7);
        starThreshold.put(82, 6);
        starThreshold.put(74, 5);
        starThreshold.put(64, 4);
        starThreshold.put(48, 3);
        starThreshold.put(30, 2);
        starThreshold.put(10, 1);
    }

    private final Map<AppraiseAttribute, Double> result;
    private final double totalPercentile;

    private AppraiseResult(@Nonnull Builder builder) {
        this.result = Map.copyOf(builder.result);
        this.totalPercentile = builder.totalPercentile;
    }

    /**
     * This method returns the stars of appraisal result
     * calculated by overall percentage.
     *
     * @return The number of stars of the result
     */
    public int getStars() {
        for (Map.Entry<Integer, Integer> entry : starThreshold.entrySet()) {
            if (totalPercentile >= entry.getKey()) {
                return entry.getValue();
            }
        }
        return 0;
    }

    /**
     * This method applies the appraisal result to given {@link ItemMeta}
     *
     * @param meta The {@link ItemMeta}
     * @param slot The {@link EquipmentSlot} of appraisal
     */
    @ParametersAreNonnullByDefault
    public void apply(ItemMeta meta, EquipmentSlot slot) {
        Preconditions.checkArgument(meta != null, "ItemMeta cannot be null");

        for (Map.Entry<AppraiseAttribute, Double> entry : result.entrySet()) {
            Attribute attr = entry.getKey().getAttribute();
            meta.addAttributeModifier(attr,
                new AttributeModifier(UUID.randomUUID(), attr.name(), entry.getValue(), AppraiseUtils.getOperation(attr), slot)
            );
        }
    }

    /**
     * This builder class is used to generate a {@link AppraiseResult}.
     * <p>
     * I want the result map immutable.
     */
    public static class Builder {
        private final Map<AppraiseAttribute, Double> result = new HashMap<>();
        private double totalPercentile = 0;

        /**
         * This method adds an appraised attribute with its value to result.
         *
         * @param attribute The {@link AppraiseAttribute}.
         * @param result    The value of the attribute
         * @return This {@link Builder}
         */
        public Builder add(AppraiseAttribute attribute, double result) {
            this.result.put(attribute, result);
            totalPercentile += attribute.getWeightedPercentile(result);
            return this;
        }

        public AppraiseResult build() {
            return new AppraiseResult(this);
        }
    }
}
