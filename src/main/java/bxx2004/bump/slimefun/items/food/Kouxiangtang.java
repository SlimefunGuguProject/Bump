package bxx2004.bump.slimefun.items.food;

import bxx2004.bump.slimefun.BumpItems;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Kouxiangtang extends ItemFood {

    public Kouxiangtang() {
        super(BumpItems.KOUXIANGTANG, RecipeType.COMPRESSOR, new ItemStack[] {
            SlimefunItems.MAGIC_SUGAR
        });
    }

    @Override
    public void applyFoodEffects(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 2000, 5));
    }
}
