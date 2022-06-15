package org.slimefunguguproject.bump.implementation.items.weapons;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.base.Preconditions;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;

import org.slimefunguguproject.bump.core.attributes.CooldownItem;
import org.slimefunguguproject.bump.core.attributes.CostHungerItem;
import org.slimefunguguproject.bump.implementation.Bump;
import org.slimefunguguproject.bump.implementation.setup.BumpItemGroups;

/**
 * A {@link BumpSword} is a {@link SlimefunItem} with an {@link ItemSetting} of hunger cost and cooldown
 *
 * @author ybw0014
 */
public abstract class BumpSword extends SimpleSlimefunItem<ItemUseHandler> implements CostHungerItem, CooldownItem {

    protected final IntRangeSetting hungerCost;
    protected final IntRangeSetting cooldownInSeconds = new IntRangeSetting(this, "cooldown-in-seconds", 0, 0, Integer.MAX_VALUE);

    @ParametersAreNonnullByDefault
    public BumpSword(int hunger, SlimefunItemStack itemStack, RecipeType recipeType, ItemStack[] recipe) {
        super(BumpItemGroups.WEAPON, itemStack, recipeType, recipe);

        // hunger cost
        Preconditions.checkArgument(hunger >= 0 && hunger <= 20, "The default hunger cost must be between 0 and 20");
        hungerCost = new IntRangeSetting(this, "hunger-cost", 0, hunger, 20);
        addItemSetting(hungerCost);

        // cooldown
        addItemSetting(cooldownInSeconds);
    }

    @Nonnull
    public ItemSetting<Integer> getHungerCostSetting() {
        return hungerCost;
    }

    public int getCooldown() {
        return cooldownInSeconds.getValue();
    }

    /**
     * This function is called when player use this sword
     *
     * @param p     the {@link Player} that uses this sword
     * @param sword the {@link ItemStack} of this sword
     */
    public abstract void onItemUse(Player p, ItemStack sword);

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Player p = e.getPlayer();
            ItemStack item = e.getItem();

            if (isCooldown(item)) {
                if (costHunger(p)) {
                    setCooldown(item);
                    onItemUse(p, item);
                } else {
                    Bump.getLocalization().sendActionbarMessage(p, "weapon.low-food-level");
                }
            } else {
                Bump.getLocalization().sendActionbarMessage(p, "weapon.cooldown");
            }
        };
    }
}
