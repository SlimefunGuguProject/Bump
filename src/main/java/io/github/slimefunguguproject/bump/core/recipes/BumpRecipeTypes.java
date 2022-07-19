package io.github.slimefunguguproject.bump.core.recipes;

import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.implementation.BumpItems;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

import lombok.experimental.UtilityClass;

/**
 * This class holds all {@link RecipeType} of Bump.
 *
 * @author ybw0014
 */
@UtilityClass
public final class BumpRecipeTypes {
    public static final RecipeType GETGOLD_SPADE = new RecipeType(
        Bump.createKey("getgold_spade"),
        BumpItems.GETGOLD_SPADE
    );
}
