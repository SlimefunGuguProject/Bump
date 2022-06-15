package org.slimefunguguproject.bump.utils;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.guizhanss.guizhanlib.minecraft.MinecraftTag;

/**
 * Tags for some materials
 */
public enum BumpTag {
    /**
     * Armors
     */
    ARMOR {
        @Override
        public boolean isTagged(@Nonnull Material type) {
            return MinecraftTag.ARMOR.isTagged(type)
                || MinecraftTag.SKULL.isTagged(type);
        }
    },

    /**
     * Weapons
     */
    WEAPON {
        @Override
        public boolean isTagged(@Nonnull Material type) {
            return type == Material.TRIDENT
                || MinecraftTag.SWORD.isTagged(type);
        }
    },

    /**
     * Horse armors
     */
    HORSE_ARMOR {
        @Override
        public boolean isTagged(@Nonnull Material type) {
            return MinecraftTag.HORSE_ARMOR.isTagged(type);
        }
    },

    /**
     * Fishing Rods
     */
    FISHING_ROD {
        @Override
        public boolean isTagged(@Nonnull Material type) {
            return MinecraftTag.FISHING_ROD.isTagged(type);
        }
    },

    /**
     * Bows
     */
    BOW {
        @Override
        public boolean isTagged(@Nonnull Material type) {
            return MinecraftTag.BOW.isTagged(type);
        }
    },

    /**
     * Tools(Including axes,pickaxes,hoes,shovels)
     */
    TOOL {
        @Override
        public boolean isTagged(@Nonnull Material type) {
            return MinecraftTag.AXE.isTagged(type)
                || MinecraftTag.PICKAXE.isTagged(type)
                || MinecraftTag.SHOVEL.isTagged(type)
                || MinecraftTag.HOE.isTagged(type);
        }
    },

    /**
     * Off hand items
     */
    OFF_HAND_ITEM {
        @Override
        public boolean isTagged(@Nonnull Material type) {
            return type == Material.SHIELD
                || type == Material.SHEARS
                || type == Material.FLINT_AND_STEEL
                || type == Material.CLOCK
                || type == Material.COMPASS
                || type == Material.FILLED_MAP;
        }
    };

    /**
     * This method returns if given {@link Material} is tagged.
     *
     * @param type the {@link Material} to be determined
     * @return if given {@link Material} is tagged
     */
    public abstract boolean isTagged(@Nonnull Material type);

    /**
     * This method returns if given {@link ItemStack} is tagged.
     *
     * @param itemStack the {@link ItemStack} to be determined
     * @return if given {@link ItemStack} is tagged
     */
    public boolean isTagged(@Nonnull ItemStack itemStack) {
        return isTagged(itemStack.getType());
    }
}
