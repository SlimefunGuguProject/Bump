@file:Suppress("deprecation")

package io.github.slimefunguguproject.bump.implementation.menu

import io.github.slimefunguguproject.bump.api.appraise.AppraiseType
import io.github.slimefunguguproject.bump.core.BumpRegistry
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.function.Consumer
import kotlin.math.ceil
import kotlin.math.min

/**
 * This class will open a menu with all [AppraiseType].
 *
 * @author ybw0014
 */
abstract class AppraiseTypesMenu(
    private val name: String,
    private val successCallback: Consumer<AppraiseType>,
    private val backCallback: Runnable
) {
    companion object {
        private const val PAGE_SIZE = 36

        private const val GUIDE_BACK = 1

        private const val PAGE_PREVIOUS = 46
        private const val PAGE_NEXT = 52

        private val HEADER = intArrayOf(
            0, 1, 2, 3, 4, 5, 6, 7, 8
        )
        private val FOOTER = intArrayOf(
            45, 46, 47, 48, 49, 50, 51, 52, 53
        )
    }

    /**
     * Open the menu to [Player].
     *
     * @param p The [Player] to open the menu to.
     */
    fun open(p: Player) {
        val chestMenu = ChestMenu(name)

        for (slot in HEADER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler())
        }
        for (slot in FOOTER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler())
        }

        chestMenu.setEmptySlotsClickable(false)
        displayCollection(p, chestMenu, 1)
        chestMenu.open(p)
    }

    private fun displayCollection(player: Player, menu: ChestMenu, page: Int) {
        val appraiseTypes = BumpRegistry.appraiseTypes
            .filter { type -> type.hasPermission(player) }
            .toList()
        val total = appraiseTypes.size
        val totalPages = ceil(total / PAGE_SIZE.toDouble()).toInt()
        val start = (page - 1) * PAGE_SIZE
        val end = min(start + PAGE_SIZE, total)

        val subList = appraiseTypes.subList(start, end)

        setupFooter(player, menu, page, totalPages)

        // Sound
        player.playSound(player.location, Sound.ITEM_BOOK_PAGE_TURN, 1.0f, 1.0f)

        // Back
        menu.replaceExistingItem(
            GUIDE_BACK, ChestMenuUtils.getBackButton(
                player,
                "",
                ChatColor.GRAY.toString() + Slimefun.getLocalization().getMessage(player, "guide.back.guide")
            )
        )
        menu.addMenuClickHandler(GUIDE_BACK) { _, _, _, _ ->
            backCallback.run()
            false
        }

        for (i in 0 until PAGE_SIZE) {
            val slot = i + 9

            if (i + 1 <= subList.size) {
                val appraiseType = subList[i]
                menu.replaceExistingItem(slot, getDisplayItem(appraiseType))
                menu.addMenuClickHandler(slot) { _, _, _, _ ->
                    successCallback.accept(appraiseType)
                    false
                }
            } else {
                menu.replaceExistingItem(slot, null)
                menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler())
            }
        }
    }

    abstract fun getDisplayItem(type: AppraiseType): ItemStack

    private fun setupFooter(player: Player, menu: ChestMenu, page: Int, totalPages: Int) {
        for (slot in FOOTER) {
            menu.replaceExistingItem(slot, ChestMenuUtils.getBackground())
            menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler())
        }

        menu.replaceExistingItem(PAGE_PREVIOUS, ChestMenuUtils.getPreviousButton(player, page, totalPages))
        menu.addMenuClickHandler(PAGE_PREVIOUS) { p: Player, _, _, _ ->
            val previousPage = page - 1
            if (previousPage >= 1) {
                displayCollection(p, menu, previousPage)
            }
            false
        }

        menu.replaceExistingItem(PAGE_NEXT, ChestMenuUtils.getNextButton(player, page, totalPages))
        menu.addMenuClickHandler(PAGE_NEXT) { p: Player, _, _, _ ->
            val nextPage = page + 1
            if (nextPage <= totalPages) {
                displayCollection(p, menu, nextPage)
            }
            false
        }
    }
}
