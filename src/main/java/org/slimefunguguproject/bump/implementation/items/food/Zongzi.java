package org.slimefunguguproject.bump.implementation.items.food;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.slimefunguguproject.bump.implementation.BumpItems;

/**
 * Zongzi. Rice dumpling
 *
 * @author haiman233
 */
public class Zongzi extends ItemFood {

    public Zongzi() {
        super(BumpItems.ZONGZI, RecipeType.MAGIC_WORKBENCH, new ItemStack[] {
            new ItemStack(Material.LILY_PAD), new ItemStack(Material.ACACIA_LEAVES), new ItemStack(Material.LILY_PAD), 
            new ItemStack(Material.ACACIA_LEAVES), new ItemStack(Material.WHEAT), new ItemStack(Material.ACACIA_LEAVES), 
            new ItemStack(Material.LILY_PAD), new ItemStack(Material.ACACIA_LEAVES), new ItemStack(Material.LILY_PAD)
        });
    }

    @Override
    public void applyFoodEffects(Player p) {
        p.setFoodLevel(8);
        p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 100, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1000, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 150, 2));
        p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 500, 2));
    }
}
