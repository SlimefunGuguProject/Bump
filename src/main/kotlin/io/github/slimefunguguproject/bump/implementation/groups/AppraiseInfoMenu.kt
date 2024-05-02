package io.github.slimefunguguproject.bump.implementation.groups

import io.github.bakedlibs.dough.items.CustomItemStack
import io.github.slimefunguguproject.bump.Bump
import io.github.slimefunguguproject.bump.api.appraise.AppraiseType
import io.github.slimefunguguproject.bump.implementation.menu.AppraiseTypesMenu
import io.github.slimefunguguproject.bump.utils.items.AppraiseUtils
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.util.function.Consumer

internal class AppraiseInfoMenu(
    name: String,
    successCallback: Consumer<AppraiseType>,
    backCallback: Runnable
) : AppraiseTypesMenu(name, successCallback, backCallback) {
    override fun getDisplayItem(type: AppraiseType): ItemStack {
        val lore = AppraiseUtils.getDescriptionLore(type)
        lore.add("")
        lore.add(Bump.localization.getString("appraise_info.click"))

        return CustomItemStack(
            Material.PAPER,
            Bump.localization.getString("appraise_info.name", type.name),
            lore
        )
    }
}
