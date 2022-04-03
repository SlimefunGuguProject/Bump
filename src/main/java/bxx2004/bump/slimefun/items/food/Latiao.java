package bxx2004.bump.slimefun.items.food;

import bxx2004.bump.slimefun.BumpItems;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Latiao extends ConsumableFood {

    public Latiao() {
        super(BumpItems.LATIAO, RecipeType.COMPRESSOR, new ItemStack[] {
            SlimefunItems.WHEAT_FLOUR
        });
    }

    @Override
    public void applyFoodEffects(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 2000, 5));
    }
}
