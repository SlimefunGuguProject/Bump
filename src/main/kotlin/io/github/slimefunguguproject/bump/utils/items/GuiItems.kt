package io.github.slimefunguguproject.bump.utils.items

import io.github.slimefunguguproject.bump.Bump
import io.github.slimefunguguproject.bump.api.appraise.AppraiseType
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

// TODO: Remove this, gui items are defined in the menus directly
object GuiItems {
    val APPRAISE_BUTTON: ItemStack = CustomItemStack(
        Material.NAME_TAG,
        Bump.localization.getString("gui.appraise.name"),
        *Bump.localization.getStringArray("gui.appraise.lore")
    )
    val APPRAISE_PAPER: ItemStack = CustomItemStack(
        Material.PAPER,
        Bump.localization.getString("gui.appraisal_paper.name"),
        *Bump.localization.getStringArray("gui.appraisal_paper.lore")
    )
    val GRIND_BUTTON: ItemStack = CustomItemStack(
        Material.GRINDSTONE,
        Bump.localization.getString("gui.grind.name"),
        *Bump.localization.getStringArray("gui.grind.lore")
    )

    fun appraiseTypeSelector(type: AppraiseType): ItemStack {
        val lore = AppraiseUtils.getDescriptionLore(type) +
            Bump.localization.getStringList("gui.appraise_type_selector.lore")

        return CustomItemStack(
            Material.MAP,
            Bump.localization.getString("gui.appraise_type_selector.name", type.name),
            lore
        )
    }
}
