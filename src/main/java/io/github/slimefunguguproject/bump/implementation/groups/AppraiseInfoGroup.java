package io.github.slimefunguguproject.bump.implementation.groups;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;

import dev.sefiraat.sefilib.slimefun.itemgroup.MainFlexGroup;

/**
 * A flex item group that displays apprase types.
 *
 * @author ybw0014
 */
public final class AppraiseInfoGroup extends MainFlexGroup {
    /**
     * Creates a new MainFlexGroup
     *
     * @param name The name of the Group, this will be displayed when opened
     * @param key  The {@link NamespacedKey} used to assign this group
     * @param item The {@link ItemStack} which will act as the display item
     */
    public AppraiseInfoGroup(String name, NamespacedKey key, ItemStack item) {
        super(name, key, item);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isVisible(Player player, PlayerProfile playerProfile, SlimefunGuideMode guideMode) {
        return false;
    }
}
