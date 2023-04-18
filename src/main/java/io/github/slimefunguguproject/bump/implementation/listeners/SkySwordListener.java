package io.github.slimefunguguproject.bump.implementation.listeners;

import javax.annotation.Nonnull;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.implementation.items.weapons.SkySword;
import io.github.slimefunguguproject.bump.utils.constant.Keys;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;

/**
 * This {@link Listener} is related to {@link SkySword}.
 *
 * @author ybw0014
 * @see SkySword
 */
public final class SkySwordListener implements Listener {
    @EventHandler
    public void onPlayerHitGround(@Nonnull EntityDamageEvent e) {
        if (e.getEntity() instanceof Player p
            && e.getCause() == EntityDamageEvent.DamageCause.FALL
            && PersistentDataAPI.getBoolean(p, Keys.SKY_SWORD_PROTECTED)
        ) {
            e.setCancelled(true);
            Bump.getLocalization().sendActionbarMessage(p, "weapon.sky_sword.protected");
            PersistentDataAPI.setBoolean(p, Keys.SKY_SWORD_PROTECTED, false);
        }
    }
}
