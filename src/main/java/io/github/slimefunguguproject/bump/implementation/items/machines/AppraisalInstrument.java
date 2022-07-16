package io.github.slimefunguguproject.bump.implementation.items.machines;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.implementation.BumpItems;
import io.github.slimefunguguproject.bump.implementation.items.RandomEquipment;
import io.github.slimefunguguproject.bump.implementation.items.tools.AppraisalPaper;
import io.github.slimefunguguproject.bump.implementation.setup.BumpItemGroups;
import io.github.slimefunguguproject.bump.utils.AppraiseUtils;
import io.github.slimefunguguproject.bump.utils.GuiItems;
import io.github.slimefunguguproject.bump.utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

/**
 * This implements the {@link AppraisalInstrument appraisal instrument}.
 * <p>
 * It will cost energy and appraise any equipment with appraisable tag.
 *
 * @author ybw0014
 * @see AppraisalPaper
 */
public class AppraisalInstrument extends SimpleMenuBlock {

    // energy
    public static final int ENERGY_CONSUMPTION = 114514;

    public AppraisalInstrument() {
        super(BumpItemGroups.MACHINE, BumpItems.APPRAISAL, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            SlimefunItems.BATTERY, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.BATTERY,
            BumpItems.MECHA_GEAR, BumpItems.CPU, BumpItems.MECHA_GEAR,
            SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.COOLING_UNIT, SlimefunItems.ADVANCED_CIRCUIT_BOARD
        });
    }

    @Override
    @Nonnull
    public ItemStack getOperationSlotItem() {
        return GuiItems.APPRAISE_BUTTON;
    }

    @ParametersAreNonnullByDefault
    @Override
    protected void onOperate(BlockMenu menu, Block b, Player p, ClickAction action) {
        appraise(menu, p);
    }

    private void appraise(@Nonnull BlockMenu blockMenu, @Nonnull Player p) {
        ItemStack item = blockMenu.getItemInSlot(getInputSlot());

        // null check
        if (!Utils.validateItem(item)) {
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
        if (blockMenu.getItemInSlot(getOutputSlot()) != null) {
            Bump.getLocalization().sendMessage(p, "output-no-space");
            return;
        }

        // check energy
        int charge = getCharge(blockMenu.getLocation());
        if (charge < ENERGY_CONSUMPTION) {
            Bump.getLocalization().sendMessage(p, "not-enough-power");
            return;
        }

        ItemStack output = item.clone();

        if (Bump.getAppraiseManager().appraiseItem(output)) {
            blockMenu.replaceExistingItem(getInputSlot(), null);
            blockMenu.pushItem(output, getOutputSlot());

            setCharge(blockMenu.getLocation(), 0);
            Bump.getLocalization().sendMessage(p, "machine.appraisal.success");
        }
    }

    private boolean validate(@Nonnull ItemStack itemStack) {
        SlimefunItem sfItem = SlimefunItem.getByItem(itemStack);

        return sfItem instanceof RandomEquipment || AppraiseUtils.isAppraisable(itemStack);
    }

    @Override
    public int getCapacity() {
        return ENERGY_CONSUMPTION;
    }
}
