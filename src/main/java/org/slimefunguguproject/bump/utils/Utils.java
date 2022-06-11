package org.slimefunguguproject.bump.utils;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Utils {
    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This method calls {@link FoodLevelChangeEvent} and add {@link Player}'s
     * food level if {@link Player}'s {@link GameMode} is not creative and
     * the event is not cancelled
     *
     * @param p the {@link Player} that food level will be changed
     * @param level the level to be added
     *
     * @return if the food level is added
     */
    public static boolean addFoodLevel(Player p, int level) {
        int newLevel = Math.min(20, p.getFoodLevel() + level);
        return setFoodLevel(p, newLevel);
    }

    /**
     * This method calls {@link FoodLevelChangeEvent} and set {@link Player}'s
     * food level if {@link Player}'s {@link GameMode} is not creative and
     * the event is not cancelled
     *
     * @param p     the {@link Player} that food level will be changed
     * @param level the target food level
     * @return if the food level is changed
     */
    public static boolean setFoodLevel(Player p, int level) {
        if (p.getGameMode() != GameMode.CREATIVE) {
            FoodLevelChangeEvent event = new FoodLevelChangeEvent(p, level);
            Bukkit.getPluginManager().callEvent(event);

            if (!event.isCancelled()) {
                p.setFoodLevel(event.getFoodLevel());
                return true;
            }

            return false;
        } else {
            return true;
        }
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
