package bxx2004.bump.setup;

import bxx2004.bump.Bump;
import bxx2004.bump.slimefun.BumpItemGroups;
import bxx2004.bump.slimefun.BumpItems;
import bxx2004.bump.slimefun.BumpRecipeTypes;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class ItemsSetup {
    private ItemsSetup() {}

    public static void setup() {
        Bump plugin = Bump.getInstance();

        // region stuff
        new UnplaceableBlock(
            BumpItemGroups.STUFF,
            BumpItems.SUN_ENERGY,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[] {
                new ItemStack(Material.CHORUS_FLOWER), new ItemStack(Material.SUNFLOWER), new ItemStack(Material.CHORUS_FLOWER),
                null, null, null,
                null, null, null
            }
        ).register(plugin);

        new UnplaceableBlock(
            BumpItemGroups.STUFF,
            BumpItems.MECHA_GEAR,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                SlimefunItems.COPPER_WIRE, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD, null, null
            }
        ).register(plugin);

        new UnplaceableBlock(
            BumpItemGroups.STUFF,
            BumpItems.OLD_COIN,
            BumpRecipeTypes.GETGOLD_SPADE,
            new ItemStack[9]
        ).register(plugin);

        new UnplaceableBlock(
            BumpItemGroups.STUFF,
            BumpItems.MAKE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.BATTERY, SlimefunItems.BATTERY, SlimefunItems.BATTERY,
                SlimefunItems.COOLING_UNIT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.COOLING_UNIT,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD
            }
        ).register(plugin);

        new UnplaceableBlock(
            BumpItemGroups.STUFF,
            BumpItems.OLD_CPU,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE,
                SlimefunItems.COPPER_WIRE, BumpItems.MAKE, SlimefunItems.COPPER_WIRE,
                SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE
            }
        ).register(plugin);
        // endregion stuff
    }
}
