package io.github.slimefunguguproject.bump.implementation.items.tools;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.api.appraise.AppraiseType;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.implementation.groups.BumpItemGroups;
import io.github.slimefunguguproject.bump.utils.AppraiseUtils;
import io.github.slimefunguguproject.bump.utils.GuiItems;
import io.github.slimefunguguproject.bump.utils.ValidateUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.LimitedUseItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;

import net.guizhanss.guizhanlib.minecraft.utils.InventoryUtil;

/**
 * An {@link AppraisalPaper quality identifier} can mark available {@link ItemStack items}
 * as appraisable.
 *
 * @author ybw0014
 */
@SuppressWarnings("deprecation")
public class AppraisalPaper extends LimitedUseItem {

    public static final int MAX_USES = 5;

    // gui
    private static final int[] BACKGROUND_SLOT = {
        0, 8, 9, 17, 18, 22, 26
    };
    private static final int[] INPUT_BORDER = {
        1, 2, 3, 10, 12, 19, 20, 21
    };
    private static final int[] OUTPUT_BORDER = {
        5, 6, 7, 14, 16, 23, 24, 25
    };
    private static final int INFO_SLOT = 4;
    private static final int INPUT_SLOT = 11;
    private static final int APPRAISE_BUTTON = 13;
    private static final int OUTPUT_SLOT = 15;

    @ParametersAreNonnullByDefault
    public AppraisalPaper(SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(BumpItemGroups.TOOL, item, recipeType, recipe);

        setMaxUseCount(MAX_USES);
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

            if (paperItemStack.getAmount() > 1) {
                Bump.getLocalization().sendMessage(p, "stacked");
                return;
            }

            // Open menu
            ChestMenu menu = new ChestMenu(paperItemStack.getItemMeta().getDisplayName());
            createMenu(p, menu, paperItemStack);
            menu.open(p);
        };
    }

    @ParametersAreNonnullByDefault
    private void createMenu(Player p, ChestMenu menu, ItemStack paperItemStack) {
        menu.setPlayerInventoryClickable(true);

        // Setup menu
        for (int i : BACKGROUND_SLOT) {
            menu.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }
        for (int i : INPUT_BORDER) {
            menu.addItem(i, ChestMenuUtils.getInputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
        }
        for (int i : OUTPUT_BORDER) {
            menu.addItem(i, ChestMenuUtils.getOutputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
        }

        // Add status
        menu.addItem(INFO_SLOT, getUsesLeftItem(getUsesLeft(paperItemStack)), ChestMenuUtils.getEmptyClickHandler());

        // Add menu close handler
        menu.addMenuCloseHandler(player -> {
            InventoryUtil.push(p, menu.getItemInSlot(INPUT_SLOT));
            InventoryUtil.push(p, menu.getItemInSlot(OUTPUT_SLOT));
        });

        // Block Appraisal paper click
        menu.addPlayerInventoryClickHandler((player, slot, item, action) -> {
            SlimefunItem sfItem = SlimefunItem.getByItem(item);
            return !(sfItem instanceof AppraisalPaper);
        });

        // Add appraise button handler
        menu.addItem(APPRAISE_BUTTON, GuiItems.APPRAISE_PAPER, getAppraiseButtonHandler(p, menu, paperItemStack));
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    private ChestMenu.MenuClickHandler getAppraiseButtonHandler(Player p, ChestMenu menu, ItemStack paperItemStack) {
        return (player, slot, item, action) -> {
            // Check input slot
            ItemStack input = menu.getItemInSlot(INPUT_SLOT);
            SlimefunItem sfItem = SlimefunItem.getByItem(input);

            if (!ValidateUtils.noAirItem(input)) {
                Bump.getLocalization().sendMessage(p, "no-input");
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F);
                return false;
            }

            // Check output slot
            if (menu.getItemInSlot(OUTPUT_SLOT) != null) {
                Bump.getLocalization().sendMessage(p, "output-no-space");
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F);
                return false;
            }

            // validate item
            if (isValidItem(input)) {
                // item can be marked appraisable
                ItemStack output = input.clone();
                AppraiseUtils.setAppraisable(output);
                menu.replaceExistingItem(INPUT_SLOT, null);
                menu.replaceExistingItem(OUTPUT_SLOT, output);

                damageItem(p, paperItemStack);

                /*
                 * The paper is used up, should close the gui.
                 * Otherwise, update the status slot.
                 */
                if (paperItemStack.getType() == Material.AIR) {
                    p.closeInventory();
                } else {
                    menu.replaceExistingItem(INFO_SLOT, getUsesLeftItem(getUsesLeft(paperItemStack)));

                    // play sound only if appraisal paper is not broken
                    p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, 1.0F, 1.0F);
                }

                Bump.getLocalization().sendMessage(p, "tool.appraisal_paper.success");
            } else {
                Bump.getLocalization().sendMessage(p, "tool.appraisal_paper.invalid");
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F);
            }

            return false;
        };
    }

    /**
     * Validate the item. The item that can be marked appraisable
     * should meet these requirements:<br>
     * - has not been appraised yet<br>
     * - has not been marked appraisable yet<br>
     *
     * @param itemStack The {@link ItemStack} to be validated.
     *
     * @return If the {@link ItemStack} is applicable to appraisal paper.
     */
    private boolean isValidItem(@Nonnull ItemStack itemStack) {
        final Set<AppraiseType> types = Bump.getRegistry().getAppraiseTypes();
        // find any match type
        for (AppraiseType type : types) {
            if (type.isValidItem(itemStack)
                && !AppraiseUtils.isAppraised(itemStack)
                && !AppraiseUtils.isAppraisable(itemStack)
            ) {
                return true;
            }
        }
        return false;
    }

    private int getUsesLeft(@Nonnull ItemStack itemStack) {
        return PersistentDataAPI.getInt(itemStack.getItemMeta(), getStorageKey(), getMaxUseCount());
    }

    @Nonnull
    private ItemStack getUsesLeftItem(int usesLeft) {
        return new CustomItemStack(
            Material.LIME_STAINED_GLASS_PANE,
            LoreBuilder.usesLeft(usesLeft)
        );
    }
}
