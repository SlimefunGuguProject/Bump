package bxx2004.bump.slimefun;

import bxx2004.bump.Bump;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

/**
 * This class holds all {@link RecipeType} of Bump.
 *
 * @author ybw0014
 */
public class BumpRecipeTypes {
    private BumpRecipeTypes() {}

    public static RecipeType GETGOLD_SPADE = new RecipeType(
        Bump.createKey("getgold_spade"),
        BumpItems.GETGOLD_SPADE
    );
}
