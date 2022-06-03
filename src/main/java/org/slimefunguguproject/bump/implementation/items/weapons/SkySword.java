package org.slimefunguguproject.bump.implementation.items.weapons;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.slimefunguguproject.bump.implementation.Bump;
import org.slimefunguguproject.bump.implementation.BumpItems;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * {@link SkySword Heaven Breaking Sword} will lift player up to the sky when using.
 *
 * @author ybw0014
 */
public class SkySword extends BumpSword {

    public SkySword() {
        super(5, BumpItems.SKY_SWORD, RecipeType.ARMOR_FORGE, new ItemStack[] {
            SlimefunItems.MAGIC_LUMP_2, SlimefunItems.AIR_RUNE, SlimefunItems.MAGIC_LUMP_2,
            SlimefunItems.RAINBOW_RUNE, SlimefunItems.RAINBOW_RUNE, SlimefunItems.MAGIC_LUMP_2,
            SlimefunItems.AIR_RUNE, SlimefunItems.MAGIC_LUMP_2, null
        });
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onItemUse(Player p, ItemStack itemStack) {
        Bump.getLocalization().sendActionbarMessage(p, "weapon.sky_sword");
        Vector direction = p.getLocation().toVector();
        p.setVelocity(p.getVelocity().add(direction.multiply(0.5D)));
        for (int i = 0; i < 20; i++) {
            p.spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 1);
        }
    }
}
