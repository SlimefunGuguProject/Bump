package io.github.slimefunguguproject.bump.implementation.items.legacy;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.implementation.BumpItems;
import io.github.slimefunguguproject.bump.implementation.groups.BumpItemGroups;
import io.github.slimefunguguproject.bump.implementation.items.tools.QualityIdentifier;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.LimitedUseItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;

/**
 * This class is used to upgrade legacy appraisal papers in player's inventory
 * to new ones.
 *
 * @author ybw0014
 * @deprecated This represents legacy quality identifiers, and will be removed soon.
 */
@Deprecated
public final class LegacyAppraisalPaper extends LimitedUseItem {

    @ParametersAreNonnullByDefault
    public LegacyAppraisalPaper(SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(BumpItemGroups.HIDDEN, item, recipeType, recipe);

        setMaxUseCount(QualityIdentifier.MAX_USES);
        addItemHandler(getItemHandler());
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();

            Player p = e.getPlayer();
            ItemStack paperItemStack = e.getItem();

            if (paperItemStack.getType() == Material.AIR) {
                return;
            }

            int usesLeft = getUsesLeft(paperItemStack);
            int amount = paperItemStack.getAmount();

            ItemStack newItem = BumpItems.QUALITY_IDENTIFIER.clone();
            newItem.setAmount(amount);

            ItemMeta newMeta = newItem.getItemMeta();
            PersistentDataAPI.setInt(newMeta, getStorageKey(), usesLeft);
            newItem.setItemMeta(newMeta);

            p.getInventory().setItem(e.getHand(), newItem);

            Bump.getLocalization().sendMessage(p, "tool.appraisal_paper.legacy");
        };
    }

    private int getUsesLeft(@Nonnull ItemStack itemStack) {
        return PersistentDataAPI.getInt(itemStack.getItemMeta(), getStorageKey(), getMaxUseCount());
    }
}
