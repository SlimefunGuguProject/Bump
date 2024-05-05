package io.github.slimefunguguproject.bump.implementation.items.materials

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock
import org.bukkit.inventory.ItemStack

class BumpMaterial(
    itemGroup: ItemGroup,
    itemStack: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<out ItemStack?>
) : UnplaceableBlock(itemGroup, itemStack, recipeType, recipe)
