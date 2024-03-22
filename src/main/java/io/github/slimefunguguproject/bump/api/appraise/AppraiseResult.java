package io.github.slimefunguguproject.bump.api.appraise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.utils.AppraiseUtils;
import io.github.slimefunguguproject.bump.utils.Utils;
import io.github.slimefunguguproject.bump.utils.ValidateUtils;
import io.github.slimefunguguproject.bump.utils.constant.Keys;
import io.github.slimefunguguproject.bump.utils.tags.BumpTag;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;

import net.guizhanss.guizhanlib.minecraft.utils.ChatUtil;

import lombok.Getter;

/**
 * An {@link AppraiseResult} contains results of attributes and the weights.
 *
 * @author ybw0014
 */
@Getter
@SuppressWarnings("ConstantConditions")
public final class AppraiseResult {
    /**
     * This is a reference to the {@link AppraiseType} that generates this {@link AppraiseResult}.
     */
    private final AppraiseType appraiseType;

    /**
     * This unmodifiable map stores the {@link AppraiseAttribute} and result.
     */
    private final Map<AppraiseAttribute, Double> result;

    /**
     * This represents the calculated overall percentage.
     */
    private final double totalPercentage;

    /**
     * Construct this {@link AppraiseResult} from {@link Builder}
     *
     * @param builder The {@link Builder} to construct this {@link AppraiseResult} from.
     */
    private AppraiseResult(@Nonnull Builder builder) {
        this.appraiseType = builder.appraiseType;
        this.result = Map.copyOf(builder.result);
        this.totalPercentage = builder.totalPercentage;
    }

    /**
     * This method returns the stars of appraisal result
     * calculated by overall percentage.
     *
     * @return The number of stars of the result
     */
    public byte getStars() {
        for (Map.Entry<Byte, Byte> entry : Bump.getRegistry().getStarThresholds().entrySet()) {
            if (totalPercentage >= entry.getKey()) {
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
    public void apply(@Nullable ItemStack itemStack) {
        if (!ValidateUtils.noAirItem(itemStack)) {
            throw new IllegalArgumentException("ItemStack cannot be null or air.");
        }
        ItemMeta meta = itemStack.getItemMeta();
        Material material = itemStack.getType();
        byte stars = getStars();

        // attributes
        for (Map.Entry<AppraiseAttribute, Double> entry : result.entrySet()) {
            Attribute attr = entry.getKey().getAttribute();

            for (EquipmentSlot slot : appraiseType.getValidEquipmentSlots()) {
                // Check if the material is applicable for slot
                BumpTag tag = BumpTag.getTag(slot.name() + "_SLOT");
                if (tag.isTagged(material)) {
                    meta.addAttributeModifier(attr,
                        new AttributeModifier(UUID.randomUUID(), appraiseType.getKey().toString(), entry.getValue(), AppraiseUtils.getOperation(attr), slot)
                    );
                }
            }
        }

        // lore
        String loreLine = Bump.getLocalization().getString("lores.appraised", Utils.getStars(stars));
        if (meta.hasLore()) {
            List<String> lore = meta.getLore();
            for (int i = 0; i < lore.size(); i++) {
                if (lore.get(i).equals(ChatUtil.color(Bump.getLocalization().getString("lores.not-appraised")))) {
                    lore.set(i, ChatUtil.color(loreLine));
                    break;
                }
            }
            meta.setLore(lore);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add(ChatUtil.color(loreLine));
            meta.setLore(lore);
        }

        // pdc
        PersistentDataAPI.setByte(meta, Keys.APPRAISE_LEVEL, stars);
        PersistentDataAPI.setByte(meta, Keys.APPRAISE_VERSION, (byte) 2);

        itemStack.setItemMeta(meta);
    }

    /**
     * This builder class is used to generate a {@link AppraiseResult}.
     */
    static class Builder {
        private final AppraiseType appraiseType;
        private final Map<AppraiseAttribute, Double> result = new HashMap<>();
        private double totalPercentage = 0;

        Builder(@Nonnull AppraiseType type) {
            appraiseType = type;
        }

        /**
         * This method adds an appraised attribute with its value to result.
         *
         * @param attribute The {@link AppraiseAttribute}.
         * @param result    The value of the attribute
         *
         * @return This {@link Builder}
         */
        Builder add(AppraiseAttribute attribute, double result) {
            this.result.put(attribute, result);
            totalPercentage += attribute.getWeightedPercentage(result);
            return this;
        }

        AppraiseResult build() {
            return new AppraiseResult(this);
        }
    }
}
