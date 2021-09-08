 package bxx2004.bump.sf;
 


import bxx2004.bump.Bump;
import bxx2004.bump.util.SfItemStackCreate;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;



import org.bukkit.plugin.Plugin;

 public class Weapon
 {
   public static SlimefunItemStack guardSword_;
   public static SlimefunItemStack peachSword_;
   public static SlimefunItemStack soulSword_;
   public static SlimefunItemStack skySword_;
   public static SlimefunItemStack devilSword_;
   public static SlimefunItemStack skydevilSword_;
   public static SlimefunItemStack emerSword_;
   public static SlimefunItemStack boneSword_;
   public static SlimefunItemStack randomSword_;
   public static SlimefunItemStack witherSkullRow_;
   public static SlimefunItemStack LightBow_ = (new SfItemStackCreate("LIGHT_BOW", Material.BOW, "&6神罚之弓", new String[] { "", "&b&k|&b- &7&o接受神的惩罚...", "" },  "ARROW_DAMAGE-5" )).get();
   
   public ItemGroup weapon;
   
   static {
     witherSkullRow_ = (new SfItemStackCreate("WITHERSKULL_ROW", Material.BOW, "&6凋零弓", new String[] { "", "&b&k|&b- &7&o它发射出去的的是每一个亡灵...", "" },  "ARROW_DAMAGE-5" )).get();
     emerSword_ = (new SfItemStackCreate("EMER_SWORD", Material.DIAMOND_SWORD, "&6绿宝石之剑", new String[] { "", "&b&k|&b- &7&o昂贵的绿宝石打造而成...", "" },  "DAMAGE_ALL-1" )).get();
     boneSword_ = (new SfItemStackCreate("BONE_SWORD", Material.WOODEN_SWORD, "&6骨剑", new String[] { "", "&b&k|&b- &7&o坚硬无比...", "" },  "DURABILITY-10" )).get();
     randomSword_ = (new SfItemStackCreate("RANDOM_SWORD", Material.DIAMOND_SWORD, "&6随机短刃", new String[] { "", "&b&k|&b- &7&o我的运气怎么样...", "&b&k|&b- &e&o鉴定仪 &7&o随机给予此一个属性...", "§b§k|§b- §7§o武器尚未鉴定..." })).get();
     guardSword_ = (new SfItemStackCreate("GUARD_SWORD", Material.GOLDEN_SWORD, "&6守护之剑", new String[] { "", "&b&k|&b- &7&o我守护着你,到生命的最后一刻...", "" },  "DAMAGE_ALL-5", "IMPALING-3" )).get();
     peachSword_ = (new SfItemStackCreate("PEACH_SWORD", Material.WOODEN_SWORD, "&6桃木剑", new String[] { "", "&b&k|&b- &7&o致敬经典僵尸片...", "" },  "KNOCKBACK-5", "DURABILITY-3" )).get();
     soulSword_ = (new SfItemStackCreate("SOUL_SWORD", Material.IRON_SWORD, "&6灵魂之剑", new String[] { "", "&b&k|&b- &7&o恶魔的灵魂在这里被注入...", "&b&k|&b- &e&o右键 &7&o可以发出一声低吼...", "&b&k|&b- &7&o将你的&e&o饱食度&7&o转化成&e&o生命值&7&o...", "" }, "DAMAGE_ALL-2", "DURABILITY-1" )).get();
     skySword_ = (new SfItemStackCreate("SKY_SWORD", Material.GOLDEN_SWORD, "&6破天剑", new String[] { "", "&b&k|&b- &7&o破天,多么霸气的名字...", "&b&k|&b- &e&o右键 &7&o将自己冲向天际...", "" },  "DAMAGE_ALL-1", "DURABILITY-1", "SWEEPING_EDGE-1" )).get();
     devilSword_ = (new SfItemStackCreate("DEVIL_SWORD", Material.IRON_SWORD, "&6斩魔剑", new String[] { "", "&b&k|&b- &7&o斩魔,多么威武的称号...", "&b&k|&b- &e&o右键 &7&o发出圣火横扫一切黑暗...", "" },  "DAMAGE_ALL-1", "DURABILITY-1", "SWEEPING_EDGE-1" )).get();
     skydevilSword_ = (new SfItemStackCreate("SKY_DEVIL_SWORD", Material.DIAMOND_SWORD, "&6破天斩魔剑", new String[] { "", "&b&k|&b- &7&o破天斩魔,相继发出强大的能量...", "&b&k|&b- &e&o右键 &7&o连续向前发射三次吐息并且提升自己..." },  "DAMAGE_ALL-5", "DURABILITY-5", "LOYALTY-5" )).get();
   }
   
   public Weapon(SlimefunAddon bump) {
     this.weapon = new ItemGroup(new NamespacedKey(Bump.getPlugin(Bump.class), "Weapon"), new CustomItemStack(Material.IRON_SWORD, "&aBump-魔法武器",  "", "&b&k|&b- 点击打开 >", "", "&7这些东西被魔法灌注的更加强大!", "请谨慎的研究他们!" ));
     this.weapon.register(bump);
     SlimefunItem LightBow = new SlimefunItem(this.weapon, LightBow_, RecipeType.ARMOR_FORGE, new ItemStack[] { SlimefunItems.LIGHTNING_RUNE, SlimefunItems.STAFF_STORM, SlimefunItems.LIGHTNING_RUNE, SlimefunItems.POWER_CRYSTAL, SlimefunItems.STAFF_STORM, SlimefunItems.LIGHTNING_RUNE, SlimefunItems.STAFF_STORM });
     SlimefunItem witherskullbow = new SlimefunItem(this.weapon, witherSkullRow_, RecipeType.ARMOR_FORGE, new ItemStack[] { SlimefunItems.NECROTIC_SKULL, Stuff.peachwood_, SlimefunItems.NECROTIC_SKULL, SlimefunItems.POWER_CRYSTAL, Stuff.peachwood_, SlimefunItems.NECROTIC_SKULL, Stuff.peachwood_ });
     SlimefunItem randomSword = new SlimefunItem(this.weapon, randomSword_, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, null, null, null, Stuff.make_, new ItemStack(Material.STICK) });
     SlimefunItem boneSword = new SlimefunItem(this.weapon, boneSword_, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.BONE_BLOCK, 64), new ItemStack(Material.BONE_BLOCK, 64), SlimefunItems.GRANDMAS_WALKING_STICK });
     SlimefunItem emerSword = new SlimefunItem(this.weapon, emerSword_, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, new ItemStack(Material.EMERALD), new ItemStack(Material.EMERALD), new ItemStack(Material.STICK) });
     SlimefunItem peachSword = new SlimefunItem(this.weapon, peachSword_, RecipeType.ARMOR_FORGE, new ItemStack[] { null, Stuff.peachwood_, Stuff.peachwood_, new ItemStack(Material.STICK) });
     SlimefunItem skydevilSword = new SlimefunItem(this.weapon, skydevilSword_, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, skySword_, Stuff.upDatePower_, devilSword_ });
     SlimefunItem devilSword = new SlimefunItem(this.weapon, devilSword_, RecipeType.ARMOR_FORGE, new ItemStack[] { SlimefunItems.MAGIC_LUMP_2, SlimefunItems.ENDER_RUNE, SlimefunItems.MAGIC_LUMP_2, SlimefunItems.FIRE_RUNE, SlimefunItems.FIRE_RUNE, SlimefunItems.MAGIC_LUMP_2, SlimefunItems.ENDER_RUNE, SlimefunItems.MAGIC_LUMP_2 });
     SlimefunItem skySword = new SlimefunItem(this.weapon, skySword_, RecipeType.ARMOR_FORGE, new ItemStack[] { SlimefunItems.MAGIC_LUMP_2, SlimefunItems.AIR_RUNE, SlimefunItems.MAGIC_LUMP_2, SlimefunItems.RAINBOW_RUNE, SlimefunItems.RAINBOW_RUNE, SlimefunItems.MAGIC_LUMP_2, SlimefunItems.AIR_RUNE, SlimefunItems.MAGIC_LUMP_2 });
     SlimefunItem guardSword = new SlimefunItem(this.weapon, guardSword_, RecipeType.ARMOR_FORGE, new ItemStack[] { null, Stuff.sunEnergy_, Stuff.sunEnergy_, new ItemStack(Material.STICK) });
     SlimefunItem soulSword = new SlimefunItem(this.weapon, soulSword_, RecipeType.ARMOR_FORGE, new ItemStack[] { null, Stuff.soulPaper_, Stuff.soulPaper_, new ItemStack(Material.DIAMOND_SWORD) });
   }
 }
