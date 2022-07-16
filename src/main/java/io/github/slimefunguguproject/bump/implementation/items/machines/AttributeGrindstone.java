package io.github.slimefunguguproject.bump.implementation.items.machines;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.implementation.BumpItems;
import io.github.slimefunguguproject.bump.implementation.setup.BumpItemGroups;
import io.github.slimefunguguproject.bump.utils.AppraiseUtils;
import io.github.slimefunguguproject.bump.utils.GuiItems;
import io.github.slimefunguguproject.bump.utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

/**
 * The {@link AttributeGrindstone} can purge the appraisal result from
 * appraised equipment.
 *
 * @author ybw0014
 */
public class AttributeGrindstone extends SimpleMenuBlock {

    // energy
    public static final int ENERGY_CONSUMPTION = 1314;

    public AttributeGrindstone() {
        super(BumpItemGroups.MACHINE, BumpItems.ATTRIBUTE_GRINDSTONE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            SlimefunItems.ELECTRO_MAGNET, BumpItems.APPRAISAL, SlimefunItems.ELECTRO_MAGNET,
            BumpItems.MECHA_GEAR, BumpItems.CPU, BumpItems.MECHA_GEAR,
            BumpItems.UPDATE_POWER, BumpItems.ZONGZI, BumpItems.UPDATE_POWER
        });
    }

    @Override
    @Nonnull
    public ItemStack getOperationSlotItem() {
        return GuiItems.GRIND_BUTTON;
    }

    @ParametersAreNonnullByDefault
    @Override
    protected void onOperate(BlockMenu menu, Block b, Player p, ClickAction action) {
        grind(menu, p);
    }

    private void grind(@Nonnull BlockMenu blockMenu, @Nonnull Player p) {
        ItemStack item = blockMenu.getItemInSlot(getInputSlot());

        // null check
        if (!Utils.validateItem(item)) {
            Bump.getLocalization().sendMessage(p, "no-input");
            return;
        }

        // check if input item is appraised
        if (!AppraiseUtils.isAppraised(item)) {
            Bump.getLocalization().sendMessage(p, "machine.attribute-grindstone.invalid");
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

        if (Bump.getAppraiseManager().clearAttributes(output)) {
            blockMenu.replaceExistingItem(getInputSlot(), null);
            blockMenu.pushItem(output, getOutputSlot());

            setCharge(blockMenu.getLocation(), 0);
            Bump.getLocalization().sendMessage(p, "machine.attribute-grindstone.success");
        }
    }

    @Override
    public int getCapacity() {
        return ENERGY_CONSUMPTION;
    }
}
