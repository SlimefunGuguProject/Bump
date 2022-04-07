package bxx2004.bump.slimefun.items.food;

import bxx2004.bump.abstracts.ConsumableFood;
import bxx2004.bump.slimefun.BumpItems;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Kele extends ConsumableFood {

    public Kele() {
        super(BumpItems.KELE, RecipeType.MAGIC_WORKBENCH, new ItemStack[] {
            BumpItems.WATER_SUGAR, new ItemStack(Material.WATER_BUCKET), BumpItems.WATER_SUGAR,
            new ItemStack(Material.WATER_BUCKET), SlimefunItems.MAGIC_SUGAR, new ItemStack(Material.WATER_BUCKET),
            BumpItems.WATER_SUGAR, new ItemStack(Material.WATER_BUCKET), BumpItems.WATER_SUGAR
        });
    }

    @Override
    public void applyFoodEffects(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 2000, 5));
    }
}
