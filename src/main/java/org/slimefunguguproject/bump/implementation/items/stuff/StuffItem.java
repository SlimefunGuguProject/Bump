package org.slimefunguguproject.bump.implementation.items.stuff;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import org.bukkit.inventory.ItemStack;
import org.slimefunguguproject.bump.implementation.setup.BumpItemGroups;

/**
 * {@link UnplaceableBlock} items in stuff category.
 */
public class StuffItem extends UnplaceableBlock {
    public StuffItem(SlimefunItemStack itemStack, RecipeType recipeType, ItemStack[] recipe) {
        super(BumpItemGroups.STUFF, itemStack, recipeType, recipe);
    }
}
