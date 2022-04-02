package bxx2004.bump.slimefun.items.machine;

import bxx2004.bump.Bump;
import bxx2004.bump.slimefun.BumpItemGroups;
import bxx2004.bump.slimefun.BumpItems;
import bxx2004.bump.util.GuiItems;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import net.guizhanss.guizhanlib.slimefun.machines.MenuBlock;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class Appraisal extends MenuBlock {

    private static final int[] BACKGROUND = {
        0, 4, 8, 9, 17, 18, 22, 26
    };
    private static final int[] INPUT_BACKGROUND = {
        1, 2, 3, 10, 12, 19, 20, 21
    };
    private static final int[] OUTPUT_BACKGROUND = {
        5, 6, 7, 14, 16, 23, 24, 25
    };
    private static final int INPUT_SLOT = 11;
    private static final int APPRAISE_BUTTON = 13;
    private static final int OUTPUT_SLOT = 15;

    public Appraisal() {
        super(BumpItemGroups.MACHINE, BumpItems.APPRAISAL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
            SlimefunItems.BATTERY, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.BATTERY,
            BumpItems.MECHA_GEAR, BumpItems.CPU, BumpItems.MECHA_GEAR,
            SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.COOLING_UNIT, SlimefunItems.ADVANCED_CIRCUIT_BOARD
        });
    }

    @Override
    protected void setup(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(ChestMenuUtils.getBackground(), BACKGROUND);
        blockMenuPreset.drawBackground(ChestMenuUtils.getInputSlotTexture(), INPUT_BACKGROUND);
        blockMenuPreset.drawBackground(ChestMenuUtils.getOutputSlotTexture(), OUTPUT_BACKGROUND);

        blockMenuPreset.addItem(APPRAISE_BUTTON, GuiItems.APPRAISE_BUTTON);

        blockMenuPreset.addMenuClickHandler(APPRAISE_BUTTON, ChestMenuUtils.getEmptyClickHandler());
    }

    @Override
    protected int[] getInputSlots() {
        return new int[0];
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[0];
    }

    @Override
    protected void onBreak(@Nonnull BlockBreakEvent e, @Nonnull BlockMenu menu) {
        super.onBreak(e, menu);
        Location location = menu.getLocation();
        menu.dropItems(location, INPUT_SLOT);
        menu.dropItems(location, OUTPUT_SLOT);
    }

    @Override
    protected void onNewInstance(@Nonnull BlockMenu blockMenu, @Nonnull Block b) {
        super.onNewInstance(blockMenu, b);
        blockMenu.addMenuClickHandler(APPRAISE_BUTTON, (player, i, itemStack, clickAction) -> {
            appraise(blockMenu, player);
            return false;
        });
    }

    private void appraise(@Nonnull BlockMenu blockMenu, @Nonnull Player p) {
        ItemStack item = blockMenu.getItemInSlot(INPUT_SLOT);

        // null check
        if (item == null) {
            Bump.getLocalization().sendMessage(p, "no_input");
            return;
        }

        // check output slot
        if (blockMenu.getItemInSlot(OUTPUT_SLOT) != null) {
            Bump.getLocalization().sendMessage(p, "output_no_space");
            return;
        }

    }
}
