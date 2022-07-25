package io.github.slimefunguguproject.bump.implementation.items.machines;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

import net.guizhanss.guizhanlib.slimefun.machines.MenuBlock;

/**
 * A {@link MenuBlock} that has single input and output slot.
 * <p>
 * Consumes power when use this machine.
 *
 * @author ybw0014
 */
public abstract class SimpleMenuBlock extends MenuBlock implements EnergyNetComponent {

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
    private static final int OPERATION_SLOT = 13;
    private static final int OUTPUT_SLOT = 15;

    /**
     * Constructor.
     *
     * @param itemGroup  the {@link ItemGroup} of this {@link MenuBlock}
     * @param item       the {@link SlimefunItemStack} of this {@link MenuBlock}
     * @param recipeType the {@link RecipeType} of this {@link MenuBlock}
     * @param recipe     the recipe of this {@link MenuBlock}
     */
    @ParametersAreNonnullByDefault
    protected SimpleMenuBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Nonnull
    protected abstract ItemStack getOperationSlotItem();

    @Override
    protected void setup(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(ChestMenuUtils.getBackground(), BACKGROUND);
        blockMenuPreset.drawBackground(ChestMenuUtils.getInputSlotTexture(), INPUT_BACKGROUND);
        blockMenuPreset.drawBackground(ChestMenuUtils.getOutputSlotTexture(), OUTPUT_BACKGROUND);

        blockMenuPreset.addItem(OPERATION_SLOT, getOperationSlotItem());
        blockMenuPreset.addMenuClickHandler(OPERATION_SLOT, ChestMenuUtils.getEmptyClickHandler());
    }

    @Override
    protected void onBreak(@Nonnull BlockBreakEvent e, @Nonnull BlockMenu menu) {
        super.onBreak(e, menu);
        Location location = menu.getLocation();
        menu.dropItems(location, INPUT_SLOT);
        menu.dropItems(location, OUTPUT_SLOT);
    }

    @ParametersAreNonnullByDefault
    @Override
    protected final void onNewInstance(BlockMenu blockMenu, Block b) {
        super.onNewInstance(blockMenu, b);
        blockMenu.addMenuClickHandler(OPERATION_SLOT, (player, slot, itemStack, clickAction) -> {
            onOperate(blockMenu, b, player, clickAction);
            return false;
        });
    }

    @ParametersAreNonnullByDefault
    protected abstract void onOperate(BlockMenu menu, Block b, Player p, ClickAction action);

    @Nonnull
    @Override
    public final EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    protected final int getInputSlot() {
        return INPUT_SLOT;
    }

    protected final int getOutputSlot() {
        return OUTPUT_SLOT;
    }

    @Override
    protected final int[] getInputSlots() {
        return new int[] { INPUT_SLOT };
    }

    @Override
    protected final int[] getOutputSlots() {
        return new int[] { OUTPUT_SLOT };
    }
}
