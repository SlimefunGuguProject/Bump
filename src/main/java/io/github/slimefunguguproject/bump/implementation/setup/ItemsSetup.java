package io.github.slimefunguguproject.bump.implementation.setup;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.bakedlibs.dough.items.CustomItemStack;
import io.github.slimefunguguproject.bump.core.recipes.BumpRecipeTypes;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.implementation.BumpItems;
import io.github.slimefunguguproject.bump.implementation.groups.BumpItemGroups;
import io.github.slimefunguguproject.bump.implementation.items.RandomEquipment;
import io.github.slimefunguguproject.bump.implementation.items.food.Fangbianmian;
import io.github.slimefunguguproject.bump.implementation.items.food.Kele;
import io.github.slimefunguguproject.bump.implementation.items.food.Kouxiangtang;
import io.github.slimefunguguproject.bump.implementation.items.food.Latiao;
import io.github.slimefunguguproject.bump.implementation.items.food.Xuebi;
import io.github.slimefunguguproject.bump.implementation.items.food.Zongzi;
import io.github.slimefunguguproject.bump.implementation.items.legacy.LegacyAppraisalPaper;
import io.github.slimefunguguproject.bump.implementation.items.machines.AppraisalInstrument;
import io.github.slimefunguguproject.bump.implementation.items.machines.AttributeGrindstone;
import io.github.slimefunguguproject.bump.implementation.items.stuff.StuffItem;
import io.github.slimefunguguproject.bump.implementation.items.tools.GetgoldSpade;
import io.github.slimefunguguproject.bump.implementation.items.tools.QualityIdentifier;
import io.github.slimefunguguproject.bump.implementation.items.weapons.DevilSword;
import io.github.slimefunguguproject.bump.implementation.items.weapons.LightBow;
import io.github.slimefunguguproject.bump.implementation.items.weapons.SkyDevilSword;
import io.github.slimefunguguproject.bump.implementation.items.weapons.SkySword;
import io.github.slimefunguguproject.bump.implementation.items.weapons.SoulSword;
import io.github.slimefunguguproject.bump.implementation.items.weapons.WitherSkullBow;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.multiblocks.Compressor;

import lombok.experimental.UtilityClass;

/**
 * This class is used to set up items.
 *
 * @author ybw0014
 */
@UtilityClass
public final class ItemsSetup {
    public static void setup(@Nonnull SlimefunAddon plugin) {
        // <editor-fold defaultstate="collapsed" desc="Armor">
        new RandomEquipment(
            BumpItemGroups.ARMOR,
            BumpItems.RANDOM_HELMET,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                BumpItems.OLD_COIN, null, BumpItems.OLD_COIN,
                BumpItems.OLD_COIN, BumpItems.MAKE, BumpItems.OLD_COIN,
                BumpItems.OLD_COIN, BumpItems.UPDATE_POWER, BumpItems.OLD_COIN
            }
        ).register(plugin);

