package bxx2004.bump.abstracts;

import bxx2004.bump.Bump;
import bxx2004.bump.interfaces.CooldownItem;
import bxx2004.bump.interfaces.CostHungerItem;
import bxx2004.bump.slimefun.BumpItemGroups;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * A {@link BumpSword} is a {@link SlimefunItem} with an {@link ItemSetting} of hunger cost and cooldown
 *
 * @author ybw0014
 */
public abstract class BumpSword extends SimpleSlimefunItem<ItemUseHandler> implements CostHungerItem, CooldownItem {

    protected final IntRangeSetting hungerCost;
    protected final IntRangeSetting cooldownInSeconds = new IntRangeSetting(this, "cooldown-in-seconds", 0, 0, Integer.MAX_VALUE);

    public BumpSword(int hunger, SlimefunItemStack itemStack, RecipeType recipeType, ItemStack[] recipe) {
        super(BumpItemGroups.WEAPON, itemStack, recipeType, recipe);

        // hunger cost
        Validate.isTrue(hunger >= 0 && hunger <= 20, "The default hunger cost must be between 0 and 20");
        hungerCost = new IntRangeSetting(this, "hunger-cost", 0, hunger, 20);
        addItemSetting(hungerCost);

        // cooldown
        addItemSetting(cooldownInSeconds);
    }

    public @Nonnull ItemSetting<Integer> getHungerCostSetting() {
        return hungerCost;
    }

    public int getCooldown() {
        return cooldownInSeconds.getValue();
    }

    /**
     * This function is called when player use this sword
     *
     * @param p         the {@link Player} that uses this sword
     * @param sword the {@link ItemStack} of this sword
     */
    public abstract void onItemUse(Player p, ItemStack sword);

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Player p = e.getPlayer();

            if (isCooldown(e.getItem())) {
                if (costHunger(p)) {
                    onItemUse(p, e.getItem());
                } else {
                    Bump.getLocalization().sendActionbarMessage(p, "weapon.low-food-level");
                }
            } else {
                Bump.getLocalization().sendActionbarMessage(p, "weapon.cooldown");
            }
        };
    }
}
