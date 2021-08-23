/*    */ package bxx2004.bump.sf;
/*    */ 
/*    */ import bxx2004.bump.Bump;
/*    */ import bxx2004.bump.util.Register;
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
/*    */ public class Food
/*    */ {
/* 21 */   public static SlimefunItemStack xueBi_ = (new SfItemStackCreate("XUEBI", Material.POTION, "&e雪碧", new String[] { "", "&b&k|&b- 很甜很甜, 还冒着气泡!", "" },  "MENDING-3" )).get();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   public Category food = new Category(new NamespacedKey(Bump.getPlugin(Bump.class), "Food"), new CustomItem(Material.BREAD, "&bBump-食物", "", "&b&k|&b- 点击打开 >", "", "&7高级的食物往往...!", "Rua Rua Rua..." ));
/*    */ 
/*    */   
/* 29 */   public static SlimefunItemStack keLe_ = (new SfItemStackCreate("KELE", Material.POTION, "&e可乐",  new String[]{ "&b&k|&b- 冰力十足,喝了真精神,还会飞呢!" })).get();
/* 30 */   public static SlimefunItemStack fangBianMian_ = (new SfItemStackCreate("FANGBIANMIAN", Material.STRING, "&e康师傅方便面", new String[] { "", "&b&k|&b- 这个味,对辣!", "" })).get(); public static SlimefunItemStack laTiao_; static {
/* 31 */     laTiao_ = (new SfItemStackCreate("LATIAO", Material.ROTTEN_FLESH, "&e辣条", new String[] { "", "&b&k|&b- 爽,吃了之后还不忘舔舔辣油...", "" })).get();
/* 32 */     kouXiangTang_ = (new SfItemStackCreate("KOUXIANGTANG", Material.SUGAR, "&e口香糖", new String[] { "", "&b&k|&b- 很粘稠的...", "" })).get();
/*    */   }
/*    */   public static SlimefunItemStack kouXiangTang_;
/*    */   public Food(SlimefunAddon plugin) {
/* 36 */     this.food.register(plugin);
/* 37 */     ItemStack[] itemStackArray = new ItemStack[9];
/* 38 */     itemStackArray[0] = SlimefunItems.MAGIC_SUGAR;
/* 39 */     SlimefunItem kouXiangTang = new SlimefunItem(this.food, kouXiangTang_, RecipeType.COMPRESSOR, itemStackArray);
/* 40 */     ItemStack[] itemStackArray2 = new ItemStack[9];
/* 41 */     itemStackArray2[0] = SlimefunItems.WHEAT_FLOUR;
/* 42 */     SlimefunItem laTiao = new SlimefunItem(this.food, laTiao_, RecipeType.COMPRESSOR, itemStackArray2);
/* 43 */     SlimefunItem fangBianMian = new SlimefunItem(this.food, fangBianMian_, RecipeType.MAGIC_WORKBENCH, new ItemStack[] { new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.WATER_BUCKET), Stuff.ksf_, Stuff.ksf_, Stuff.ksf_, SlimefunItems.WHEAT_FLOUR, SlimefunItems.WHEAT_FLOUR, SlimefunItems.WHEAT_FLOUR });
/* 44 */     SlimefunItem xueBi = new SlimefunItem(this.food, xueBi_, RecipeType.MAGIC_WORKBENCH, new ItemStack[] { Stuff.waterSugar_, new ItemStack(Material.WATER_BUCKET), Stuff.waterSugar_, new ItemStack(Material.WATER_BUCKET), Stuff.waterSugar_, new ItemStack(Material.WATER_BUCKET), Stuff.waterSugar_, new ItemStack(Material.WATER_BUCKET), Stuff.waterSugar_ });
/* 45 */     SlimefunItem keLe = new SlimefunItem(this.food, keLe_, RecipeType.MAGIC_WORKBENCH, new ItemStack[] { Stuff.waterSugar_, new ItemStack(Material.WATER_BUCKET), Stuff.waterSugar_, new ItemStack(Material.WATER_BUCKET), SlimefunItems.MAGIC_SUGAR, new ItemStack(Material.WATER_BUCKET), Stuff.waterSugar_, new ItemStack(Material.WATER_BUCKET), Stuff.waterSugar_ });
/* 46 */     new Register( xueBi, keLe, kouXiangTang, fangBianMian, laTiao );
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\bump测试\【魔法】Bump_v1.0.jar!\bxx2004\bump\sf\Food.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */