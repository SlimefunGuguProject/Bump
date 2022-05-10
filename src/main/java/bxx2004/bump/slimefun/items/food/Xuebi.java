package bxx2004.bump.slimefun.items.food;

import bxx2004.bump.abstracts.ConsumableFood;
import bxx2004.bump.slimefun.BumpItems;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Sprite.
 *
 * @author ybw0014
 */
public class Xuebi extends ConsumableFood {

    public Xuebi() {
        super(BumpItems.XUEBI, RecipeType.MAGIC_WORKBENCH, new ItemStack[] {
            BumpItems.WATER_SUGAR, new ItemStack(Material.WATER_BUCKET), BumpItems.WATER_SUGAR, 
            new ItemStack(Material.WATER_BUCKET), BumpItems.WATER_SUGAR, new ItemStack(Material.WATER_BUCKET), 
            BumpItems.WATER_SUGAR, new ItemStack(Material.WATER_BUCKET), BumpItems.WATER_SUGAR
        });
    }

    @Override
    public void applyFoodEffects(Player p) {
        p.setFoodLevel(20);
        p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 2000, 5));
    }
}
