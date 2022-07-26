package io.github.slimefunguguproject.bump.implementation.groups;

import org.bukkit.Material;

import io.github.slimefunguguproject.bump.api.items.groups.HiddenItemGroup;
import io.github.slimefunguguproject.bump.api.items.groups.MainFlexGroup;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

import lombok.experimental.UtilityClass;

/**
 * This class holds all {@link ItemGroup}s of Bump.
 *
 * @author ybw0014
 */
@UtilityClass
public final class BumpItemGroups {
    public static final MainFlexGroup MAIN = new MainFlexGroup(
        Bump.createKey("bump_main"),
        new CustomItemStack(
            Material.DIAMOND,
            Bump.getLocalization().getCategoryName("main")
        )
    );

    public static final HiddenItemGroup STUFF = new HiddenItemGroup(
        Bump.createKey("bump_stuff"),
        new CustomItemStack(
            Material.NETHER_STAR,
            Bump.getLocalization().getCategoryName("stuff")
        )
    );
    public static final HiddenItemGroup FOOD = new HiddenItemGroup(
        Bump.createKey("bump_food"),
        new CustomItemStack(
            Material.BREAD,
            Bump.getLocalization().getCategoryName("food")
        )
    );
    public static final HiddenItemGroup MACHINE = new HiddenItemGroup(
        Bump.createKey("bump_machine"),
        new CustomItemStack(
            Material.ANVIL,
            Bump.getLocalization().getCategoryName("machine")
        )
    );
    public static final HiddenItemGroup TOOL = new HiddenItemGroup(
        Bump.createKey("bump_tool"),
        new CustomItemStack(
            Material.DIAMOND_PICKAXE,
            Bump.getLocalization().getCategoryName("tool")
        )
    );
    public static final HiddenItemGroup ARMOR = new HiddenItemGroup(
        Bump.createKey("bump_armor"),
        new CustomItemStack(
            Material.DIAMOND,
            Bump.getLocalization().getCategoryName("armor")
        )
    );
    public static final HiddenItemGroup WEAPON = new HiddenItemGroup(
        Bump.createKey("bump_weapon"),
        new CustomItemStack(
            Material.IRON_SWORD,
            Bump.getLocalization().getCategoryName("weapon")
        )
    );

    static {
        final Bump plugin = Bump.getInstance();

        MAIN.addItemGroup(STUFF);
        MAIN.addItemGroup(FOOD);
        MAIN.addItemGroup(MACHINE);
        MAIN.addItemGroup(TOOL);
        MAIN.addItemGroup(ARMOR);
        MAIN.addItemGroup(WEAPON);

        MAIN.register(plugin);

        STUFF.register(plugin);
        FOOD.register(plugin);
        MACHINE.register(plugin);
        TOOL.register(plugin);
        ARMOR.register(plugin);
        WEAPON.register(plugin);
    }
}
