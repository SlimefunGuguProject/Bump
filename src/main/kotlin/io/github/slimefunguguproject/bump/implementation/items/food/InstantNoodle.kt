package io.github.slimefunguguproject.bump.implementation.items.food

import io.github.slimefunguguproject.bump.utils.FoodLevelUtils
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType


class InstantNoodle(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>
) : ItemFood(itemGroup, itemStack, recipeType, recipe) {
    override fun applyFoodEffects(p: Player) {
        FoodLevelUtils.set(p, 20)
        p.addPotionEffect(PotionEffect(PotionEffectType.HEALTH_BOOST, 1500, 9))
        p.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 200, 4))
    }
}
