/*    */ package bxx2004.bump.sf;
/*    */ 
/*    */ import bxx2004.bump.Bump;
/*    */ import bxx2004.bump.util.SfItemStackCreate;
/*    */ import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
/*    */ import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
/*    */ import me.mrCookieSlime.Slimefun.Lists.RecipeType;
/*    */ import me.mrCookieSlime.Slimefun.Objects.Category;
/*    */ import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*    */ import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ //import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Machine
/*    */ {
/*    */   public static SlimefunItem appraisal;
/*    */   public Category machine;
/*    */   
/*    */   public Machine(SlimefunAddon plugin) {
/* 24 */     this.machine = new Category(new NamespacedKey(Bump.getPlugin(Bump.class), "Machine"), new CustomItem(Material.ANVIL, "&bBump-机器",  "", "&b&k|&b- 点击打开 >", "", "&7我们还可以继续工作!", "兹拉,兹拉,滋滋滋..." ));
/* 25 */     this.machine.register(plugin);
/* 26 */     appraisal = new SlimefunItem(this.machine, (new SfItemStackCreate("APPRAISAL", Material.BELL, "&B鉴定仪", new String[] { "", "&c&k|&7- 哦,你这是个残次品...", "" })).get(), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { SlimefunItems.BATTERY, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.BATTERY, Stuff.mechaGeat_, Stuff.CPU_, Stuff.mechaGeat_, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.COOLING_UNIT,SlimefunItems.ADVANCED_CIRCUIT_BOARD });
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\bump测试\【魔法】Bump_v1.0.jar!\bxx2004\bump\sf\Machine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */