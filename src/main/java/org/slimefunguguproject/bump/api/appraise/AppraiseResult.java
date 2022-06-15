package org.slimefunguguproject.bump.api.appraise;

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

import org.slimefunguguproject.bump.utils.AppraiseUtils;

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
     * @param attribute  The {@link AppraiseAttribute}
     * @param value      The value of the attribute
     * @param percentage The weight of the attribute
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
    public int getStars() {
        if (overallPercentage >= 100) {
            return 20;
        } else if (overallPercentage >= 98) {
            return 10;
        } else if (overallPercentage >= 96) {
            return 9;
        } else if (overallPercentage >= 92) {
            return 8;
        } else if (overallPercentage >= 88) {
            return 7;
        } else if (overallPercentage >= 82) {
            return 6;
        } else if (overallPercentage >= 74) {
            return 5;
        } else if (overallPercentage >= 64) {
            return 4;
        } else if (overallPercentage >= 48) {
            return 3;
        } else if (overallPercentage >= 30) {
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
     * @param slot The {@link EquipmentSlot} of appraisal
     */
    @ParametersAreNonnullByDefault
    public void apply(ItemMeta meta, EquipmentSlot slot) {
        Preconditions.checkArgument(meta != null, "ItemMeta cannot be null");

        for (Map.Entry<AppraiseAttribute, Double> entry : result.entrySet()) {
            Attribute attr = entry.getKey().attribute();
            meta.addAttributeModifier(attr,
                new AttributeModifier(UUID.randomUUID(), attr.name(), entry.getValue(), AppraiseUtils.getOperation(attr), slot)
            );
        }
    }
}
