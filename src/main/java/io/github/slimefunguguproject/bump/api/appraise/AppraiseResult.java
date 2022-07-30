package io.github.slimefunguguproject.bump.api.appraise;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.utils.AppraiseUtils;
import io.github.slimefunguguproject.bump.utils.ValidateUtils;
import io.github.slimefunguguproject.bump.utils.tags.BumpTag;

import lombok.Getter;

/**
 * An {@link AppraiseResult} contains results of attributes and
 *
 * @author ybw0014
 */
@Getter
@SuppressWarnings("ConstantConditions")
public final class AppraiseResult {
    /**
     * This is a reference to the {@link AppraiseType} that generates this {@link AppraiseResult}.
     */
    @Getter
    private final AppraiseType appraiseType;

    /**
     * This unmodifiable map stores the {@link AppraiseAttribute} and result.
     */
    @Getter
    private final Map<AppraiseAttribute, Double> result;

    /**
     * This represents the calculated overall percentile.
     */
    @Getter
    private final double totalPercentile;

    /**
     * Construct this {@link AppraiseResult} from {@link Builder}
     *
     * @param builder The {@link Builder} to construct this {@link AppraiseResult} from.
     */
    private AppraiseResult(@Nonnull Builder builder) {
        this.appraiseType = builder.appraiseType;
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
        for (Map.Entry<Byte, Byte> entry : Bump.getRegistry().getStarThresholds().entrySet()) {
            if (totalPercentile >= entry.getKey()) {
                return entry.getValue();
            }
        }
        return 0;
    }

    /**
     * This method applies the appraisal result to given {@link ItemStack}.
     *
     * @param itemStack The {@link ItemStack}.
     */
    @ParametersAreNonnullByDefault
    public void apply(ItemStack itemStack) {
        if (!ValidateUtils.noAirItem(itemStack)) {
            throw new IllegalArgumentException("ItemStack cannot be null or air.");
        }
        ItemMeta meta = itemStack.getItemMeta();
        Material material = itemStack.getType();

        for (Map.Entry<AppraiseAttribute, Double> entry : result.entrySet()) {
            Attribute attr = entry.getKey().getAttribute();

            for (EquipmentSlot slot : appraiseType.getValidEquipmentSlots()) {
                // Check if the material is applicable for slot
                BumpTag tag = BumpTag.getTag(slot.name() + "_SLOT");
                if (tag.isTagged(material)) {
                    meta.addAttributeModifier(attr,
                        new AttributeModifier(UUID.randomUUID(), attr.name(), entry.getValue(), AppraiseUtils.getOperation(attr), slot)
                    );
                }
            }
        }

        itemStack.setItemMeta(meta);
    }

    /**
     * This builder class is used to generate a {@link AppraiseResult}.
     */
    static class Builder {
        private final AppraiseType appraiseType;
        private final Map<AppraiseAttribute, Double> result = new HashMap<>();
        private double totalPercentile = 0;

        Builder(@Nonnull AppraiseType type) {
            appraiseType = type;
        }

        /**
         * This method adds an appraised attribute with its value to result.
         *
         * @param attribute The {@link AppraiseAttribute}.
         * @param result    The value of the attribute
         * @return This {@link Builder}
         */
        Builder add(AppraiseAttribute attribute, double result) {
            this.result.put(attribute, result);
            totalPercentile += attribute.getWeightedPercentile(result);
            return this;
        }

        AppraiseResult build() {
            return new AppraiseResult(this);
        }
    }
}
