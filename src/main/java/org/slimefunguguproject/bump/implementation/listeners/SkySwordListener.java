package org.slimefunguguproject.bump.implementation.listeners;

import javax.annotation.Nonnull;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;

import org.slimefunguguproject.bump.implementation.Bump;
import org.slimefunguguproject.bump.implementation.items.weapons.SkySword;
import org.slimefunguguproject.bump.utils.Keys;

/**
 * This {@link Listener} is related to {@link SkySword}.
 *
 * @author ybw0014
 * @see SkySword
 */
public final class SkySwordListener implements Listener {
    @EventHandler
    public void onPlayerHitGround(@Nonnull EntityDamageEvent e) {
        if (e.getEntity() instanceof Player p && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if (PersistentDataAPI.getBoolean(p, Keys.SKY_SWORD_PROTECTED)) {
                e.setCancelled(true);
                Bump.getLocalization().sendActionbarMessage(p, "weapon.sky_sword.protected");
                PersistentDataAPI.setBoolean(p, Keys.SKY_SWORD_PROTECTED, false);
            }
        }
    }
}
