package org.slimefunguguproject.bump.utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.base.Preconditions;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@ParametersAreNonnullByDefault
@SuppressWarnings("ConstantConditions")
public final class Utils {
    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Get a {@link String} of consecutive stars
     *
     * @param n the number of stars
     * @return {@link String} of consecutive stars
     */
    @Nonnull
    public static String getStars(int n) {
        StringBuilder builder = new StringBuilder();
        while (n > 0) {
            builder.append("⭐");
            n--;
        }
        return builder.toString();
    }

    /**
     * Make the {@link ItemStack} glow.
     *
     * @param item The {@link ItemStack} to be dealt with.
     */
    public static void glowItem(@Nonnull ItemStack item) {
        if (!validateItem(item)) {
            return;
        }

        final ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.LUCK, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
    }

    /**
     * Just a simple null check wrapper
     *
     * @param itemStack The {@link ItemStack} to be checked
     * @return if the {@link ItemStack} is valid
     */
    public static boolean validateItem(@Nullable ItemStack itemStack) {
        try {
            Preconditions.checkArgument(itemStack != null, "ItemStack should not be null");
            Preconditions.checkArgument(itemStack.getType() != Material.AIR, "ItemStack should not be empty");
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
