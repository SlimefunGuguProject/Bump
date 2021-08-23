/*    */ package bxx2004.bump.sf;
/*    */ 
/*    */ import bxx2004.bump.Bump;
/*    */ import bxx2004.bump.util.SfItemStackCreate;
/*    */ import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
/*    */ import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*    */ import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
/*    */ import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ //import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Stuff
/*    */ {
/*    */   public static SlimefunItemStack sunEnergy_;
/*    */   public static SlimefunItemStack mechaGeat_;
/*    */   public static SlimefunItemStack oldCoin_;
/*    */   public static SlimefunItemStack make_;
/*    */   public static SlimefunItemStack oldCPU_;
/*    */   public static SlimefunItemStack CPU_;
/*    */   public static SlimefunItemStack soulPaper_;
/*    */   public static SlimefunItemStack ksf_;
/*    */   public static SlimefunItemStack waterSugar_;
/*    */   public static SlimefunItemStack peachwood_;
/* 40 */   public static SlimefunItemStack upDatePower_ = (new SfItemStackCreate("UPDATE_POWER", Material.ZOMBIE_HEAD, "&e升级核心", new String[] { "", "&b&k|&b- 用来升级一些奇怪的物品...", "" })).get();
/*    */   
/*    */   public Category stuff;
/*    */   
/*    */   static {
/* 45 */     sunEnergy_ = (new SfItemStackCreate("SUN_ENERGY", Material.SUNFLOWER, "&e光合能量", new String[] { "", "&b&k|&b- 变异的植物也会进行光合作用...", "" })).get();
/* 46 */     mechaGeat_ = (new SfItemStackCreate("MECHA_GEAR", Material.ENDER_PEARL, "&e机械齿轮", new String[] { "", "&b&k|&b- 某大型工厂淘汰下来的东西...", "" })).get();
/* 47 */     oldCoin_ = (new SfItemStackCreate("OLD_COIN", Material.GOLD_NUGGET, "&e破损的的金币", new String[] { "", "&b&k|&b- 这东西能买什么...", "" })).get();
/* 48 */     make_ = (new SfItemStackCreate("MAKE", Material.DIAMOND, "&e计算机工艺核心", new String[] { "", "&b&k|&b- 它用来干什么呢...", "" })).get();
/* 49 */     oldCPU_ = (new SfItemStackCreate("OLD_CPU", Material.PRISMARINE_CRYSTALS, "&e破损的CPU", new String[] { "", "&b&k|&b- 从哪家的电脑上拆下来的...", "" })).get();
/* 50 */     CPU_ = (new SfItemStackCreate("CPU", Material.DIAMOND, "&eCPU", new String[] { "", "&b&k|&b- 完好无损...", "" })).get();
/* 51 */     soulPaper_ = (new SfItemStackCreate("SOUL_PAPER", Material.PAPER, "&e灵魂之符", new String[] { "", "&b&k|&b- 被注入了灵魂的力量", "" })).get();
/* 52 */     ksf_ = (new SfItemStackCreate("KSF_STUFF", Material.BEETROOT_SEEDS, "&e方便面调料", new String[] { "", "&b&k|&b- 嘶~ 是香辣味的", "" })).get();
/* 53 */     waterSugar_ = (new SfItemStackCreate("WATER_SUGAR", Material.SUGAR, "&E汽水糖", new String[] { "", "&b&k|&b- 雪碧? 还是可口可乐?", "" })).get();
/* 54 */     peachwood_ = (new SfItemStackCreate("PEACH_WOOD", Material.STICK, "&e桃木", new String[] { "", "&b&k|&b- 驱邪避难", "" })).get();
/*    */   }
/*    */   
/*    */   public Stuff(SlimefunAddon plugin) {
/* 58 */     this.stuff = new Category(new NamespacedKey(Bump.getPlugin(Bump.class), "Stuff"), new CustomItem(Material.NETHER_STAR, "&bBump-魔法物品",  "", "&b&k|&b- 点击打开 >", "", "&7在生活中发生变异的一些物品!", "他们可能含有未知的力量..." ));
/* 59 */     this.stuff.register(plugin);
/* 60 */     SlimefunItem upDatePower = new SlimefunItem(this.stuff, upDatePower_, RecipeType.MAGIC_WORKBENCH, new ItemStack[] { SlimefunItems.POWER_CRYSTAL, SlimefunItems.LAVA_CRYSTAL, SlimefunItems.POWER_CRYSTAL, SlimefunItems.LAVA_CRYSTAL, CPU_, SlimefunItems.LAVA_CRYSTAL, SlimefunItems.GOLD_24K, SlimefunItems.GOLD_24K, SlimefunItems.GOLD_24K });
/* 61 */     SlimefunItem peachwood = new SlimefunItem(this.stuff, peachwood_, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { new ItemStack(Material.ACACIA_WOOD), new ItemStack(Material.BIRCH_WOOD), new ItemStack(Material.DARK_OAK_WOOD) });
/* 62 */     SlimefunItem soulPaper = new SlimefunItem(this.stuff, soulPaper_, RecipeType.ANCIENT_ALTAR, new ItemStack[] { SlimefunItems.MAGIC_LUMP_1, SlimefunItems.ESSENCE_OF_AFTERLIFE, SlimefunItems.MAGIC_LUMP_1, SlimefunItems.SOULBOUND_RUNE, SlimefunItems.SOULBOUND_RUNE, SlimefunItems.SOULBOUND_RUNE, SlimefunItems.MAGIC_LUMP_1, SlimefunItems.ESSENCE_OF_AFTERLIFE, SlimefunItems.MAGIC_LUMP_1 });
/* 63 */     SlimefunItem ksf = new SlimefunItem(this.stuff, ksf_, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.SALT, SlimefunItems.SALT, SlimefunItems.SALT, SlimefunItems.CARROT_FERTILIZER, SlimefunItems.CARROT_FERTILIZER, SlimefunItems.CARROT_FERTILIZER, SlimefunItems.SALT, SlimefunItems.SALT, SlimefunItems.SALT });
/* 64 */     SlimefunItem watersugar = new SlimefunItem(this.stuff, waterSugar_, RecipeType.PRESSURE_CHAMBER, new ItemStack[] { SlimefunItems.MAGIC_SUGAR });
/* 65 */     SlimefunItem sunEnergy = new SlimefunItem(this.stuff, sunEnergy_, RecipeType.MAGIC_WORKBENCH, new ItemStack[] { new ItemStack(Material.CHORUS_FLOWER, 1), new ItemStack(Material.SUNFLOWER, 1), new ItemStack(Material.CHORUS_FLOWER, 1) });
/* 66 */     SlimefunItem mechaGeat = new SlimefunItem(this.stuff, mechaGeat_, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.COPPER_WIRE, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD });
/* 67 */     SlimefunItem oldCoin = new SlimefunItem(this.stuff, oldCoin_, RecipeType.ARMOR_FORGE, new ItemStack[] { null, null, null, null, new CustomItem(Material.DIAMOND_SHOVEL, "&e&o获取方式: ","&7&o摸金铲也许对他有点作用..." ) });
/* 68 */     SlimefunItem make = new SlimefunItem(this.stuff, make_, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.BATTERY, SlimefunItems.BATTERY, SlimefunItems.BATTERY, SlimefunItems.COOLING_UNIT, SlimefunItems.POWER_CRYSTAL, SlimefunItems.COOLING_UNIT, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ADVANCED_CIRCUIT_BOARD });
/* 69 */     SlimefunItem oldCPU = new SlimefunItem(this.stuff, oldCPU_, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE, make.getItem(), SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE, SlimefunItems.COPPER_WIRE });
/* 70 */     SlimefunItem CPU = new SlimefunItem(this.stuff, CPU_, RecipeType.COMPRESSOR, new ItemStack[] { oldCPU.getItem() });
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\bump测试\【魔法】Bump_v1.0.jar!\bxx2004\bump\sf\Stuff.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */