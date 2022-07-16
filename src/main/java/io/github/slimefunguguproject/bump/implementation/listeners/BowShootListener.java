package io.github.slimefunguguproject.bump.implementation.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

import io.github.slimefunguguproject.bump.core.handlers.BowUseHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;

/**
 * This {@link Listener} will call {@link BowUseHandler}
 * when {@link Player} fires with a bow.
 *
 * @author ybw0014
 * @see BowUseHandler
 */
public final class BowShootListener implements Listener {
    @EventHandler
    public void onBowUse(EntityShootBowEvent e) {
        if (e.getEntity() instanceof Player player && e.getProjectile() instanceof Arrow) {
            SlimefunItem bow = SlimefunItem.getByItem(e.getBow());

            if (bow != null) {
                bow.callItemHandler(BowUseHandler.class, handler -> handler.onUse(e, player, e.getBow()));
            }
        }
    }
}
