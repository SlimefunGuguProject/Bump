package io.github.slimefunguguproject.bump.api.items.groups;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;

public class HiddenItemGroup extends ItemGroup {
    /**
     * Initialize this {@link HiddenItemGroup}.
     *
     * @param key The {@link NamespacedKey} of this item group.
     * @param item The display {@link ItemStack} of this item group.
     */
    @ParametersAreNonnullByDefault
    public HiddenItemGroup(NamespacedKey key, ItemStack item) {
        super(key, item);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isVisible(Player p) {
        return false;
    }
}
