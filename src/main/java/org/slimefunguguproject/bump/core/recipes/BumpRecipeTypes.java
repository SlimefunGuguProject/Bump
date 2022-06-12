package org.slimefunguguproject.bump.core.recipes;

import org.slimefunguguproject.bump.implementation.Bump;
import org.slimefunguguproject.bump.implementation.BumpItems;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

/**
 * This class holds all {@link RecipeType} of Bump.
 *
 * @author ybw0014
 */
public final class BumpRecipeTypes {
    private BumpRecipeTypes() {
        throw new IllegalStateException("Utility class");
    }

    public static RecipeType GETGOLD_SPADE = new RecipeType(
        Bump.createKey("getgold_spade"),
        BumpItems.GETGOLD_SPADE
    );
}
