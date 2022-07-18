package io.github.slimefunguguproject.bump.implementation.items.weapons;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.SmallFireball;
import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.implementation.BumpItems;
import io.github.slimefunguguproject.bump.implementation.tasks.WeaponProjectileTask;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

/**
 * {@link DevilSword Demon Slayer Sword} will launch {@link SmallFireball} when using.
 *
 * @author ybw0014
 */
public class DevilSword extends BumpSword {

    public DevilSword() {
        super(5, BumpItems.DEVIL_SWORD, RecipeType.ARMOR_FORGE, new ItemStack[]{
            SlimefunItems.MAGIC_LUMP_2, SlimefunItems.ENDER_RUNE, SlimefunItems.MAGIC_LUMP_2,
            SlimefunItems.FIRE_RUNE, SlimefunItems.FIRE_RUNE, SlimefunItems.MAGIC_LUMP_2,
            SlimefunItems.ENDER_RUNE, SlimefunItems.MAGIC_LUMP_2, null
        });
    }

    @Override
    public void onItemUse(Player p, ItemStack itemStack) {
        Bump.getLocalization().sendActionbarMessage(p, "weapon.devil_sword");

        p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1.0F, 1.0F);

        for (int i = 0; i < 20; i++) {
            Projectile projectile = p.launchProjectile(SmallFireball.class);
            WeaponProjectileTask.track(projectile);
            p.spawnParticle(Particle.ENCHANTMENT_TABLE, p.getLocation(), 1);
        }
    }
}
