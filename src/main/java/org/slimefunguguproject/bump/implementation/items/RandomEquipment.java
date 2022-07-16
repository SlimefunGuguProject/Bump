package org.slimefunguguproject.bump.implementation.items;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

import org.slimefunguguproject.bump.core.attributes.AppraisableItem;

/**
 * A {@link RandomEquipment} is an item that is an {@link AppraisableItem}.
 *
 * @author ybw0014
 */
public final class RandomEquipment extends SlimefunItem implements AppraisableItem {

    @ParametersAreNonnullByDefault
    public RandomEquipment(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }
}
