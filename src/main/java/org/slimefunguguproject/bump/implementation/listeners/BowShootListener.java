package org.slimefunguguproject.bump.implementation.listeners;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.slimefunguguproject.bump.core.handlers.BowUseHandler;

/**
 * This {@link Listener} will call {@link BowUseHandler}
 * when {@link Player} fires with a bow.
 *
 * @see BowUseHandler
 *
 * @author ybw0014
 */
public final class BowShootListener implements Listener {
    @EventHandler
    public void onBowUse(EntityShootBowEvent e) {
        if (e.getEntity() instanceof Player && e.getProjectile() instanceof Arrow) {
            SlimefunItem bow = SlimefunItem.getByItem(e.getBow());

            if (bow != null) {
                bow.callItemHandler(BowUseHandler.class, handler -> handler.onUse(e, (Player) e.getEntity(), e.getBow()));
            }
        }
    }
}
