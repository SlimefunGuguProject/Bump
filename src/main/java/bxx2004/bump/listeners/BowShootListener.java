package bxx2004.bump.listeners;

import bxx2004.bump.handlers.BowShootHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

public final class BowShootListener implements Listener {
    @EventHandler
    public void onBowShoot(EntityShootBowEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }

        Player p = (Player) e.getEntity();
        ItemStack item = p.getInventory().getItemInMainHand();

        if (!item.getType().isAir()) {
            SlimefunItem sfItem = SlimefunItem.getByItem(item);

            if (sfItem != null && sfItem.canUse(p, true)) {
                sfItem.callItemHandler(BowShootHandler.class, handler -> handler.onShoot(e, p, item));
            }
        }
    }
}
