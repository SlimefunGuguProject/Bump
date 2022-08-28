package io.github.slimefunguguproject.bump.implementation.items.machines;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.api.appraise.AppraiseResult;
import io.github.slimefunguguproject.bump.api.appraise.AppraiseType;
import io.github.slimefunguguproject.bump.core.services.sounds.BumpSound;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.implementation.BumpItems;
import io.github.slimefunguguproject.bump.implementation.groups.BumpItemGroups;
import io.github.slimefunguguproject.bump.implementation.items.RandomEquipment;
import io.github.slimefunguguproject.bump.implementation.items.tools.QualityIdentifier;
import io.github.slimefunguguproject.bump.utils.AppraiseUtils;
import io.github.slimefunguguproject.bump.utils.GuiItems;
import io.github.slimefunguguproject.bump.utils.ValidateUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

/**
 * This implements the {@link AppraisalInstrument}.
 * <p>
 * It will cost energy and appraise any equipment with appraisable tag.
 *
 * @author ybw0014
 * @see QualityIdentifier
 */
public final class AppraisalInstrument extends SimpleMenuBlock {

    // energy
    public static final int ENERGY_CONSUMPTION = 114514;

    private static final int APPRAISE_TYPE_SLOT = 4;

    private static final String APPRAISE_TYPE_KEY = "appraise_type";

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

    @Override
    public int getCapacity() {
        return ENERGY_CONSUMPTION;
    }

    @ParametersAreNonnullByDefault
    @Override
    protected void onNewInstance(BlockMenu menu, Block b) {
        super.onNewInstance(menu, b);
        updateSelector(menu, b.getLocation());
    }

    @ParametersAreNonnullByDefault
    @Override
    protected void onOperate(BlockMenu menu, Block b, Player p, ClickAction action) {
        appraise(menu, p);
    }

    @ParametersAreNonnullByDefault
    private void openSelector(Player p, BlockMenu blockMenu, Location l) {
        new AppraisalInstrumentSelector(type -> {
            BlockStorage.addBlockInfo(l, APPRAISE_TYPE_KEY, type.getKey().toString());
            updateSelector(blockMenu, l);
            BumpSound.APPRAISE_TYPE_SELECT.playFor(p);
            blockMenu.open(p);
        }, () -> blockMenu.open(p)).open(p);
    }

    @ParametersAreNonnullByDefault
    private void appraise(BlockMenu blockMenu, Player p) {
        ItemStack item = blockMenu.getItemInSlot(getInputSlot());

        // null check
        if (!ValidateUtils.noAirItem(item)) {
            Bump.getLocalization().sendMessage(p, "no-input");
            BumpSound.APPRAISAL_INSTRUMENT_FAIL.playFor(p);
            return;
        }

        // validate input
        if (!validate(item)) {
            Bump.getLocalization().sendMessage(p, "machine.appraisal.invalid");
            BumpSound.APPRAISAL_INSTRUMENT_FAIL.playFor(p);
            return;
        }

        // check if input item is already appraised
        if (AppraiseUtils.isAppraised(item)) {
            Bump.getLocalization().sendMessage(p, "machine.appraisal.appraised");
            BumpSound.APPRAISAL_INSTRUMENT_FAIL.playFor(p);
            return;
        }

        // check output slot
        if (blockMenu.getItemInSlot(getOutputSlot()) != null) {
            Bump.getLocalization().sendMessage(p, "output-no-space");
            BumpSound.APPRAISAL_INSTRUMENT_FAIL.playFor(p);
            return;
        }

        // check energy
        int charge = getCharge(blockMenu.getLocation());
        if (charge < ENERGY_CONSUMPTION) {
            Bump.getLocalization().sendMessage(p, "not-enough-power");
            BumpSound.APPRAISAL_INSTRUMENT_FAIL.playFor(p);
            return;
        }

        // Check current appraise type
        AppraiseType type = getCurrentType(blockMenu.getLocation());
        if (!type.hasPermission(p)) {
            Bump.getLocalization().sendMessage(p, "no-permission");
            BumpSound.APPRAISAL_INSTRUMENT_FAIL.playFor(p);
            return;
        }
        if (!type.isValidItem(item)) {
            Bump.getLocalization().sendMessage(p, "machine.appraisal.not-accepted");
            BumpSound.APPRAISAL_INSTRUMENT_FAIL.playFor(p);
            return;
        }

        ItemStack output = item.clone();
        AppraiseResult result = type.appraise();

        result.apply(output);

        blockMenu.replaceExistingItem(getInputSlot(), null);
        blockMenu.pushItem(output, getOutputSlot());

        setCharge(blockMenu.getLocation(), 0);
        Bump.getLocalization().sendMessage(p, "machine.appraisal.success");
        BumpSound.APPRAISAL_INSTRUMENT_SUCCEED.playFor(p);
    }

    private boolean validate(@Nonnull ItemStack itemStack) {
        SlimefunItem sfItem = SlimefunItem.getByItem(itemStack);

        return sfItem instanceof RandomEquipment || AppraiseUtils.isAppraisable(itemStack);
    }

    @ParametersAreNonnullByDefault
    private void updateSelector(BlockMenu menu, Location l) {
        AppraiseType type = getCurrentType(l);
        menu.replaceExistingItem(APPRAISE_TYPE_SLOT, GuiItems.appraiseTypeSelector(type));
        menu.addMenuClickHandler(APPRAISE_TYPE_SLOT, (player, slot, itemStack, clickAction) -> {
            openSelector(player, menu, l);
            return false;
        });
    }

    @Nonnull
    private AppraiseType getCurrentType(@Nonnull Location loc) {
        String current = BlockStorage.getLocationInfo(loc, APPRAISE_TYPE_KEY);
        AppraiseType type = null;
        if (current != null) {
            NamespacedKey key = NamespacedKey.fromString(current, Bump.getInstance());
            if (key != null) {
                type = AppraiseType.getByKey(key);
            }
        }

        if (type == null) {
            type = Bump.getRegistry().getAppraiseTypes().iterator().next();
        }
        return type;
    }
}
