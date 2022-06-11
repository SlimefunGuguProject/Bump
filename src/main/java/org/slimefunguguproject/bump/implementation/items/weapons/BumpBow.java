package org.slimefunguguproject.bump.implementation.items.weapons;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import com.google.common.base.Preconditions;
import org.bukkit.inventory.ItemStack;
import org.slimefunguguproject.bump.core.attributes.CostHungerItem;
import org.slimefunguguproject.bump.core.handlers.BowUseHandler;
import org.slimefunguguproject.bump.implementation.setup.BumpItemGroups;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link BumpBow} is a {@link SlimefunItem} which has an {@link ItemSetting} of damageable
 *
 * @author ybw0014
 */
public abstract class BumpBow extends SimpleSlimefunItem<BowUseHandler> implements DamageableItem, CostHungerItem {

    protected final IntRangeSetting hungerCost;
    private final ItemSetting<Boolean> costDurability = new ItemSetting<>(this, "cost-durability", true);

    @ParametersAreNonnullByDefault
    protected BumpBow(int hunger, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(BumpItemGroups.WEAPON, item, recipeType, recipe);

        // durability cost
        addItemSetting(costDurability);

        // hunger cost
        Preconditions.checkArgument(hunger >= 0 && hunger <= 20, "Hunger cost must be between 0 and 20");
        hungerCost = new IntRangeSetting(this, "hunger-cost", 0, hunger, 20);
        addItemSetting(hungerCost);
    }

    @Override
    public boolean isDamageable() {
        return costDurability.getValue();
    }

    @Nonnull
    public ItemSetting<Integer> getHungerCostSetting() {
        return hungerCost;
    }
}
