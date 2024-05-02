package io.github.slimefunguguproject.bump.implementation.items.machines

import io.github.bakedlibs.dough.items.CustomItemStack
import io.github.slimefunguguproject.bump.Bump
import io.github.slimefunguguproject.bump.api.appraise.AppraiseType
import io.github.slimefunguguproject.bump.implementation.menu.AppraiseTypesMenu
import io.github.slimefunguguproject.bump.utils.items.AppraiseUtils
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.util.function.Consumer

internal class AppraiserSelector(
    successCallback: Consumer<AppraiseType>,
    backCallback: Runnable
) : AppraiseTypesMenu(
    Bump.localization.getString("gui.appraise_type_selector_menu.title"),
    successCallback,
    backCallback
) {
    override fun getDisplayItem(type: AppraiseType): ItemStack {
        val lore: MutableList<String> = AppraiseUtils.getDescriptionLore(type)
        lore.addAll(Bump.localization.getStringList("gui.appraise_type_selector_menu.lore"))

        return CustomItemStack(
            Material.PAPER,
            Bump.localization.getString("appraise_info.name", type.name),
            lore
        )
    }
}
