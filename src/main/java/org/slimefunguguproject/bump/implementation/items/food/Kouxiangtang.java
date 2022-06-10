package org.slimefunguguproject.bump.implementation.items.food;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.slimefunguguproject.bump.implementation.BumpItems;

/**
 * Chewing gum.
 *
 * @author ybw0014
 */
public class Kouxiangtang extends ItemFood {

    public Kouxiangtang() {
        super(BumpItems.KOUXIANGTANG, RecipeType.COMPRESSOR, new ItemStack[] {
            SlimefunItems.MAGIC_SUGAR
        });
    }

    @Override
    public void applyFoodEffects(Player p) {
        p.setFoodLevel(6);
        p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 1000, 5));
    }
}
