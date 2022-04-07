package bxx2004.bump.abstracts;

import bxx2004.bump.handlers.BowUseHandler;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.bukkit.inventory.ItemStack;

public abstract class BumpBow extends SimpleSlimefunItem<BowUseHandler> implements DamageableItem {
    protected BumpBow(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public boolean isDamageable() {
        return true;
    }
}
