package org.slimefunguguproject.bump.implementation;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.slimefunguguproject.bump.api.items.LocalizedItemStack;
import org.slimefunguguproject.bump.implementation.items.machines.AppraisalInstrument;
import org.slimefunguguproject.bump.implementation.items.machines.AttributeGrindstone;
import org.slimefunguguproject.bump.implementation.items.tools.AppraisalPaper;
import org.slimefunguguproject.bump.utils.AppraiseUtils;

/**
 * This class holds all {@link SlimefunItemStack} of Bump.
 *
 * @author ybw0014
 */
public class BumpItems {
    private BumpItems() {
        throw new IllegalStateException("Utility class");
    }

    // region armor
    public static final SlimefunItemStack RANDOM_HELMET = new LocalizedItemStack(
        "RANDOM_HELMET",
        Material.DIAMOND_HELMET
    );
    static{
        AppraiseUtils.setAppraisable(RANDOM_HELMET);
    }
    
    public static final SlimefunItemStack RANDOM_HORSE_ARMOR = new LocalizedItemStack(
        "RANDOM_HORSE_ARMOR",
        Material.DIAMOND_HORSE_ARMOR
    );
    static{
        AppraiseUtils.setAppraisable(RANDOM_HORSE_ARMOR);
    }
    // endregion armor

    // region food
    public static final SlimefunItemStack XUEBI = new LocalizedItemStack(
        "XUEBI",
        Material.POTION
    );
    static {
        XUEBI.addUnsafeEnchantment(Enchantment.MENDING, 3);
    }

    public static final SlimefunItemStack KELE = new LocalizedItemStack(
        "KELE",
        Material.POTION
    );

    public static final SlimefunItemStack FANGBIANMIAN = new LocalizedItemStack(
        "FANGBIANMIAN",
        Material.STRING
    );

    public static final SlimefunItemStack LATIAO = new LocalizedItemStack(
        "LATIAO",
        Material.ROTTEN_FLESH
    );

    public static final SlimefunItemStack KOUXIANGTANG = new LocalizedItemStack(
        "KOUXIANGTANG",
        Material.SUGAR
    );
    
    public static final SlimefunItemStack ZONGZI = new LocalizedItemStack(
        "ZONGZI",
        Material.GREEN_DYE
    );
    // endregion food

    // region machine
    public static final SlimefunItemStack APPRAISAL = new LocalizedItemStack(
        "APPRAISAL",
        Material.BELL,
        LoreBuilder.power(AppraisalInstrument.getEnergyConsumption(), " " + Bump.getLocalization().getString("lores.per-use"))
    );

    public static final SlimefunItemStack ATTRIBUTE_GRINDSTONE = new LocalizedItemStack(
        "ATTRIBUTE_GRINDSTONE",
        Material.GRINDSTONE,
        LoreBuilder.power(AttributeGrindstone.getEnergyConsumption(), " " + Bump.getLocalization().getString("lores.per-use"))
    );
    // endregion machine

    // region stuff
    public static final SlimefunItemStack SUN_ENERGY = new LocalizedItemStack(
        "SUN_ENERGY",
        Material.BELL
    );

    public static final SlimefunItemStack MECHA_GEAR = new LocalizedItemStack(
        "MECHA_GEAR",
        Material.ENDER_PEARL
    );

    public static final SlimefunItemStack OLD_COIN = new LocalizedItemStack(
        "OLD_COIN",
        Material.GOLD_NUGGET
    );

    public static final SlimefunItemStack MAKE = new LocalizedItemStack(
        "MAKE",
        Material.DIAMOND
    );

    public static final SlimefunItemStack OLD_CPU = new LocalizedItemStack(
        "OLD_CPU",
        Material.PRISMARINE_CRYSTALS
    );

    public static final SlimefunItemStack CPU = new LocalizedItemStack(
        "CPU",
        Material.DIAMOND
    );

    public static final SlimefunItemStack SOUL_PAPER = new LocalizedItemStack(
        "SOUL_PAPER",
        Material.PAPER
    );

    public static final SlimefunItemStack KSF_STUFF = new LocalizedItemStack(
        "KSF_STUFF",
        Material.BEETROOT_SEEDS
    );

    public static final SlimefunItemStack WATER_SUGAR = new LocalizedItemStack(
        "WATER_SUGAR",
        Material.SUGAR
    );

