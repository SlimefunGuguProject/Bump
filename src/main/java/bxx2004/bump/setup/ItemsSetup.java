package bxx2004.bump.setup;

import bxx2004.bump.Bump;
import bxx2004.bump.slimefun.BumpItemGroups;
import bxx2004.bump.slimefun.BumpItems;
import bxx2004.bump.slimefun.BumpRecipeTypes;
import bxx2004.bump.slimefun.items.food.Fangbianmian;
import bxx2004.bump.slimefun.items.food.Kele;
import bxx2004.bump.slimefun.items.food.Kouxiangtang;
import bxx2004.bump.slimefun.items.food.Latiao;
import bxx2004.bump.slimefun.items.food.Xuebi;
import bxx2004.bump.slimefun.items.machine.Appraisal;
import bxx2004.bump.slimefun.items.stuff.StuffItem;
import bxx2004.bump.slimefun.items.tool.AppraisalPaper;
import bxx2004.bump.slimefun.items.tool.GetgoldSpade;
import bxx2004.bump.slimefun.items.weapon.DevilSword;
import bxx2004.bump.slimefun.items.weapon.LightBow;
import bxx2004.bump.slimefun.items.weapon.SkyDevilSword;
import bxx2004.bump.slimefun.items.weapon.SkySword;
import bxx2004.bump.slimefun.items.weapon.SoulSword;
import bxx2004.bump.slimefun.items.weapon.WitherSkullBow;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class ItemsSetup {
    private ItemsSetup() {}

    public static void setup() {
        Bump plugin = Bump.getInstance();

        // region armor
        new SlimefunItem(
            BumpItemGroups.ARMOR,
            BumpItems.RANDOM_HELMET,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                BumpItems.OLD_COIN, null, BumpItems.OLD_COIN,
                BumpItems.OLD_COIN, BumpItems.MAKE, BumpItems.OLD_COIN,
                BumpItems.OLD_COIN, BumpItems.UPDATE_POWER, BumpItems.OLD_COIN
            }
        ).register(plugin);
        // endregion armor

        // region food
        new Xuebi().register(plugin);
        new Kele().register(plugin);
        new Fangbianmian().register(plugin);
        new Latiao().register(plugin);
        new Kouxiangtang().register(plugin);
        // endregion food

        // region machine
        new Appraisal().register(plugin);
        // endregion machine

        // region stuff
        new StuffItem(
            BumpItems.SUN_ENERGY,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[] {
                new ItemStack(Material.CHORUS_FLOWER), new ItemStack(Material.SUNFLOWER), new ItemStack(Material.CHORUS_FLOWER),
                null, null, null,
                null, null, null
            }
        ).register(plugin);

        new StuffItem(
            BumpItems.MECHA_GEAR,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                SlimefunItems.COPPER_WIRE, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD, null, null
            }
        ).register(plugin);

        new StuffItem(
            BumpItems.OLD_COIN,
            BumpRecipeTypes.GETGOLD_SPADE,
            new ItemStack[9]
        ).register(plugin);

        new StuffItem(
            BumpItems.MAKE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.BATTERY, SlimefunItems.BATTERY, SlimefunItems.BATTERY,
                SlimefunItems.COOLING_UNIT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.COOLING_UNIT,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD
            }
        ).register(plugin);

        new StuffItem(
            BumpItems.OLD_CPU,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE,
                SlimefunItems.COPPER_WIRE, BumpItems.MAKE, SlimefunItems.COPPER_WIRE,
                SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE
            }
        ).register(plugin);

        new StuffItem(
            BumpItems.CPU,
            RecipeType.COMPRESSOR,
            new ItemStack[] {
                BumpItems.OLD_CPU
            }
        ).register(plugin);

        new StuffItem(
            BumpItems.SOUL_PAPER,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[] {
                SlimefunItems.MAGIC_LUMP_1, SlimefunItems.ESSENCE_OF_AFTERLIFE, SlimefunItems.MAGIC_LUMP_1,
                SlimefunItems.SOULBOUND_RUNE, SlimefunItems.SOULBOUND_RUNE, SlimefunItems.SOULBOUND_RUNE,
                SlimefunItems.MAGIC_LUMP_1, SlimefunItems.ESSENCE_OF_AFTERLIFE, SlimefunItems.MAGIC_LUMP_1
            }
        ).register(plugin);

        new StuffItem(
            BumpItems.KSF_STUFF,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.SALT, SlimefunItems.SALT, SlimefunItems.SALT,
                SlimefunItems.CARROT_FERTILIZER, SlimefunItems.CARROT_FERTILIZER, SlimefunItems.CARROT_FERTILIZER,
                SlimefunItems.SALT, SlimefunItems.SALT, SlimefunItems.SALT
            }
        ).register(plugin);

        new StuffItem(
            BumpItems.WATER_SUGAR,
            RecipeType.PRESSURE_CHAMBER,
            new ItemStack[] {
                SlimefunItems.MAGIC_SUGAR
            }
        ).register(plugin);

        new StuffItem(
            BumpItems.PEACH_WOOD,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.ACACIA_WOOD), new ItemStack(Material.BIRCH_WOOD), new ItemStack(Material.DARK_OAK_WOOD),
                null, null, null,
                null, null, null
            }
        ).register(plugin);

        new StuffItem(
            BumpItems.UPDATE_POWER,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[] {
                SlimefunItems.POWER_CRYSTAL, SlimefunItems.LAVA_CRYSTAL, SlimefunItems.POWER_CRYSTAL,
                SlimefunItems.LAVA_CRYSTAL, BumpItems.CPU, SlimefunItems.LAVA_CRYSTAL,
                SlimefunItems.GOLD_24K, SlimefunItems.GOLD_24K, SlimefunItems.GOLD_24K
            }
        ).register(plugin);
        // endregion stuff

        // region tool
        new GetgoldSpade().register(plugin);

        new AppraisalPaper(
            BumpItems.APPRAISAL_PAPER_ARMOR,
            RecipeType.SMELTERY,
            new ItemStack[] {
                BumpItems.RANDOM_HELMET
            }
        ).register(plugin);

        new AppraisalPaper(
            BumpItems.APPRAISAL_PAPER_DAMAGE,
            RecipeType.SMELTERY,
            new ItemStack[] {
                BumpItems.RANDOM_SWORD
            }
        ).register(plugin);
        // endregion tool

        // region weapon
        new LightBow().register(plugin);
        new WitherSkullBow().register(plugin);

        new SlimefunItem(
            BumpItemGroups.WEAPON,
            BumpItems.EMER_SWORD,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                null, new ItemStack(Material.EMERALD), null,
                null, new ItemStack(Material.EMERALD), null,
                null, new ItemStack(Material.STICK), null
            }
        ).register(plugin);

        new SlimefunItem(
            BumpItemGroups.WEAPON,
            BumpItems.BONE_SWORD,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                null, new ItemStack(Material.BONE_BLOCK, 64), null,
                null, new ItemStack(Material.BONE_BLOCK, 64), null,
                null, SlimefunItems.GRANDMAS_WALKING_STICK, null
            }
        ).register(plugin);

        new SlimefunItem(
            BumpItemGroups.WEAPON,
            BumpItems.RANDOM_SWORD,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                null, BumpItems.UPDATE_POWER, null,
                null, BumpItems.MAKE, null,
                null, new ItemStack(Material.STICK), null
            }
        ).register(plugin);

        new SlimefunItem(
            BumpItemGroups.WEAPON,
            BumpItems.GUARD_SWORD,
            RecipeType.ARMOR_FORGE,
            new ItemStack[] {
                null, BumpItems.SUN_ENERGY, null,
                null, BumpItems.SUN_ENERGY, null,
                null, new ItemStack(Material.STICK), null
            }
        ).register(plugin);

        new SlimefunItem(
            BumpItemGroups.WEAPON,
            BumpItems.PEACH_SWORD,
            RecipeType.ARMOR_FORGE,
            new ItemStack[] {
                null, BumpItems.PEACH_WOOD, null,
                null, BumpItems.PEACH_WOOD, null,
                null, new ItemStack(Material.STICK), null
            }
        ).register(plugin);

        new SoulSword().register(plugin);
        new SkySword().register(plugin);
        new DevilSword().register(plugin);
        new SkyDevilSword().register(plugin);
        // endregion weapon
    }
}
