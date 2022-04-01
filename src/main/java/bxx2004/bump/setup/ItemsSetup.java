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

        new UnplaceableBlock(
            BumpItemGroups.STUFF,
            BumpItems.CPU,
            RecipeType.COMPRESSOR,
            new ItemStack[] {
                BumpItems.OLD_CPU
            }
        ).register(plugin);

        new UnplaceableBlock(
            BumpItemGroups.STUFF,
            BumpItems.SOUL_PAPER,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[] {
                SlimefunItems.MAGIC_LUMP_1, SlimefunItems.ESSENCE_OF_AFTERLIFE, SlimefunItems.MAGIC_LUMP_1,
                SlimefunItems.SOULBOUND_RUNE, SlimefunItems.SOULBOUND_RUNE, SlimefunItems.SOULBOUND_RUNE,
                SlimefunItems.MAGIC_LUMP_1, SlimefunItems.ESSENCE_OF_AFTERLIFE, SlimefunItems.MAGIC_LUMP_1
            }
        ).register(plugin);

        new UnplaceableBlock(
            BumpItemGroups.STUFF,
            BumpItems.KSF_STUFF,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.SALT, SlimefunItems.SALT, SlimefunItems.SALT,
                SlimefunItems.CARROT_FERTILIZER, SlimefunItems.CARROT_FERTILIZER, SlimefunItems.CARROT_FERTILIZER,
                SlimefunItems.SALT, SlimefunItems.SALT, SlimefunItems.SALT
            }
        ).register(plugin);

        new UnplaceableBlock(
            BumpItemGroups.STUFF,
            BumpItems.WATER_SUGAR,
            RecipeType.PRESSURE_CHAMBER,
            new ItemStack[] {
                SlimefunItems.MAGIC_SUGAR
            }
        ).register(plugin);

        new UnplaceableBlock(
            BumpItemGroups.STUFF,
            BumpItems.PEACH_WOOD,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.ACACIA_WOOD), new ItemStack(Material.BIRCH_WOOD), new ItemStack(Material.DARK_OAK_WOOD),
                null, null, null,
                null, null, null
            }
        ).register(plugin);

        new UnplaceableBlock(
            BumpItemGroups.STUFF,
            BumpItems.UPDATE_POWER,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[] {
                SlimefunItems.POWER_CRYSTAL, SlimefunItems.LAVA_CRYSTAL, SlimefunItems.POWER_CRYSTAL,
                SlimefunItems.LAVA_CRYSTAL, BumpItems.CPU, SlimefunItems.LAVA_CRYSTAL,
                SlimefunItems.GOLD_24K, SlimefunItems.GOLD_24K, SlimefunItems.GOLD_24K
            }
        ).register(plugin);
        // endregion stuff
    }
}
