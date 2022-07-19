package io.github.slimefunguguproject.bump.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

import lombok.experimental.UtilityClass;

/**
 * Some GUI items.
 *
 * @author ybw0014
 */
@UtilityClass
public final class GuiItems {
    public static final ItemStack APPRAISE_BUTTON = new CustomItemStack(
        Material.NAME_TAG,
        Bump.getLocalization().getString("gui.appraise.name"),
        Bump.getLocalization().getStringArray("gui.appraise.lore")
    );
    public static final ItemStack APPRAISE_PAPER = new CustomItemStack(
        Material.PAPER,
        Bump.getLocalization().getString("gui.appraisal_paper.name"),
        Bump.getLocalization().getStringArray("gui.appraisal_paper.lore")
    );
    public static final ItemStack GRIND_BUTTON = new CustomItemStack(
        Material.GRINDSTONE,
        Bump.getLocalization().getString("gui.grind.name"),
        Bump.getLocalization().getStringArray("gui.grind.lore")
    );
}
