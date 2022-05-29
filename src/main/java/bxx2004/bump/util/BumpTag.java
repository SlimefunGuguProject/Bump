package bxx2004.bump.util;

import net.guizhanss.guizhanlib.minecraft.MinecraftTag;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Tags for some materials
 */
public enum BumpTag {
    /**
     * Armors
     */
    ARMOR {
        @Override
        public boolean isTagged(Material type) {
            return MinecraftTag.ARMOR.isTagged(type);
        }
    },

    /**
     * Weapons
     */
    WEAPON {
        @Override
        public boolean isTagged(Material type) {
            return type == Material.ELYTRA
                || MinecraftTag.SWORD.isTagged(type);
        }
    },

    /**
     * Horse armors
     */
    HORSE_ARMOR {
        @Override
        public boolean isTagged(Material type) {
            return MinecraftTag.HORSE_ARMOR.isTagged(type);
        }
    };

    /**
     * This method returns if given {@link Material} is tagged.
     *
     * @param type the {@link Material} to be determined
     *
     * @return if given {@link Material} is tagged
     */
    public abstract boolean isTagged(Material type);

    /**
     * This method returns if given {@link ItemStack} is tagged.
     *
     * @param itemStack the {@link ItemStack} to be determined
     *
     * @return if given {@link ItemStack} is tagged
     */
    public boolean isTagged(ItemStack itemStack) {
        return isTagged(itemStack.getType());
    }
}
