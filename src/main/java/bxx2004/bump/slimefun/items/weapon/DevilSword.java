package bxx2004.bump.slimefun.items.weapon;

import bxx2004.bump.Bump;
import bxx2004.bump.abstracts.BumpSword;
import bxx2004.bump.slimefun.BumpItems;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class DevilSword extends BumpSword {

    public DevilSword() {
        super(5, BumpItems.DEVIL_SWORD, RecipeType.ARMOR_FORGE, new ItemStack[] {
            SlimefunItems.MAGIC_LUMP_2, SlimefunItems.ENDER_RUNE, SlimefunItems.MAGIC_LUMP_2,
            SlimefunItems.FIRE_RUNE, SlimefunItems.FIRE_RUNE, SlimefunItems.MAGIC_LUMP_2,
            SlimefunItems.ENDER_RUNE, SlimefunItems.MAGIC_LUMP_2, null
        });
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Player p = e.getPlayer();

            if (costHunger(p)) {
                Bump.getLocalization().sendActionbarMessage(p, "weapon.devil_sword");
                for (int i = 0; i < 20; i++) {
                    p.launchProjectile(SmallFireball.class);
                    p.spawnParticle(Particle.ENCHANTMENT_TABLE, p.getLocation(), 1);
                }
            } else {
                Bump.getLocalization().sendActionbarMessage(p, "weapon.low-food-level");
            }
        };
    }
}
