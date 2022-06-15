package org.slimefunguguproject.bump.core.attributes;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.core.attributes.ItemAttribute;

import org.slimefunguguproject.bump.utils.Utils;

/**
 * This {@link ItemAttribute} indicates that the item will cost hunger when using.
 *
 * @author ybw0014
 */
public interface CostHungerItem extends ItemAttribute {
    /**
     * This method returns the {@link ItemSetting} of hunger cost.
     *
     * @return the {@link ItemSetting} of hunger cost
     */
    ItemSetting<Integer> getHungerCostSetting();

    /**
     * This method returns the hunger cost from {@link ItemSetting}.
     *
     * @return the hunger cost
     */
    default int getHungerCost() {
        return getHungerCostSetting().getValue();
    }

    /**
     * This method will check whether {@link Player}'s food level is sufficient to cost.
     *
     * @param p the {@link Player} that uses the item
     * @return if player has enough hunger
     */
    default boolean checkHunger(@Nonnull Player p) {
        Preconditions.checkArgument(p != null, "player cannot be null");

        if (p.getGameMode() != GameMode.CREATIVE) {
            return p.getFoodLevel() >= getHungerCost();
        } else {
            return true;
        }
    }

    /**
     * This method will cost hunger of a {@link Player}.
     * <p>
     * It will call {@link FoodLevelChangeEvent} and cost {@link Player}'s
     * hunger if {@link Player}'s {@link GameMode} is not creative and
     * the event is not cancelled.
     *
     * @param p the {@link Player} that uses the item
     * @return if player has reduced enough hunger
     */
    default boolean costHunger(@Nonnull Player p) {
        Preconditions.checkArgument(p != null, "player cannot be null");

        if (checkHunger(p)) {
            return Utils.setFoodLevel(p, p.getFoodLevel() - getHungerCost());
        } else {
            return false;
        }
    }
}
