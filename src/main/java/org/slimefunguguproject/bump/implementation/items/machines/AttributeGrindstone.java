package org.slimefunguguproject.bump.implementation.items.machines;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;

import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

import net.guizhanss.guizhanlib.slimefun.machines.MenuBlock;

import org.slimefunguguproject.bump.implementation.Bump;
import org.slimefunguguproject.bump.implementation.BumpItems;
import org.slimefunguguproject.bump.implementation.setup.BumpItemGroups;
import org.slimefunguguproject.bump.utils.AppraiseUtils;
import org.slimefunguguproject.bump.utils.GuiItems;
import org.slimefunguguproject.bump.utils.Utils;

/**
 * The {@link AttributeGrindstone} can purge the appraisal result from
 * appraised equipment.
 *
 * @author ybw0014
 */
public class AttributeGrindstone extends MenuBlock implements EnergyNetComponent {

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
    private static final int GRIND_BUTTON = 13;
    private static final int OUTPUT_SLOT = 15;

    // energy
    private static final int ENERGY_CONSUMPTION = 1314;

    public AttributeGrindstone() {
        super(BumpItemGroups.MACHINE, BumpItems.ATTRIBUTE_GRINDSTONE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
            SlimefunItems.ELECTRO_MAGNET, BumpItems.APPRAISAL, SlimefunItems.ELECTRO_MAGNET,
            BumpItems.MECHA_GEAR, BumpItems.CPU, BumpItems.MECHA_GEAR,
            BumpItems.UPDATE_POWER, BumpItems.ZONGZI, BumpItems.UPDATE_POWER
        });
    }

    public static int getEnergyConsumption() {
        return ENERGY_CONSUMPTION;
    }

    @Override
    protected void setup(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(ChestMenuUtils.getBackground(), BACKGROUND);
        blockMenuPreset.drawBackground(ChestMenuUtils.getInputSlotTexture(), INPUT_BACKGROUND);
        blockMenuPreset.drawBackground(ChestMenuUtils.getOutputSlotTexture(), OUTPUT_BACKGROUND);

        blockMenuPreset.addItem(GRIND_BUTTON, GuiItems.GRIND_BUTTON);
        blockMenuPreset.addMenuClickHandler(GRIND_BUTTON, ChestMenuUtils.getEmptyClickHandler());
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
        blockMenu.addMenuClickHandler(GRIND_BUTTON, (player, i, itemStack, clickAction) -> {
            grind(blockMenu, player);
            return false;
        });
    }

    private void grind(@Nonnull BlockMenu blockMenu, @Nonnull Player p) {
        ItemStack item = blockMenu.getItemInSlot(INPUT_SLOT);

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

        if (Bump.getAppraiseManager().clearAttributes(output)) {
            blockMenu.replaceExistingItem(INPUT_SLOT, null);
            blockMenu.pushItem(output, OUTPUT_SLOT);

            setCharge(blockMenu.getLocation(), 0);
            Bump.getLocalization().sendMessage(p, "machine.attribute-grindstone.success");
        }
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
