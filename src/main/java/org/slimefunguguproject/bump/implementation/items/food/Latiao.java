package org.slimefunguguproject.bump.implementation.items.food;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

import org.slimefunguguproject.bump.implementation.BumpItems;
import org.slimefunguguproject.bump.utils.Utils;

/**
 * Latiao. Spicy stick.
 *
 * @author ybw0014
 */
public class Latiao extends ConsumableFood {

    public Latiao() {
        super(BumpItems.LATIAO, RecipeType.COMPRESSOR, new ItemStack[] {
            SlimefunItems.WHEAT_FLOUR
        });
    }

    @Override
    public void applyFoodEffects(Player p) {
        Utils.addFoodLevel(p, 2);
        p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 48, 2));
    }
}
