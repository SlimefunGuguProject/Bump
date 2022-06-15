package org.slimefunguguproject.bump.api.blocks;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

import net.guizhanss.guizhanlib.slimefun.machines.MenuBlock;

/**
 * A {@link MenuBlock} that has some initial settings.
 *
 * @author ybw0014
 */
public abstract class AbstractMenuBlock extends MenuBlock {

    /**
     * Constructor of {@link MenuBlock}.
     * Add events on break and place
     *
     * @param itemGroup  the {@link ItemGroup} of this {@link MenuBlock}
     * @param item       the {@link SlimefunItemStack} of this {@link MenuBlock}
     * @param recipeType the {@link RecipeType} of this {@link MenuBlock}
     * @param recipe     the recipe of this {@link MenuBlock}
     */
    @ParametersAreNonnullByDefault
    public AbstractMenuBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    protected int[] getInputSlots() {
        return new int[0];
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[0];
    }
}
