package io.github.slimefunguguproject.bump.implementation.items.machines;

import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.bakedlibs.dough.items.CustomItemStack;
import io.github.slimefunguguproject.bump.api.appraise.AppraiseType;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.implementation.menus.AppraiseTypesMenu;
import io.github.slimefunguguproject.bump.utils.AppraiseUtils;

import lombok.NonNull;

/**
 * A selector menu that can be opened from {@link AppraisalInstrument}.
 *
 * @author ybw0014
 */
final class AppraisalInstrumentSelector extends AppraiseTypesMenu {

    public AppraisalInstrumentSelector(@NonNull Consumer<AppraiseType> successCallback, @NonNull Runnable backCallback) {
        super(Bump.getLocalization().getString("gui.appraise_type_selector_menu.title"), successCallback, backCallback);
    }

    @Nonnull
    @Override
    public ItemStack getDisplayItem(@Nonnull AppraiseType type) {
        List<String> lore = AppraiseUtils.getDescriptionLore(type);
        lore.addAll(Bump.getLocalization().getStringList("gui.appraise_type_selector_menu.lore"));

        return new CustomItemStack(
            Material.PAPER,
            Bump.getLocalization().getString("appraise_info.name", type.getName()),
            lore
        );
    }
}
