package io.github.slimefunguguproject.bump.implementation.items;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.implementation.groups.BumpItemGroups;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

/**
 * A {@link SlimefunItem} in hidden item group, usually deprecated or secret item.
 *
 * @author ybw0014
 */
public class HiddenItem extends SlimefunItem {
    @ParametersAreNonnullByDefault
    public HiddenItem(SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(BumpItemGroups.HIDDEN, item, recipeType, recipe);
    }

    @ParametersAreNonnullByDefault
    public HiddenItem(SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack output) {
        super(BumpItemGroups.HIDDEN, item, recipeType, recipe, output);
    }
}