    public static final SlimefunItemStack PEACH_WOOD = new LocalizedItemStack(
        "PEACH_WOOD",
        Material.STICK
    );

    public static final SlimefunItemStack UPDATE_POWER = new LocalizedItemStack(
        "UPDATE_POWER",
        Material.ZOMBIE_HEAD
    );
    // endregion stuff

    // region tool
    public static final SlimefunItemStack GETGOLD_SPADE = new LocalizedItemStack(
        "GETGOLD_SPADE",
        Material.GOLDEN_SHOVEL
    );
    static {
        GETGOLD_SPADE.addUnsafeEnchantment(Enchantment.MENDING, 3);
    }

    public static final SlimefunItemStack APPRAISAL_PAPER_ARMOR = new LocalizedItemStack(
        "APPRAISAL_PAPER_ARMOR",
        Material.PAPER,
        LoreBuilder.usesLeft(AppraisalPaper.MAX_USES)
    );

    public static final SlimefunItemStack APPRAISAL_PAPER_DAMAGE = new LocalizedItemStack(
        "APPRAISAL_PAPER_DAMAGE",
        Material.PAPER,
        LoreBuilder.usesLeft(AppraisalPaper.MAX_USES)
    );

    public static final SlimefunItemStack APPRAISAL_PAPER_HORSE_ARMOR = new LocalizedItemStack(
        "APPRAISAL_PAPER_HORSE_ARMOR",
        Material.PAPER,
        LoreBuilder.usesLeft(AppraisalPaper.MAX_USES)
    );
    // endregion tool

    // region weapon
    public static final SlimefunItemStack LIGHT_BOW = new LocalizedItemStack(
        "LIGHT_BOW",
        Material.BOW
    );
    static {
        LIGHT_BOW.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 5);
    }

    public static final SlimefunItemStack WITHERSKULL_BOW = new LocalizedItemStack(
        "WITHERSKULL_BOW",
        Material.BOW
    );
    static {
        WITHERSKULL_BOW.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 5);
    }

    public static final SlimefunItemStack EMER_SWORD = new LocalizedItemStack(
        "EMER_SWORD",
        Material.DIAMOND_SWORD
    );
    static {
        EMER_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
    }

    public static final SlimefunItemStack BONE_SWORD = new LocalizedItemStack(
        "BONE_SWORD",
        Material.WOODEN_SWORD
    );
    static {
        BONE_SWORD.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
    }

    public static final SlimefunItemStack RANDOM_SWORD = new LocalizedItemStack(
        "RANDOM_SWORD",
        Material.DIAMOND_SWORD
    );
    static{
        AppraiseUtils.setAppraisable(RANDOM_SWORD);
    }

    public static final SlimefunItemStack GUARD_SWORD = new LocalizedItemStack(
        "GUARD_SWORD",
        Material.GOLDEN_SWORD
    );
    static {
        GUARD_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
        GUARD_SWORD.addUnsafeEnchantment(Enchantment.IMPALING, 3);
    }

    public static final SlimefunItemStack PEACH_SWORD = new LocalizedItemStack(
        "PEACH_SWORD",
        Material.WOODEN_SWORD
    );
    static {
        PEACH_SWORD.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);
        PEACH_SWORD.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
    }

    public static final SlimefunItemStack SOUL_SWORD = new LocalizedItemStack(
        "SOUL_SWORD",
        Material.IRON_SWORD
    );
    static {
        SOUL_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
        SOUL_SWORD.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
    }

    public static final SlimefunItemStack SKY_SWORD = new LocalizedItemStack(
        "SKY_SWORD",
        Material.GOLDEN_SWORD
    );
    static {
        SKY_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        SKY_SWORD.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        SKY_SWORD.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 1);
    }

    public static final SlimefunItemStack DEVIL_SWORD = new LocalizedItemStack(
        "DEVIL_SWORD",
        Material.IRON_SWORD
    );
    static {
        DEVIL_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        DEVIL_SWORD.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        DEVIL_SWORD.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 1);
    }

    public static final SlimefunItemStack SKY_DEVIL_SWORD = new LocalizedItemStack(
        "SKY_DEVIL_SWORD",
        Material.DIAMOND_SWORD
    );
    static {
        SKY_DEVIL_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
        SKY_DEVIL_SWORD.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
        SKY_DEVIL_SWORD.addUnsafeEnchantment(Enchantment.LOYALTY, 5);
    }
    // endregion weapon
}
