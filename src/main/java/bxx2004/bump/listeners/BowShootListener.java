package bxx2004.bump.listeners;

import bxx2004.bump.handlers.BowUseHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.weapons.SlimefunBow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

/**
 * This {@link Listener} will call {@link BowUseHandler}.
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

            if (bow instanceof SlimefunBow) {
                bow.callItemHandler(BowUseHandler.class, handler -> handler.onUse(e, (Player) e.getEntity(), e.getBow()));
            }
        }
    }
}
