package bxx2004.bump.slimefun.items.tool;

import bxx2004.bump.Bump;
import bxx2004.bump.slimefun.BumpItemGroups;
import bxx2004.bump.util.AppraiseUtils;
import bxx2004.bump.util.GuiItems;
import bxx2004.bump.util.Utils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class AppraisalPaper extends SimpleSlimefunItem<ItemUseHandler> {

    // gui
    private static final int[] BACKGROUND_SLOT = {
        0, 4, 8, 9, 17, 18, 22, 26
    };
    private static final int[] INPUT_BORDER = {
        1, 2, 3, 10, 12, 19, 20, 21
    };
    private static final int[] OUTPUT_BORDER = {
        5, 6, 7, 14, 16, 23, 24, 25
    };
    private static final int INPUT_SLOT = 11;
    private static final int APPRAISE_BUTTON = 13;
    private static final int OUTPUT_SLOT = 15;

    public AppraisalPaper(SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(BumpItemGroups.TOOL, item, recipeType, recipe);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();

            Player p = e.getPlayer();
            SlimefunItemStack paperItemStack = (SlimefunItemStack) e.getItem();

            ChestMenu menu = new ChestMenu(paperItemStack.getDisplayName());
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

            // Add menu close handler
            menu.addMenuCloseHandler((player) -> {
                Utils.pushToPlayerInventory(p, menu.getItemInSlot(INPUT_SLOT));
                Utils.pushToPlayerInventory(p, menu.getItemInSlot(OUTPUT_SLOT));
            });

            // Block Appraisal paper click
            menu.addPlayerInventoryClickHandler((player, slot, item, action) -> {
                SlimefunItem sfItem = SlimefunItem.getByItem(item);
                return sfItem instanceof AppraisalPaper;
            });

            // Add appraise button handler
            menu.addItem(APPRAISE_BUTTON, GuiItems.APPRAISE_PAPER, (player, slot, item, action) -> {
                // Check input slot
                ItemStack input = menu.getItemInSlot(INPUT_SLOT);
                SlimefunItem sfItem = SlimefunItem.getByItem(input);

                if (input == null) {
                    Bump.getLocalization().sendMessage(p, "no-input");
                    return true;
                }

                // Check output slot
                if (menu.getItemInSlot(OUTPUT_SLOT) != null) {
                    Bump.getLocalization().sendMessage(p, "output-no-space");
                    return true;
                }

                // validate item
                if (AppraiseUtils.isAppraisableMaterial(input.getType())
                    && sfItem != null
                    && !AppraiseUtils.isAppraised(input)) {
                    // item can be marked appraisable
                    ItemStack output = input.clone();
                    AppraiseUtils.setAppraisable(output);
                    menu.replaceExistingItem(INPUT_SLOT, null);
                    menu.addItem(OUTPUT_SLOT, output);

                    Bump.getLocalization().sendMessage(p, "tool.appraisal_paper.success");
                } else {
                    Bump.getLocalization().sendMessage(p, "tool.appraisal_paper.invalid");
                }

                return true;
            });

            menu.open(p);
        };
    }
}
