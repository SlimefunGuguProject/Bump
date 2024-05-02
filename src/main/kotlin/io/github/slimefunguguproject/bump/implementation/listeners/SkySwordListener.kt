package io.github.slimefunguguproject.bump.implementation.listeners

import io.github.slimefunguguproject.bump.Bump
import io.github.slimefunguguproject.bump.utils.constant.Keys
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

class SkySwordListener(plugin: Bump) : Listener {
    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    @EventHandler
    fun onPlayerHitGround(e: EntityDamageEvent) {
        val p = e.entity
        if (p !is Player) return
        if (e.cause == EntityDamageEvent.DamageCause.FALL
            && PersistentDataAPI.getBoolean(p, Keys.SKY_SWORD_PROTECTED)
        ) {
            e.isCancelled = true
            Bump.localization.sendActionbarMessage(p, "weapon.sky_sword.protected")
            PersistentDataAPI.setBoolean(p, Keys.SKY_SWORD_PROTECTED, false)
        }
    }
}
