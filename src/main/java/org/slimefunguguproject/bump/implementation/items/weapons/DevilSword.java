package org.slimefunguguproject.bump.implementation.items.weapons;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.inventory.ItemStack;
import org.slimefunguguproject.bump.implementation.Bump;
import org.slimefunguguproject.bump.implementation.BumpItems;

public class DevilSword extends BumpSword {

    public DevilSword() {
        super(5, BumpItems.DEVIL_SWORD, RecipeType.ARMOR_FORGE, new ItemStack[] {
            SlimefunItems.MAGIC_LUMP_2, SlimefunItems.ENDER_RUNE, SlimefunItems.MAGIC_LUMP_2,
            SlimefunItems.FIRE_RUNE, SlimefunItems.FIRE_RUNE, SlimefunItems.MAGIC_LUMP_2,
            SlimefunItems.ENDER_RUNE, SlimefunItems.MAGIC_LUMP_2, null
        });
    }

    @Override
    public void onItemUse(Player p, ItemStack itemStack) {
        Bump.getLocalization().sendActionbarMessage(p, "weapon.devil_sword");
        for (int i = 0; i < 20; i++) {
            p.launchProjectile(SmallFireball.class);
            p.spawnParticle(Particle.ENCHANTMENT_TABLE, p.getLocation(), 1);
        }
    }
}
