package bxx2004.bump.abstracts;

import bxx2004.bump.interfaces.CostHungerItem;
import bxx2004.bump.slimefun.BumpItemGroups;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.apache.commons.lang.Validate;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * A {@link BumpSword} is a {@link SlimefunItem} with an {@link ItemSetting} of hunger cost
 *
 * @author ybw0014
 */
public abstract class BumpSword extends SimpleSlimefunItem<ItemUseHandler> implements CostHungerItem {

    protected final IntRangeSetting hungerCost;

    public BumpSword(int hunger, SlimefunItemStack itemStack, RecipeType recipeType, ItemStack[] recipe) {
        super(BumpItemGroups.WEAPON, itemStack, recipeType, recipe);

        // hunger cost
        Validate.isTrue(hunger >= 0 && hunger <= 20, "Hunger cost must be between 0 and 20");
        hungerCost = new IntRangeSetting(this, "hunger-cost", hunger, 5, 20);
        addItemSetting(hungerCost);
    }

    public @Nonnull ItemSetting<Integer> getHungerCostSetting() {
        return hungerCost;
    }
}
