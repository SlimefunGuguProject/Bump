package org.slimefunguguproject.bump.implementation.setup;

import org.bukkit.Material;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

import org.slimefunguguproject.bump.implementation.Bump;

/**
 * This class holds all {@link ItemGroup}s of Bump.
 *
 * @author ybw0014
 */
public final class BumpItemGroups {
    public static final NestedItemGroup MAIN = new NestedItemGroup(
        Bump.createKey("bump_main"),
        new CustomItemStack(
            Material.DIAMOND,
            Bump.getLocalization().getCategoryName("main")
        )
    );
    public static final SubItemGroup ARMOR = new SubItemGroup(
        Bump.createKey("bump_armor"),
        MAIN,
        new CustomItemStack(
            Material.DIAMOND,
            Bump.getLocalization().getCategoryName("armor")
        )
    );
    public static final SubItemGroup FOOD = new SubItemGroup(
        Bump.createKey("bump_food"),
        MAIN,
        new CustomItemStack(
            Material.BREAD,
            Bump.getLocalization().getCategoryName("food")
        )
    );
    public static final SubItemGroup MACHINE = new SubItemGroup(
        Bump.createKey("bump_machine"),
        MAIN,
        new CustomItemStack(
            Material.ANVIL,
            Bump.getLocalization().getCategoryName("machine")
        )
    );
    public static final SubItemGroup STUFF = new SubItemGroup(
        Bump.createKey("bump_stuff"),
        MAIN,
        new CustomItemStack(
            Material.NETHER_STAR,
            Bump.getLocalization().getCategoryName("stuff")
        )
    );
    public static final SubItemGroup TOOL = new SubItemGroup(
        Bump.createKey("bump_tool"),
        MAIN,
        new CustomItemStack(
            Material.DIAMOND_PICKAXE,
            Bump.getLocalization().getCategoryName("tool")
        )
    );
    public static final SubItemGroup WEAPON = new SubItemGroup(
        Bump.createKey("bump_weapon"),
        MAIN,
        new CustomItemStack(
            Material.IRON_SWORD,
            Bump.getLocalization().getCategoryName("weapon")
        )
    );

    private BumpItemGroups() {
        throw new IllegalStateException("Utility class");
    }
}
