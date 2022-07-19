package io.github.slimefunguguproject.bump.implementation.listeners;

import org.bukkit.Particle;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * This {@link Listener} is responsible for dragon breath damage.
 *
 * @author ybw0014
 */
public class DragonBreathListener implements Listener {
    @EventHandler
    public void onPlayerDamaged(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof AreaEffectCloud damager
            && damager.getParticle() == Particle.DRAGON_BREATH
            && damager.getSource() instanceof Player damageSource
            && e.getEntity() instanceof Player p
            && damageSource.getUniqueId().equals(p.getUniqueId())
        ) {
            e.setCancelled(true);
        }
    }
}
