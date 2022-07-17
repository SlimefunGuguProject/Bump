package io.github.slimefunguguproject.bump.implementation.items.weapons;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.implementation.BumpItems;
import io.github.slimefunguguproject.bump.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;

/**
 * {@link SkySword Heaven Breaking Sword} will lift player up to the sky when using.
 *
 * @author ybw0014
 */
public class SkySword extends BumpSword {

    public SkySword() {
        super(5, BumpItems.SKY_SWORD, RecipeType.ARMOR_FORGE, new ItemStack[]{
            SlimefunItems.MAGIC_LUMP_2, SlimefunItems.AIR_RUNE, SlimefunItems.MAGIC_LUMP_2,
            SlimefunItems.RAINBOW_RUNE, SlimefunItems.RAINBOW_RUNE, SlimefunItems.MAGIC_LUMP_2,
            SlimefunItems.AIR_RUNE, SlimefunItems.MAGIC_LUMP_2, null
        });
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onItemUse(Player p, ItemStack itemStack) {
        Bump.getLocalization().sendActionbarMessage(p, "weapon.sky_sword.activated");
        p.setVelocity(p.getLocation().getDirection().multiply(0.5D));
        p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
        for (int i = 0; i < 20; i++) {
            p.spawnParticle(Particle.EXPLOSION_HUGE, p.getLocation(), 1);
        }
        PersistentDataAPI.setBoolean(p, Keys.SKY_SWORD_PROTECTED, true);
    }
}
