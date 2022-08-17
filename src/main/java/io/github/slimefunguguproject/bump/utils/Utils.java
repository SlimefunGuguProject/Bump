package io.github.slimefunguguproject.bump.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.slimefunguguproject.bump.implementation.Bump;

import net.guizhanss.guizhanlib.utils.StringUtil;

import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("ConstantConditions")
public final class Utils {
    /**
     * Get a {@link String} of consecutive stars, maximum at 10.
     * <p>
     * When there are over 10 stars, returns number + star.
     *
     * @param n The number of stars
     *
     * @return {@link String} of consecutive stars.
     */
    @Nonnull
    public static String getStars(int n) {
        if (n <= 10) {
            StringBuilder builder = new StringBuilder();
            while (n > 0) {
                builder.append("⭐");
                n--;
            }
            return builder.toString();
        } else {
            return n + " ⭐";
        }
    }

    /**
     * Make the {@link ItemStack} glow.
     *
     * @param item The {@link ItemStack} to be dealt with.
     */
    public static void glowItem(@Nonnull ItemStack item) {
        if (!ValidateUtils.noAirItem(item)) {
            return;
        }

        final ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.LUCK, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
    }

    /**
     * Get the material name.
     * @param material The {@link Material}
     * @return The material name in Simplified Chinese if GuizhanLibPlugin exists. Otherwise, in English.
     */
    public static String getMaterialName(@Nonnull Material material) {
        try {
            Class<?> clazz = Class.forName("net.guizhanss.guizhanlib.minecraft.helper.MaterialHelper");
            Object result = clazz.getMethod("getName", Material.class).invoke(null, material);
            return String.valueOf(result);
        } catch (ClassNotFoundException | NoSuchMethodException | NullPointerException
            | IllegalAccessException | InvocationTargetException e) {
            return StringUtil.humanize(material.toString());
        }
    }
}
