package bxx2004.bump.util;

import bxx2004.bump.Bump;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class GuiItems {
    private GuiItems() {}

    public static ItemStack APPRAISE_BUTTON = new CustomItemStack(
        Material.NAME_TAG,
        Bump.getLocalization().getString("gui.appraise.name"),
        Bump.getLocalization().getStringArray("gui.appraise.lore")
    );

    public static ItemStack APPRAISE_PAPER = new CustomItemStack(
        Material.PAPER,
        Bump.getLocalization().getString("gui.appraisal_paper.name"),
        Bump.getLocalization().getStringArray("gui.appraisal_paper.lore")
    );
}
