package io.github.slimefunguguproject.bump.implementation.items.tools;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.api.appraise.AppraiseTypes;
import io.github.slimefunguguproject.bump.implementation.groups.BumpItemGroups;
import io.github.slimefunguguproject.bump.utils.AppraiseUtils;
import io.github.slimefunguguproject.bump.utils.GuiItems;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.LimitedUseItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import io.github.thebusybiscuit.slimefun4.utils.PatternUtils;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;

import net.guizhanss.guizhanlib.minecraft.utils.ChatUtil;
import net.guizhanss.guizhanlib.minecraft.utils.InventoryUtil;

/**
 * An {@link AppraisalPaper quality identifier} can mark available {@link ItemStack items}
 * as appraisable. It has limited uses now.
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

    private final AppraiseTypes appraiseTypes;

    @ParametersAreNonnullByDefault
    public AppraisalPaper(SlimefunItemStack item, AppraiseTypes appraiseTypes, RecipeType recipeType, ItemStack[] recipe) {
        super(BumpItemGroups.TOOL, item, recipeType, recipe);

        this.appraiseTypes = appraiseTypes;

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

            // Check old item
            renewOldItem(paperItemStack);

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

            if (input == null) {
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

            /*
                Validate the item. The item that can be marked appraisable
                should meet these requirements:
                - matches the appraisal paper type
                - has not been appraised yet
                - has not been marked appraisable yet
             */
            if (appraiseTypes.isValidMaterial(input.getType(), sfItem != null)) {
                if (!AppraiseUtils.isAppraised(input)
                    && !AppraiseUtils.isAppraisable(input)) {
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
            } else {
                Bump.getLocalization().sendMessage(p, "tool.appraisal_paper.mismatch");
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F);
            }

            return false;
        };
    }

    /**
     * Check old
     */
    private void renewOldItem(@Nonnull ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = meta.getLore();

        // Check line of uses
        for (String line : lore) {
            if (PatternUtils.USES_LEFT_LORE.matcher(line).matches()) {
                return;
            }
        }

        // No uses line, append
        lore.add(ChatUtil.color(LoreBuilder.usesLeft(getMaxUseCount())));

        meta.setLore(lore);
        itemStack.setItemMeta(meta);
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
