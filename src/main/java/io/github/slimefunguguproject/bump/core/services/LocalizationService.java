package io.github.slimefunguguproject.bump.core.services;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.logging.Level;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.base.Preconditions;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.bakedlibs.dough.items.CustomItemStack;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

import net.guizhanss.guizhanlib.localization.Localization;
import net.guizhanss.guizhanlib.localization.MinecraftLocalization;
import net.guizhanss.guizhanlib.minecraft.utils.ChatUtil;
import net.guizhanss.guizhanlib.utils.StringUtil;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * This is an extended {@link Localization} service made for Bump.
 *
 * @author ybw0014
 */
@SuppressWarnings({"ConstantConditions", "java:S1192"})
public final class LocalizationService extends MinecraftLocalization {
    private static final String NULL_RECIPE_TYPE_MESSAGE = "RecipeType Id cannot be null";

    public LocalizationService(Bump plugin) {
        super(plugin);
    }

    @ParametersAreNonnullByDefault
    @Nonnull
    public String getString(String key, Object... args) {
        return MessageFormat.format(getString(key), args);
    }

    @Nonnull
    public String getCategoryName(@Nonnull String categoryId) {
        Preconditions.checkArgument(categoryId != null, "Category Id cannot be null");

        return getString("categories." + StringUtil.dehumanize(categoryId).toLowerCase(Locale.ROOT));
    }

    @Nonnull
    public String getItemName(@Nonnull String itemId) {
        Preconditions.checkArgument(itemId != null, "Item Id cannot be null");

        return getString("items." + StringUtil.dehumanize(itemId).toLowerCase(Locale.ROOT) + ".name");
    }

    @Nonnull
    public String[] getItemLore(@Nonnull String itemId) {
        Preconditions.checkArgument(itemId != null, "Item Id cannot be null");

        return getStringArray("items." + StringUtil.dehumanize(itemId).toLowerCase(Locale.ROOT) + ".lore");
    }

    @Nonnull
    public String getResearchName(@Nonnull String researchId) {
        Preconditions.checkArgument(researchId != null, "Research Id cannot be null");

        return getString("researches." + StringUtil.dehumanize(researchId).toLowerCase(Locale.ROOT));
    }

    @ParametersAreNonnullByDefault
    @Nonnull
    public ItemStack getRecipeTypeItem(String recipeTypeId, Material material) {
        Preconditions.checkArgument(recipeTypeId != null, NULL_RECIPE_TYPE_MESSAGE);
        Preconditions.checkArgument(material != null, "Material cannot be null");

        recipeTypeId = StringUtil.dehumanize(recipeTypeId).toLowerCase(Locale.ROOT);

        return new CustomItemStack(
            material,
            getString("recipes." + recipeTypeId + ".name"),
            getStringArray("recipes." + recipeTypeId + ".lore")
        );
    }

    @ParametersAreNonnullByDefault
    @Nonnull
    public ItemStack getRecipeTypeItem(String recipeTypeId, String texture) {
        Preconditions.checkArgument(recipeTypeId != null, NULL_RECIPE_TYPE_MESSAGE);
        Preconditions.checkArgument(texture != null, "Texture cannot be null");

        recipeTypeId = StringUtil.dehumanize(recipeTypeId).toLowerCase(Locale.ROOT);

        return new CustomItemStack(
            SlimefunUtils.getCustomHead(texture),
            getString("recipes." + recipeTypeId + ".name"),
            getStringArray("recipes." + recipeTypeId + ".lore")
        );
    }

    @ParametersAreNonnullByDefault
    @Nonnull
    public ItemStack getRecipeTypeItem(String recipeTypeId, ItemStack itemStack) {
        Preconditions.checkArgument(recipeTypeId != null, NULL_RECIPE_TYPE_MESSAGE);
        Preconditions.checkArgument(itemStack != null, "ItemStack cannot be null");

        recipeTypeId = StringUtil.dehumanize(recipeTypeId).toLowerCase(Locale.ROOT);

        return new CustomItemStack(
            itemStack,
            getString("recipes." + recipeTypeId + ".name"),
            getStringArray("recipes." + recipeTypeId + ".lore")
        );
    }

    @ParametersAreNonnullByDefault
    public void sendMessage(CommandSender sender, String messageKey, Object... args) {
        Preconditions.checkArgument(sender != null, "CommandSender cannot be null");
        Preconditions.checkArgument(messageKey != null, "Message key cannot be null");

        ChatUtil.send(sender, MessageFormat.format(getString("messages." + messageKey), args));
    }

    @ParametersAreNonnullByDefault
    public void sendActionbarMessage(Player p, String messageKey, Object... args) {
        Preconditions.checkArgument(p != null, "Player cannot be null");
        Preconditions.checkArgument(messageKey != null, "Message key cannot be null");

        String message = MessageFormat.format(getString("messages." + messageKey), args);

        BaseComponent[] components = TextComponent.fromLegacyText(ChatUtil.color(message));
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, components);
    }

    @ParametersAreNonnullByDefault
    public void log(Level level, String key, Object... args) {
        Preconditions.checkArgument(level != null, "Log level cannot be null");
        Preconditions.checkArgument(key != null, "Key cannot be null");

        String message = MessageFormat.format(getString("console." + key), args);

        Bump.log(level, message);
    }
}
