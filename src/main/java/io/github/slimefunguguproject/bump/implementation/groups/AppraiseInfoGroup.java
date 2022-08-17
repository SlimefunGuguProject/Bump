package io.github.slimefunguguproject.bump.implementation.groups;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.implementation.menus.AppraiseTypeMenu;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;

/**
 * A flex item group that displays apprase types.
 *
 * @author ybw0014
 */
public final class AppraiseInfoGroup extends FlexItemGroup {

    private final String name;

    @ParametersAreNonnullByDefault
    public AppraiseInfoGroup(String name, NamespacedKey key, ItemStack item) {
        super(key, item);

        this.name = name;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isVisible(Player player, PlayerProfile playerProfile, SlimefunGuideMode guideMode) {
        return false;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void open(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        new AppraiseInfoMenu(name, type -> {
            // open the detail menu
            new AppraiseTypeMenu(type, () -> open(p, profile, mode)).open(p);
        }, () -> {
            // back to main menu
            SlimefunGuide.openItemGroup(profile, BumpItemGroups.MAIN, mode, 1);
        }).open(p);
    }
}
