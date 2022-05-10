package bxx2004.bump.slimefun.items.stuff;

import bxx2004.bump.slimefun.BumpItemGroups;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import org.bukkit.inventory.ItemStack;

/**
 * {@link UnplaceableBlock} items in stuff category.
 */
public class StuffItem extends UnplaceableBlock {
    public StuffItem(SlimefunItemStack itemStack, RecipeType recipeType, ItemStack[] recipe) {
        super(BumpItemGroups.STUFF, itemStack, recipeType, recipe);
    }
}
