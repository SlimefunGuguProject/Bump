package io.github.slimefunguguproject.bump.utils;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.api.appraise.AppraiseType;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

import lombok.experimental.UtilityClass;

/**
 * Some GUI items.
 *
 * @author ybw0014
 */
@UtilityClass
@SuppressWarnings("ConstantConditions")
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

    @Nonnull
    public static ItemStack appraiseTypeSelector(@Nonnull AppraiseType type) {
        Preconditions.checkArgument(type != null, "Appraise type cannot be null");

        List<String> lore = AppraiseUtils.getDescriptionLore(type);
        lore.addAll(Bump.getLocalization().getStringList("gui.appraise_type_selector.lore"));

        return new CustomItemStack(
            Material.MAP,
            Bump.getLocalization().getString("gui.appraise_type_selector.name", type.getName()),
            lore
        );
    }
}
