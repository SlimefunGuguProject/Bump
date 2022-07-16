package io.github.slimefunguguproject.bump.implementation.setup;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.implementation.BumpItems;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;

import lombok.experimental.UtilityClass;

/**
 * This class is used to set up {@link Research Researches}.
 *
 * @author ybw0014
 */
@UtilityClass
public final class ResearchSetup {
    private static int researchId = 114514;

    public static void setup() {
        createResearch("food", 18, BumpItems.XUEBI, BumpItems.KELE, BumpItems.FANGBIANMIAN, BumpItems.LATIAO, BumpItems.KOUXIANGTANG);
        createResearch("random_equipment", 45, BumpItems.RANDOM_HELMET, BumpItems.RANDOM_SWORD, BumpItems.RANDOM_HORSE_ARMOR);
        createResearch("appraise", 70, BumpItems.APPRAISAL, BumpItems.APPRAISAL_PAPER_ARMOR, BumpItems.APPRAISAL_PAPER_DAMAGE, BumpItems.APPRAISAL_PAPER_HORSE_ARMOR, BumpItems.ATTRIBUTE_GRINDSTONE);
        createResearch("stuff", 25, BumpItems.SUN_ENERGY, BumpItems.MECHA_GEAR, BumpItems.MAKE, BumpItems.OLD_CPU, BumpItems.CPU, BumpItems.SOUL_PAPER, BumpItems.KSF_STUFF, BumpItems.WATER_SUGAR, BumpItems.UPDATE_POWER);
        createResearch("old_stuff", 20, BumpItems.OLD_COIN, BumpItems.GETGOLD_SPADE);
        createResearch("bow", 30, BumpItems.LIGHT_BOW, BumpItems.WITHERSKULL_BOW);
        createResearch("peach_sword", 32, BumpItems.PEACH_WOOD, BumpItems.PEACH_SWORD);
        createResearch("normal_sword", 28, BumpItems.EMER_SWORD, BumpItems.BONE_SWORD, BumpItems.GUARD_SWORD);
        createResearch("soul_sword", 20, BumpItems.SOUL_SWORD);
        createResearch("sky_devil", 42, BumpItems.SKY_SWORD, BumpItems.DEVIL_SWORD, BumpItems.SKY_DEVIL_SWORD);
    }

    @ParametersAreNonnullByDefault
    private static void createResearch(String key, int cost, ItemStack... itemStacks) {
        new Research(Bump.createKey("research_" + key), ++researchId, Bump.getLocalization().getResearchName("key"), cost)
            .addItems(itemStacks).register();
    }
}
