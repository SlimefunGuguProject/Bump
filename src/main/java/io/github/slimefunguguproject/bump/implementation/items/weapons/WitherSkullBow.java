package io.github.slimefunguguproject.bump.implementation.items.weapons;

import javax.annotation.Nonnull;

import org.bukkit.Sound;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.WitherSkull;
import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.core.handlers.BowUseHandler;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.implementation.BumpItems;
import io.github.slimefunguguproject.bump.implementation.tasks.WeaponProjectileTask;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

/**
 * {@link WitherSkullBow Withered bow} will launch {@link WitherSkull} when using.
 *
 * @author ybw0014
 */
public class WitherSkullBow extends BumpBow {

    public WitherSkullBow() {
        super(5, BumpItems.WITHERSKULL_BOW, RecipeType.ARMOR_FORGE, new ItemStack[]{
            SlimefunItems.NECROTIC_SKULL, BumpItems.PEACH_WOOD, SlimefunItems.NECROTIC_SKULL,
            SlimefunItems.POWER_CRYSTAL, BumpItems.PEACH_WOOD, SlimefunItems.NECROTIC_SKULL,
            BumpItems.PEACH_WOOD, null, null
        });
    }

    @Nonnull
    @Override
    public BowUseHandler getItemHandler() {
        return (e, p, item) -> {
            e.setCancelled(true);
            if (costHunger(p)) {
                damageItem(p, item);

                Bump.getLocalization().sendActionbarMessage(p, "weapon.wither_skull_bow");

                p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1.0F, 1.0F);

                Projectile projectile = p.launchProjectile(WitherSkull.class);
                WeaponProjectileTask.track(projectile);
            } else {
                Bump.getLocalization().sendActionbarMessage(p, "weapon.low-food-level");
            }
        };
    }
}
