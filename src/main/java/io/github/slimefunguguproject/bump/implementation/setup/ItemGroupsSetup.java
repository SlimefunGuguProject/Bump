package io.github.slimefunguguproject.bump.implementation.setup;

import javax.annotation.Nonnull;

import io.github.slimefunguguproject.bump.implementation.groups.BumpItemGroups;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;

import lombok.experimental.UtilityClass;

/**
 * This class setup all item groups.
 *
 * @author ybw0014
 */
@UtilityClass
public final class ItemGroupsSetup {
    public static void setup(@Nonnull SlimefunAddon addon) {
        BumpItemGroups.MAIN.addItemGroup(BumpItemGroups.STUFF);
        BumpItemGroups.MAIN.addItemGroup(BumpItemGroups.FOOD);
        BumpItemGroups.MAIN.addItemGroup(BumpItemGroups.MACHINE);
        BumpItemGroups.MAIN.addItemGroup(BumpItemGroups.TOOL);
        BumpItemGroups.MAIN.addItemGroup(BumpItemGroups.ARMOR);
        BumpItemGroups.MAIN.addItemGroup(BumpItemGroups.WEAPON);
        BumpItemGroups.MAIN.addItemGroup(BumpItemGroups.APPRAISE_INFO);

        BumpItemGroups.MAIN.register(addon);

        BumpItemGroups.STUFF.register(addon);
        BumpItemGroups.FOOD.register(addon);
        BumpItemGroups.MACHINE.register(addon);
        BumpItemGroups.TOOL.register(addon);
        BumpItemGroups.ARMOR.register(addon);
        BumpItemGroups.WEAPON.register(addon);
        BumpItemGroups.APPRAISE_INFO.register(addon);
        BumpItemGroups.LEGACY.register(addon);
    }
}
