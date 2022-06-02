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

    private Utils() {}

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
     * Try to push {@link ItemStack} to player's inventory,
     * if full, then drop on the ground
     *
     * @param p the {@link Player} to be dealt with
     * @param itemStacks all {@link ItemStack} to be pushed
     */
    public static void pushToPlayerInventory(@Nonnull Player p, @Nonnull ItemStack... itemStacks) {
        Validate.notNull(p, "player should not be null");
        Validate.notNull(itemStacks, "at least one ItemStack is needed");

        // filter null ItemStacks
        List<ItemStack> items = new ArrayList<>();
        for (ItemStack item : itemStacks) {
            if (item != null) {
                items.add(item);
            }
        }
        Map<Integer, ItemStack> remainingItemMap = p.getInventory().addItem(items.toArray(new ItemStack[0]));

        for (ItemStack item : remainingItemMap.values()) {
            p.getWorld().dropItem(p.getLocation(), item.clone());
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
