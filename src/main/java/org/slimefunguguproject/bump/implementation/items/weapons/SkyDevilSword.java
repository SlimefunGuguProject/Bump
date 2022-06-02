package org.slimefunguguproject.bump.implementation.items.weapons;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.slimefunguguproject.bump.implementation.Bump;
import org.slimefunguguproject.bump.implementation.BumpItems;

public class SkyDevilSword extends BumpSword {

    public SkyDevilSword() {
        super(5, BumpItems.SKY_DEVIL_SWORD, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
            null, null, null,
            BumpItems.SKY_SWORD, BumpItems.UPDATE_POWER, BumpItems.DEVIL_SWORD,
            null, null, null
        });
    }

    @Override
    public void onItemUse(Player p, ItemStack itemStack) {
        Bump.getLocalization().sendActionbarMessage(p, "weapon.sky_devil_sword.activate");

        p.setGlowing(true);
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 3));
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 3));

        (new BukkitRunnable() {
            int count = 3;

            @Override
            public void run() {
                if (count > 0) {
                    p.launchProjectile(DragonFireball.class);
                    count--;
                } else {
                    this.cancel();
                    p.setGlowing(false);
                    Bump.getLocalization().sendActionbarMessage(p, "weapon.sky_devil_sword.end");
                }
            }
        }).runTaskTimer(Bump.getInstance(), 1L, 100L);
    }
}
