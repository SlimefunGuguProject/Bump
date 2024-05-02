package io.github.slimefunguguproject.bump.implementation.setup

import io.github.slimefunguguproject.bump.Bump
import io.github.slimefunguguproject.bump.utils.constant.Keys.createKey
import io.github.thebusybiscuit.slimefun4.api.researches.Research
import org.bukkit.inventory.ItemStack

object ResearchSetup {
    private var researchId = 114514

    fun setup() {
        // TODO: resume research setup
//        createResearch(
//            "food",
//            18,
//            BumpItems.XUEBI,
//            BumpItems.KELE,
//            BumpItems.FANGBIANMIAN,
//            BumpItems.LATIAO,
//            BumpItems.KOUXIANGTANG
//        )
//        createResearch(
//            "random_equipment",
//            45,
//            BumpItems.RANDOM_HELMET,
//            BumpItems.RANDOM_SWORD,
//            BumpItems.RANDOM_HORSE_ARMOR
//        )
//        createResearch(
//            "appraise",
//            70,
//            BumpItems.APPRAISAL,
//            BumpItems.ATTRIBUTE_GRINDSTONE
//        )
//        createResearch(
//            "stuff",
//            25,
//            BumpItems.SUN_ENERGY,
//            BumpItems.MECHA_GEAR,
//            BumpItems.MAKE,
//            BumpItems.OLD_CPU,
//            BumpItems.CPU,
//            BumpItems.SOUL_PAPER,
//            BumpItems.KSF_STUFF,
//            BumpItems.WATER_SUGAR,
//            BumpItems.UPDATE_POWER
//        )
//        createResearch("old_stuff", 20, BumpItems.OLD_COIN, BumpItems.GETGOLD_SPADE)
//        createResearch("bow", 30, BumpItems.LIGHT_BOW, BumpItems.WITHERSKULL_BOW)
//        createResearch("peach_sword", 32, BumpItems.PEACH_WOOD, BumpItems.PEACH_SWORD)
//        createResearch("normal_sword", 28, BumpItems.EMER_SWORD, BumpItems.BONE_SWORD, BumpItems.GUARD_SWORD)
//        createResearch("soul_sword", 20, BumpItems.SOUL_SWORD)
//        createResearch("sky_devil", 42, BumpItems.SKY_SWORD, BumpItems.DEVIL_SWORD, BumpItems.SKY_DEVIL_SWORD)
    }

    private fun createResearch(key: String, cost: Int, vararg itemStacks: ItemStack) {
        Research("research_$key".createKey(), ++researchId, Bump.localization.getResearchName("key"), cost)
            .addItems(*itemStacks).register()
    }
}
