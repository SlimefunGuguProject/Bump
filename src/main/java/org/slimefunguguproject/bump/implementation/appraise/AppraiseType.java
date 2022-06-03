package org.slimefunguguproject.bump.implementation.appraise;

import net.guizhanss.guizhanlib.minecraft.MinecraftTag;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.slimefunguguproject.bump.utils.BumpTag;

import javax.annotation.Nonnull;

/**
 * This enum holds all available appraisal types.
 *
 * @author ybw0014
 */
public enum AppraiseType {
    WEAPON(false) {
        @Override
        public boolean isValidMaterial(@Nonnull Material type) {
            return BumpTag.WEAPON.isTagged(type);
        }

        @Override
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
        public EquipmentSlot getEquipmentSlot(@Nonnull Material type) {
            return EquipmentSlot.CHEST;
        }
    };

    private final boolean allowVanillaItems;

    AppraiseType(boolean allowVanillaItems) {
        this.allowVanillaItems = allowVanillaItems;
    }

    public abstract boolean isValidMaterial(@Nonnull Material type);

    public abstract EquipmentSlot getEquipmentSlot(@Nonnull Material type);

    public boolean allowVanillaItems() {
        return allowVanillaItems;
    }

    public boolean isValidMaterial(@Nonnull Material material, boolean isSlimefunItem) {
        if (isValidMaterial(material)) {
            return allowVanillaItems || isSlimefunItem;
        } else {
            return false;
        }
    }

    @Nonnull
    public static AppraiseType getFromMaterial(@Nonnull Material material) {
        Validate.notNull(material, "Material should not be null");

        for (AppraiseType type : AppraiseType.values()) {
            if (type.isValidMaterial(material)) {
                return type;
            }
        }

        throw new IllegalArgumentException("Invalid material");
    }
}
