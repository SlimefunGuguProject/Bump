package org.slimefunguguproject.bump.utils;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ParametersAreNonnullByDefault
public class Utils {
    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This method calls {@link FoodLevelChangeEvent} and set {@link Player}'s
     * food level if {@link Player}'s {@link GameMode} is not creative and
     * the event is not cancelled
     *
     * @param p the {@link Player} that food level will be changed
     * @param level the target food level
     *
     * @return if the food level is changed
     */
    public static boolean changeFoodLevel(Player p, int level) {
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
     *
     * @return {@link String} of consecutive stars
     */
    public static @Nonnull String getStars(int n) {
        StringBuilder builder = new StringBuilder();
        while (n > 0) {
            builder.append("⭐");
            n--;
        }
        return builder.toString();
    }
}
