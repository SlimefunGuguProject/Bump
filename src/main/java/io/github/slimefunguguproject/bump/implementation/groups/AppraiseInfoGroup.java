package io.github.slimefunguguproject.bump.implementation.groups;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;

import dev.sefiraat.sefilib.slimefun.itemgroup.SimpleFlexGroup;

/**
 * A flex item group that displays apprase types.
 *
 * @author ybw0014
 */
public final class AppraiseInfoGroup extends SimpleFlexGroup {

    public AppraiseInfoGroup(String name, NamespacedKey key, ItemStack item) {
        super(Bump.getInstance(), name, key, item);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isVisible(Player player, PlayerProfile playerProfile, SlimefunGuideMode guideMode) {
        return false;
    }
}
