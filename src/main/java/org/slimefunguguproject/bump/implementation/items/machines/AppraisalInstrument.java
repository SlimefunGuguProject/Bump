package org.slimefunguguproject.bump.implementation.items.machines;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import net.guizhanss.guizhanlib.slimefun.machines.MenuBlock;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.slimefunguguproject.bump.implementation.Bump;
import org.slimefunguguproject.bump.implementation.BumpItems;
import org.slimefunguguproject.bump.implementation.setup.BumpItemGroups;
import org.slimefunguguproject.bump.utils.AppraiseUtils;
import org.slimefunguguproject.bump.utils.GuiItems;

import javax.annotation.Nonnull;

/**
 * This implements the {@link AppraisalInstrument appraisal instrument}.
 *
 * It will cost energy and appraise any equipment with appraisable tag.
 *
 * @author ybw0014
 *
 * @see org.slimefunguguproject.bump.implementation.items.tools.AppraisalPaper
 */
public class AppraisalInstrument extends MenuBlock implements EnergyNetComponent {

    // gui
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

    // energy
    private static final int ENERGY_CONSUMPTION = 114514;

    public AppraisalInstrument() {
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
            Bump.getLocalization().sendMessage(p, "no-input");
            return;
        }

        // validate input
        if (!validate(item)) {
            Bump.getLocalization().sendMessage(p, "machine.appraisal.invalid");
            return;
        }

        // check if input item is already appraised
        if (AppraiseUtils.isAppraised(item)) {
            Bump.getLocalization().sendMessage(p, "machine.appraisal.appraised");
            return;
        }

        // check output slot
        if (blockMenu.getItemInSlot(OUTPUT_SLOT) != null) {
            Bump.getLocalization().sendMessage(p, "output-no-space");
            return;
        }

        // check energy
        int charge = getCharge(blockMenu.getLocation());
        if (charge < getEnergyConsumption()) {
            Bump.getLocalization().sendMessage(p, "not-enough-power");
            return;
        }

        ItemStack output = item.clone();

        if (Bump.getAppraiseManager().appraiseItem(output)) {
            blockMenu.replaceExistingItem(INPUT_SLOT, null);
            blockMenu.pushItem(output, OUTPUT_SLOT);

            setCharge(blockMenu.getLocation(), 0);
            Bump.getLocalization().sendMessage(p, "machine.appraisal.success");
        }
    }

    private boolean validate(ItemStack itemStack) {
        return AppraiseUtils.isAppraisable(itemStack)
            || SlimefunUtils.isItemSimilar(BumpItems.RANDOM_HELMET, itemStack, false)
            || SlimefunUtils.isItemSimilar(BumpItems.RANDOM_HORSE_ARMOR, itemStack, false)
            || SlimefunUtils.isItemSimilar(BumpItems.RANDOM_SWORD, itemStack, false);
    }

    public static int getEnergyConsumption() {
        return ENERGY_CONSUMPTION;
    }

    @Nonnull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return ENERGY_CONSUMPTION;
    }
}
