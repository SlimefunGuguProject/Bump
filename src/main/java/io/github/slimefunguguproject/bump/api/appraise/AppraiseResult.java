package io.github.slimefunguguproject.bump.api.appraise;

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
        for (Map.Entry<Integer, Integer> entry : starThreshold.entrySet()) {
            if (overallPercentage >= entry.getKey()) {
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
            Attribute attr = entry.getKey().attribute();
            meta.addAttributeModifier(attr,
                new AttributeModifier(UUID.randomUUID(), attr.name(), entry.getValue(), AppraiseUtils.getOperation(attr), slot)
            );
        }
    }
}
