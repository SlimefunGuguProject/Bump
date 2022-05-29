package bxx2004.bump.slimefun.items.tool;

import bxx2004.bump.Bump;
import bxx2004.bump.slimefun.BumpItemGroups;
import bxx2004.bump.util.AppraiseUtils;
import bxx2004.bump.util.BumpTag;
import bxx2004.bump.util.GuiItems;
import bxx2004.bump.util.Utils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.Material;
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

    private final Type paperType;

    public AppraisalPaper(SlimefunItemStack item, Type type, RecipeType recipeType, ItemStack[] recipe) {
        super(BumpItemGroups.TOOL, item, recipeType, recipe);

        paperType = type;
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();

            Player p = e.getPlayer();
            ItemStack paperItemStack = e.getItem();

            ChestMenu menu = new ChestMenu(paperItemStack.getItemMeta().getDisplayName());
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
                return !(sfItem instanceof AppraisalPaper);
            });

            // Add appraise button handler
            menu.addItem(APPRAISE_BUTTON, GuiItems.APPRAISE_PAPER, (player, slot, item, action) -> {
                // Check input slot
                ItemStack input = menu.getItemInSlot(INPUT_SLOT);
                SlimefunItem sfItem = SlimefunItem.getByItem(input);

                if (input == null) {
                    Bump.getLocalization().sendMessage(p, "no-input");
                    return false;
                }

                // Check output slot
                if (menu.getItemInSlot(OUTPUT_SLOT) != null) {
                    Bump.getLocalization().sendMessage(p, "output-no-space");
                    return false;
                }

                /*
                    Validate the item. The item that can be marked appraisable
                    should meet these requirements:
                    - matches the appraisal paper type
                    - is a slimefun item
                    - has not been appraised yet
                    - has not been marked appraisable yet
                 */
                if (matchType(input.getType())){
                    if (sfItem != null
                        && !AppraiseUtils.isAppraised(input)
                        && !AppraiseUtils.isAppraisable(input)) {
                        // item can be marked appraisable
                        ItemStack output = input.clone();
                        AppraiseUtils.setAppraisable(output);
                        menu.replaceExistingItem(INPUT_SLOT, null);
                        menu.replaceExistingItem(OUTPUT_SLOT, output);

                        Bump.getLocalization().sendMessage(p, "tool.appraisal_paper.success");
                    } else {
                        Bump.getLocalization().sendMessage(p, "tool.appraisal_paper.invalid");
                    }
                } else {
                    Bump.getLocalization().sendMessage(p, "tool.appraisal_paper.mismatch");
                }

                return false;
            });

            menu.open(p);
        };
    }

    private boolean matchType(Material mat) {
        switch (this.paperType) {
            case WEAPON:
                return BumpTag.WEAPON.isTagged(mat);
            case ARMOR:
                return BumpTag.ARMOR.isTagged(mat);
            case HORSE_ARMOR:
                return BumpTag.HORSE_ARMOR.isTagged(mat);
            default:
                return false;
        }
    }

    public enum Type {
        WEAPON,
        ARMOR,
        HORSE_ARMOR
    }
}
