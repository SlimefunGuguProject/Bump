package io.github.slimefunguguproject.bump.implementation.items.weapons

import io.github.slimefunguguproject.bump.Bump
import io.github.slimefunguguproject.bump.implementation.tasks.WeaponProjectileTask
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import org.bukkit.Sound
import org.bukkit.entity.DragonFireball
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scheduler.BukkitRunnable

/**
 * [Heaven-demon Crumble Sword][SkyDevilSword] will fire 3 [dragon fireballs][DragonFireball]
 * and get some potion effects when using
 *
 * @author ybw0014
 */
class SkyDevilSword(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<ItemStack?>,
    hunger: Int,
) : BumpSword(itemGroup, itemStack, recipeType, recipe, hunger) {
    override fun onItemUse(p: Player, sword: ItemStack) {
        Bump.localization.sendActionbarMessage(p, "weapon.sky_devil_sword.activated")

        p.isGlowing = true
        p.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 300, 3))
        p.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 3))

        object : BukkitRunnable() {
            var count: Int = 3

            override fun run() {
                if (count > 0) {
                    p.playSound(p.location, Sound.ENTITY_ENDER_DRAGON_SHOOT, 1.0f, 1.0f)
                    val projectile = p.launchProjectile(DragonFireball::class.java)
                    WeaponProjectileTask.track(projectile)
                    count--
                } else {
                    cancel()
                    p.isGlowing = false
                    Bump.localization.sendActionbarMessage(p, "weapon.sky_devil_sword.deactivated")
                }
            }
        }.runTaskTimer(Bump.instance, 1L, 100L)
    }
}
