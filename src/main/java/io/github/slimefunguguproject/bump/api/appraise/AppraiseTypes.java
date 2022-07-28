package io.github.slimefunguguproject.bump.api.appraise;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import io.github.slimefunguguproject.bump.utils.tags.BumpTag;

import net.guizhanss.guizhanlib.minecraft.MinecraftTag;

/**
 * This class holds an appraisal type. It is bind to a collection of
 *
 * @author ybw0014
 */
public enum AppraiseTypes {
    WEAPON(false) {
        @Override
        public boolean isValidMaterial(@Nonnull Material type) {
            return BumpTag.WEAPON.isTagged(type);
        }

        @Override
        @Nonnull
        public EquipmentSlot getEquipmentSlot(@Nonnull Material type) {
            return EquipmentSlot.HAND;
        }
    },
    ARMOR(false) {
        @Override
        public boolean isValidMaterial(@Nonnull Material type) {
            return BumpTag.ARMOR.isTagged(type);
        }

        @Override
        @Nonnull
        public EquipmentSlot getEquipmentSlot(@Nonnull Material type) {
            if (MinecraftTag.HELMET.isTagged(type)) {
                return EquipmentSlot.HEAD;
            } else if (MinecraftTag.CHESTPLATE.isTagged(type)) {
                return EquipmentSlot.CHEST;
            } else if (MinecraftTag.LEGGINGS.isTagged(type)) {
                return EquipmentSlot.LEGS;
            } else if (MinecraftTag.BOOTS.isTagged(type)) {
                return EquipmentSlot.FEET;
            } else {
                return EquipmentSlot.HAND;
            }
        }
    },
    HORSE_ARMOR(true) {
        @Override
        public boolean isValidMaterial(@Nonnull Material type) {
            return BumpTag.HORSE_ARMOR.isTagged(type);
        }

        @Override
        @Nonnull
        public EquipmentSlot getEquipmentSlot(@Nonnull Material type) {
            return EquipmentSlot.CHEST;
        }
    };

    private final boolean allowVanillaItems;

    AppraiseTypes(boolean allowVanillaItems) {
        this.allowVanillaItems = allowVanillaItems;
    }

    /**
     * Get the {@link AppraiseTypes} from given {@link Material}.
     *
     * @param material The {@link Material}
     * @return Appropriate {@link AppraiseTypes}
     * @throws IllegalArgumentException when given material is invalid.
     */
    @Nonnull
    public static AppraiseTypes getFromMaterial(@Nonnull Material material) {
        Preconditions.checkArgument(material != null, "Material should not be null");

        for (AppraiseTypes type : AppraiseTypes.values()) {
            if (type.isValidMaterial(material)) {
                return type;
            }
        }

        throw new IllegalArgumentException("Invalid material");
    }

    /**
     * Check if the given {@link Material} is valid.
     *
     * @param type The {@link Material} to be checked
     * @return If the given {@link Material} is valid
     */
    public abstract boolean isValidMaterial(@Nonnull Material type);

    /**
     * Get the {@link EquipmentSlot} for appraisal to be applied to.
     *
     * @param type The {@link Material} of appraisal equipment
     * @return The target {@link EquipmentSlot}
     */
    public abstract EquipmentSlot getEquipmentSlot(@Nonnull Material type);

    /**
     * Check if the given {@link Material} is valid, with SlimefunItem check.
     *
     * @param material       the {@link Material} to be checked
     * @param isSlimefunItem if the item is SlimefunItem
     * @return If the given {@link Material} is valid
     */
    public boolean isValidMaterial(@Nonnull Material material, boolean isSlimefunItem) {
        if (isValidMaterial(material)) {
            return allowVanillaItems || isSlimefunItem;
        } else {
            return false;
        }
    }
}
