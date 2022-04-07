package bxx2004.bump.slimefun.items.food;

import bxx2004.bump.abstracts.ItemFood;
import bxx2004.bump.slimefun.BumpItems;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Fangbianmian extends ItemFood {

    public Fangbianmian() {
        super(BumpItems.FANGBIANMIAN, RecipeType.MAGIC_WORKBENCH, new ItemStack[] {
            new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.WATER_BUCKET),
            BumpItems.KSF_STUFF, BumpItems.KSF_STUFF, BumpItems.KSF_STUFF,
            SlimefunItems.WHEAT_FLOUR, SlimefunItems.WHEAT_FLOUR, SlimefunItems.WHEAT_FLOUR
        });
    }

    @Override
    public void applyFoodEffects(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 2000, 5));
    }
}