        new RandomEquipment(
            BumpItemGroups.ARMOR,
            BumpItems.RANDOM_HORSE_ARMOR,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                BumpItems.OLD_COIN, null, BumpItems.OLD_COIN,
                BumpItems.OLD_COIN, new ItemStack(Material.DIAMOND_HORSE_ARMOR), BumpItems.OLD_COIN,
                BumpItems.OLD_COIN, BumpItems.UPDATE_POWER, BumpItems.OLD_COIN
            }
        ).register(plugin);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Food">
        new Xuebi().register(plugin);
        new Kele().register(plugin);
        new Fangbianmian().register(plugin);
        new Latiao().register(plugin);
        new Kouxiangtang().register(plugin);
        new Zongzi().register(plugin);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Machine">
        new AppraisalInstrument().register(plugin);
        new AttributeGrindstone().register(plugin);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Stuff">
        new StuffItem(
            BumpItems.SUN_ENERGY,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                new ItemStack(Material.CHORUS_FLOWER), new ItemStack(Material.SUNFLOWER), new ItemStack(Material.CHORUS_FLOWER),
                null, null, null,
                null, null, null
            }
        ).register(plugin);

        new StuffItem(
            BumpItems.MECHA_GEAR,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
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
            new ItemStack[]{
                SlimefunItems.BATTERY, SlimefunItems.BATTERY, SlimefunItems.BATTERY,
                SlimefunItems.COOLING_UNIT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.COOLING_UNIT,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD
            }
        ).register(plugin);

        new StuffItem(
            BumpItems.OLD_CPU,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE,
                SlimefunItems.COPPER_WIRE, BumpItems.MAKE, SlimefunItems.COPPER_WIRE,
                SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE
            }
        ).register(plugin);

        new StuffItem(
            BumpItems.CPU,
            RecipeType.COMPRESSOR,
            new ItemStack[]{
                BumpItems.OLD_CPU
            }
        ).register(plugin);

        new StuffItem(
            BumpItems.SOUL_PAPER,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                SlimefunItems.MAGIC_LUMP_1, SlimefunItems.ESSENCE_OF_AFTERLIFE, SlimefunItems.MAGIC_LUMP_1,
                SlimefunItems.SOULBOUND_RUNE, SlimefunItems.SOULBOUND_RUNE, SlimefunItems.SOULBOUND_RUNE,
                SlimefunItems.MAGIC_LUMP_1, SlimefunItems.ESSENCE_OF_AFTERLIFE, SlimefunItems.MAGIC_LUMP_1
            }
        ).register(plugin);

        new StuffItem(
            BumpItems.KSF_STUFF,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.SALT, SlimefunItems.SALT, SlimefunItems.SALT,
                SlimefunItems.CARROT_FERTILIZER, SlimefunItems.CARROT_FERTILIZER, SlimefunItems.CARROT_FERTILIZER,
                SlimefunItems.SALT, SlimefunItems.SALT, SlimefunItems.SALT
            }
        ).register(plugin);

        new StuffItem(
            BumpItems.WATER_SUGAR,
            RecipeType.PRESSURE_CHAMBER,
            new ItemStack[]{
                SlimefunItems.MAGIC_SUGAR
            }
        ).register(plugin);

        new StuffItem(
            BumpItems.PEACH_WOOD,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                new ItemStack(Material.ACACIA_WOOD), new ItemStack(Material.BIRCH_WOOD), new ItemStack(Material.DARK_OAK_WOOD),
                null, null, null,
                null, null, null
            }
        ).register(plugin);

        new StuffItem(
            BumpItems.UPDATE_POWER,
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[]{
                SlimefunItems.POWER_CRYSTAL, SlimefunItems.LAVA_CRYSTAL, SlimefunItems.POWER_CRYSTAL,
                SlimefunItems.LAVA_CRYSTAL, BumpItems.CPU, SlimefunItems.LAVA_CRYSTAL,
                SlimefunItems.GOLD_24K, SlimefunItems.GOLD_24K, SlimefunItems.GOLD_24K
            }
        ).register(plugin);

        new StuffItem(
            BumpItems.COMPRESSED_RANDOM_EQUIPMENT,
            BumpRecipeTypes.COMPRESSOR_MOCK,
            new ItemStack[]{
                new CustomItemStack(Material.DIAMOND_CHESTPLATE, Bump.getLocalization().getString("lores.any-random-equipment"))
            }
        ).register(plugin);

        // compression random equipment
        final SlimefunItem compressorItem = SlimefunItem.getById("COMPRESSOR");
        if (compressorItem instanceof Compressor compressor) {
            compressor.addRecipe(new ItemStack[]{BumpItems.RANDOM_HELMET}, BumpItems.COMPRESSED_RANDOM_EQUIPMENT);
            compressor.addRecipe(new ItemStack[]{BumpItems.RANDOM_SWORD}, BumpItems.COMPRESSED_RANDOM_EQUIPMENT);
            compressor.addRecipe(new ItemStack[]{BumpItems.RANDOM_HORSE_ARMOR}, BumpItems.COMPRESSED_RANDOM_EQUIPMENT);
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Tools">
        new GetgoldSpade().register(plugin);

        new QualityIdentifier(
            BumpItems.QUALITY_IDENTIFIER,
            RecipeType.SMELTERY,
            new ItemStack[]{
                BumpItems.COMPRESSED_RANDOM_EQUIPMENT
            }
        ).register(plugin);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Weapon">
        new LightBow().register(plugin);
        new WitherSkullBow().register(plugin);

        new SlimefunItem(
            BumpItemGroups.WEAPON,
            BumpItems.EMER_SWORD,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                null, new ItemStack(Material.EMERALD), null,
                null, new ItemStack(Material.EMERALD), null,
                null, new ItemStack(Material.STICK), null
            }
        ).register(plugin);

        new SlimefunItem(
            BumpItemGroups.WEAPON,
            BumpItems.BONE_SWORD,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                null, new ItemStack(Material.BONE_BLOCK, 64), null,
                null, new ItemStack(Material.BONE_BLOCK, 64), null,
                null, SlimefunItems.GRANDMAS_WALKING_STICK, null
            }
        ).register(plugin);

        new RandomEquipment(
            BumpItemGroups.WEAPON,
            BumpItems.RANDOM_SWORD,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                null, BumpItems.UPDATE_POWER, null,
                null, BumpItems.MAKE, null,
                null, new ItemStack(Material.STICK), null
            }
        ).register(plugin);

        new SlimefunItem(
            BumpItemGroups.WEAPON,
            BumpItems.GUARD_SWORD,
            RecipeType.ARMOR_FORGE,
            new ItemStack[]{
                null, BumpItems.SUN_ENERGY, null,
                null, BumpItems.SUN_ENERGY, null,
                null, new ItemStack(Material.STICK), null
            }
        ).register(plugin);

        new SlimefunItem(
            BumpItemGroups.WEAPON,
            BumpItems.PEACH_SWORD,
            RecipeType.ARMOR_FORGE,
            new ItemStack[]{
                null, BumpItems.PEACH_WOOD, null,
                null, BumpItems.PEACH_WOOD, null,
                null, new ItemStack(Material.STICK), null
            }
        ).register(plugin);

        new SoulSword().register(plugin);
        new SkySword().register(plugin);
        new DevilSword().register(plugin);
        new SkyDevilSword().register(plugin);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Hidden">

        // legacy quality identifiers
        new LegacyAppraisalPaper(
            BumpItems.APPRAISAL_PAPER_ARMOR,
            RecipeType.SMELTERY,
            new ItemStack[]{
                BumpItems.RANDOM_HELMET
            }
        ).register(plugin);

        new LegacyAppraisalPaper(
            BumpItems.APPRAISAL_PAPER_DAMAGE,
            RecipeType.SMELTERY,
            new ItemStack[]{
                BumpItems.RANDOM_SWORD
            }
        ).register(plugin);

        new LegacyAppraisalPaper(
            BumpItems.APPRAISAL_PAPER_HORSE_ARMOR,
            RecipeType.SMELTERY,
            new ItemStack[]{
                BumpItems.RANDOM_HORSE_ARMOR
            }
        ).register(plugin);
        // </editor-fold>
    }
}
