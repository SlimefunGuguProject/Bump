package io.github.slimefunguguproject.bump.implementation.groups;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

import dev.sefiraat.sefilib.slimefun.itemgroup.DummyItemGroup;
import dev.sefiraat.sefilib.slimefun.itemgroup.SimpleFlexGroup;
import lombok.experimental.UtilityClass;

/**
 * This class holds all {@link ItemGroup}s of Bump.
 *
 * @author ybw0014
 */
@UtilityClass
public final class BumpItemGroups {
    public static final SimpleFlexGroup MAIN = new SimpleFlexGroup(
        Bump.getInstance(),
        Bump.getLocalization().getCategoryName("main"),
        Bump.createKey("main"),
        new CustomItemStack(
            Material.DIAMOND,
            Bump.getLocalization().getCategoryName("main")
        )
    );

    public static final ItemStack WIKI = new CustomItemStack(
        Material.KNOWLEDGE_BOOK,
        Bump.getLocalization().getCategoryName("wiki"),
        "",
        Bump.getLocalization().getString("lores.click-to-open")
    );

    public static final ItemGroup STUFF = new DummyItemGroup(
        Bump.createKey("stuff"),
        new CustomItemStack(
            Material.NETHER_STAR,
            Bump.getLocalization().getCategoryName("stuff")
        )
    );

    public static final ItemGroup FOOD = new DummyItemGroup(
        Bump.createKey("food"),
        new CustomItemStack(
            Material.BREAD,
            Bump.getLocalization().getCategoryName("food")
        )
    );

    public static final ItemGroup MACHINE = new DummyItemGroup(
        Bump.createKey("machine"),
        new CustomItemStack(
            Material.ANVIL,
            Bump.getLocalization().getCategoryName("machine")
        )
    );

    public static final ItemGroup TOOL = new DummyItemGroup(
        Bump.createKey("tool"),
        new CustomItemStack(
            Material.DIAMOND_PICKAXE,
            Bump.getLocalization().getCategoryName("tool")
        )
    );

    public static final ItemGroup ARMOR = new DummyItemGroup(
        Bump.createKey("armor"),
        new CustomItemStack(
            Material.DIAMOND,
            Bump.getLocalization().getCategoryName("armor")
        )
    );

    public static final ItemGroup WEAPON = new DummyItemGroup(
        Bump.createKey("weapon"),
        new CustomItemStack(
            Material.IRON_SWORD,
            Bump.getLocalization().getCategoryName("weapon")
        )
    );

    public static final AppraiseInfoGroup APPRAISE_INFO = new AppraiseInfoGroup(
        Bump.getLocalization().getCategoryName("appraise_info"),
        Bump.createKey("appraise_info"),
        new CustomItemStack(
            Material.NAME_TAG,
            Bump.getLocalization().getCategoryName("appraise_info")
        )
    );

    public static final ItemGroup LEGACY = new DummyItemGroup(
        Bump.createKey("legacy"),
        new CustomItemStack(
            Material.BARRIER,
            "Never gonna give you up~"
        )
    );
}
