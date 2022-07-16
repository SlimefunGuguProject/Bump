package org.slimefunguguproject.bump.utils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Utility methods about player's food level.
 *
 * @author ybw0014
 */
public class FoodLevelUtils {
    private FoodLevelUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This method calls {@link FoodLevelChangeEvent} and add {@link Player}'s
     * food level if {@link Player}'s {@link GameMode} is not creative and
     * the event is not cancelled
     *
     * @param p     the {@link Player} that food level will be changed
     * @param level the level to be added
     * @return if the food level is added
     */
    public static boolean add(Player p, int level) {
        int newLevel = Math.min(20, p.getFoodLevel() + level);
        return set(p, newLevel);
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
    public static boolean set(Player p, int level) {
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
}
