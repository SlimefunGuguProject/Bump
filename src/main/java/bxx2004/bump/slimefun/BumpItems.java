package bxx2004.bump.slimefun;

import bxx2004.bump.Bump;
import bxx2004.bump.slimefun.items.BumpItemStack;
import bxx2004.bump.slimefun.items.machine.Appraisal;
import bxx2004.bump.util.AppraiseUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class BumpItems {
    private BumpItems() {}

    // region armor
    public static final SlimefunItemStack RANDOM_HELMET = new BumpItemStack(
        "RANDOM_HELMET",
        Material.DIAMOND_HELMET
    );
    static{
        AppraiseUtils.setAppraisable(RANDOM_HELMET);
    }
    // endregion armor

    // region food
    public static final SlimefunItemStack XUEBI = new BumpItemStack(
        "XUEBI",
        Material.POTION
    );
    static {
        XUEBI.addUnsafeEnchantment(Enchantment.MENDING, 3);
    }

    public static final SlimefunItemStack KELE = new BumpItemStack(
        "KELE",
        Material.POTION
    );

    public static final SlimefunItemStack FANGBIANMIAN = new BumpItemStack(
        "FANGBIANMIAN",
        Material.STRING
    );

    public static final SlimefunItemStack LATIAO = new BumpItemStack(
        "LATIAO",
        Material.ROTTEN_FLESH
    );

    public static final SlimefunItemStack KOUXIANGTANG = new BumpItemStack(
        "KOUXIANGTANG",
        Material.SUGAR
    );
    // endregion food

    // region machine
    public static final SlimefunItemStack APPRAISAL = new BumpItemStack(
        "APPRAISAL",
        Material.BELL,
        LoreBuilder.power(Appraisal.getEnergyConsumption(), Bump.getLocalization().getString("lores.per-use"))
    );
    // endregion machine

    // region stuff
    public static final SlimefunItemStack SUN_ENERGY = new BumpItemStack(
        "SUN_ENERGY",
        Material.BELL
    );

    public static final SlimefunItemStack MECHA_GEAR = new BumpItemStack(
        "MECHA_GEAR",
        Material.ENDER_PEARL
    );

    public static final SlimefunItemStack OLD_COIN = new BumpItemStack(
        "OLD_COIN",
        Material.GOLD_NUGGET
    );

    public static final SlimefunItemStack MAKE = new BumpItemStack(
        "MAKE",
        Material.DIAMOND
    );

    public static final SlimefunItemStack OLD_CPU = new BumpItemStack(
        "OLD_CPU",
        Material.PRISMARINE_CRYSTALS
    );

    public static final SlimefunItemStack CPU = new BumpItemStack(
        "CPU",
        Material.DIAMOND
    );

    public static final SlimefunItemStack SOUL_PAPER = new BumpItemStack(
        "SOUL_PAPER",
        Material.PAPER
    );

    public static final SlimefunItemStack KSF_STUFF = new BumpItemStack(
        "KSF_STUFF",
        Material.BEETROOT_SEEDS
    );

    public static final SlimefunItemStack WATER_SUGAR = new BumpItemStack(
        "WATER_SUGAR",
        Material.SUGAR
    );

    public static final SlimefunItemStack PEACH_WOOD = new BumpItemStack(
        "PEACH_WOOD",
        Material.STICK
    );

    public static final SlimefunItemStack UPDATE_POWER = new BumpItemStack(
        "UPDATE_POWER",
        Material.ZOMBIE_HEAD
    );
    // endregion stuff

    // region tool
    public static final SlimefunItemStack GETGOLD_SPADE = new BumpItemStack(
        "GETGOLD_SPADE",
        Material.GOLDEN_SHOVEL
    );
    static {
        GETGOLD_SPADE.addUnsafeEnchantment(Enchantment.MENDING, 3);
    }

    public static final SlimefunItemStack APPRAISAL_PAPER_ARMOR = new BumpItemStack(
        "APPRAISAL_PAPER_ARMOR",
        Material.PAPER
    );

    public static final SlimefunItemStack APPRAISAL_PAPER_DAMAGE = new BumpItemStack(
        "APPRAISAL_PAPER_DAMAGE",
        Material.PAPER
    );
    // endregion tool

    // region weapon
    public static final SlimefunItemStack LIGHT_BOW = new BumpItemStack(
        "LIGHT_BOW",
        Material.BOW
    );
    static {
        LIGHT_BOW.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 5);
    }

    public static final SlimefunItemStack WITHERSKULL_ROW = new BumpItemStack(
        "WITHERSKULL_ROW",
        Material.BOW
    );
    static {
        WITHERSKULL_ROW.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 5);
    }

    public static final SlimefunItemStack EMER_SWORD = new BumpItemStack(
        "EMER_SWORD",
        Material.DIAMOND_SWORD
    );
    static {
        EMER_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
    }

    public static final SlimefunItemStack BONE_SWORD = new BumpItemStack(
        "BONE_SWORD",
        Material.WOODEN_SWORD
    );
    static {
        BONE_SWORD.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
    }

    public static final SlimefunItemStack RANDOM_SWORD = new BumpItemStack(
        "RANDOM_SWORD",
        Material.DIAMOND_SWORD
    );
    static{
        AppraiseUtils.setAppraisable(RANDOM_SWORD);
    }

    public static final SlimefunItemStack GUARD_SWORD = new BumpItemStack(
        "GUARD_SWORD",
        Material.GOLDEN_SWORD
    );
    static {
        GUARD_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
        GUARD_SWORD.addUnsafeEnchantment(Enchantment.IMPALING, 3);
    }

    public static final SlimefunItemStack PEACH_SWORD = new BumpItemStack(
        "PEACH_SWORD",
        Material.WOODEN_SWORD
    );
    static {
        PEACH_SWORD.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);
        PEACH_SWORD.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
    }

    public static final SlimefunItemStack SOUL_SWORD = new BumpItemStack(
        "SOUL_SWORD",
        Material.IRON_SWORD
    );
    static {
        SOUL_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
        SOUL_SWORD.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
    }

    public static final SlimefunItemStack SKY_SWORD = new BumpItemStack(
        "SKY_SWORD",
        Material.GOLDEN_SWORD
    );
    static {
        SKY_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        SKY_SWORD.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        SKY_SWORD.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 1);
    }

    public static final SlimefunItemStack DEVIL_SWORD = new BumpItemStack(
        "DEVIL_SWORD",
        Material.IRON_SWORD
    );
    static {
        DEVIL_SWORD.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        DEVIL_SWORD.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        DEVIL_SWORD.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 1);
    }

    public static final SlimefunItemStack SKY_DEVIL_SWORD = new BumpItemStack(
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
