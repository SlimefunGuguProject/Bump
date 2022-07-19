package io.github.slimefunguguproject.bump.implementation.items.stuff;

import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.implementation.setup.BumpItemGroups;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;

/**
 * {@link UnplaceableBlock} items in stuff category.
 *
 * @author ybw0014
 */
public class StuffItem extends UnplaceableBlock {
    public StuffItem(SlimefunItemStack itemStack, RecipeType recipeType, ItemStack[] recipe) {
        super(BumpItemGroups.STUFF, itemStack, recipeType, recipe);
    }
}
