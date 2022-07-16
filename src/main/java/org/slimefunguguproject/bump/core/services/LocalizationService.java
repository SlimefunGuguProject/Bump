package org.slimefunguguproject.bump.core.services;

import java.text.MessageFormat;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.base.Preconditions;

import org.bukkit.entity.Player;

import net.guizhanss.guizhanlib.localization.Localization;
import net.guizhanss.guizhanlib.utils.ChatUtil;
import net.guizhanss.guizhanlib.utils.StringUtil;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

import org.slimefunguguproject.bump.implementation.Bump;

/**
 * This is an extended {@link Localization} service made for Bump.
 *
 * @author ybw0014
 */
@SuppressWarnings("ConstantConditions")
public final class LocalizationService extends Localization {
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
    public void sendMessage(Player p, String messageKey, Object... args) {
        Preconditions.checkArgument(p != null, "Player cannot be null");
        Preconditions.checkArgument(messageKey != null, "Message key cannot be null");

        ChatUtil.send(p, MessageFormat.format(getString("messages." + messageKey), args));
    }

    @ParametersAreNonnullByDefault
    public void sendActionbarMessage(Player p, String messageKey, Object... args) {
        Preconditions.checkArgument(p != null, "Player cannot be null");
        Preconditions.checkArgument(messageKey != null, "Message key cannot be null");

        String message = MessageFormat.format(getString("messages." + messageKey), args);

        BaseComponent[] components = TextComponent.fromLegacyText(ChatUtil.color(message));
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, components);
    }
}
