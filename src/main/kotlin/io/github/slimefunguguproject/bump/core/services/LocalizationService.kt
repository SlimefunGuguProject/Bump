@file:Suppress("deprecation")

package io.github.slimefunguguproject.bump.core.services

import io.github.slimefunguguproject.bump.Bump
import io.github.slimefunguguproject.bump.utils.FileUtils.listYmlFilesInJar
import io.github.slimefunguguproject.bump.utils.items.MaterialType
import net.guizhanss.guizhanlib.minecraft.utils.ChatUtil
import net.guizhanss.guizhanlib.minecraft.utils.ItemUtil
import net.guizhanss.guizhanlib.slimefun.addon.SlimefunLocalization
import net.guizhanss.guizhanlib.utils.StringUtil
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.io.File
import java.text.MessageFormat

class LocalizationService(
    private val plugin: Bump,
    private val jarFile: File
) : SlimefunLocalization(plugin) {
    companion object {
        private const val FOLDER_NAME = "lang"
    }

    init {
        extractTranslations()
    }

    private fun extractTranslations() {
        val translationsFolder = File(plugin.dataFolder, FOLDER_NAME)
        if (!translationsFolder.exists()) {
            translationsFolder.mkdirs()
        }
        val translationFiles = listYmlFilesInJar(jarFile, "${FOLDER_NAME}/")
        for (translationFile in translationFiles) {
            val filePath = FOLDER_NAME + File.separator + translationFile
            val file = File(plugin.dataFolder, filePath)
            if (file.exists()) {
                continue
            }
            plugin.saveResource(filePath, true)
        }
    }

    private fun String.toId() = StringUtil.dehumanize(this).uppercase()

    fun getString(key: String, vararg args: Any?): String = MessageFormat.format(getString(key), *args)

    // items
    fun getItemName(itemId: String, vararg args: Any?) = getString("items.${itemId.toId()}.name", *args)
    fun getItemLore(itemId: String): List<String> = getStringList("items.${itemId.toId()}.lore")

    // item groups (special items with prefix _IG_)
    fun getItemGroupName(groupId: String) = getItemName("_IG_${groupId}")
    fun getItemGroupItem(item: MaterialType, groupId: String) = getItem("_IG_${groupId.toId()}", item.convert())

    // single line lore
    fun getLore(loreId: String, vararg args: Any?): String = getString("lores.${loreId.lowercase()}", *args)

    // research
    fun getResearchName(researchId: String): String = getString("researches.${researchId.lowercase()}")

    // gui items (special items with prefix _UI_)
    fun getGuiItem(item: MaterialType, id: String, vararg extraLore: String): ItemStack =
        ItemUtil.appendLore(getItem("_UI_${id.toId()}", item.convert()), *extraLore)

    fun getGuiItemName(id: String, vararg args: Any?) = getItemName("_UI_${id.toId()}", *args)
    fun getGuiItemLore(id: String): List<String> = getItemLore("_UI_${id.toId()}")

    fun sendMessage(sender: CommandSender, key: String, vararg args: Any) {
        ChatUtil.send(sender, MessageFormat.format(getString("messages.$key"), *args))
    }

    fun sendActionbarMessage(p: Player, key: String, vararg args: Any) {
        val message = MessageFormat.format(getString("messages.$key"), *args)

        val components = TextComponent.fromLegacyText(ChatUtil.color(message))
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, *components)
    }
}
