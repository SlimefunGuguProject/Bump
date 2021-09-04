/*    */ package bxx2004.bump.sf;
/*    */ 
/*    */

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

/*    */ //import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Tools
/*    */ {
/*    */   public static SlimefunItemStack appraisalPaperDamage_;
/*    */   public static SlimefunItemStack appraisalPaperArmor_;
/* 24 */   public static SlimefunItemStack getGoldSpade_ = (new SfItemStackCreate("GETGOLD_SPADE", Material.GOLDEN_SHOVEL, "&e摸金铲", new String[] { "", "&b&k|&b- 在沙子里一定几率挖出破损的金币!", "" },  "MENDING-3" )).get();
/*    */   
/*    */   public ItemGroup tools;
/*    */   
/*    */   static {
/* 29 */     appraisalPaperArmor_ = (new SfItemStackCreate("APPRAISAL_PAPER_ARMOR", Material.PAPER, "&e护甲品质鉴定符", new String[] { "", "&b&k|&b- 给定物品随机一个护甲属性!", "&b&k|&b- 非常昂贵,一定要保存好...", "" })).get();
/* 30 */     appraisalPaperDamage_ = (new SfItemStackCreate("APPRAISAL_PAPER_DAMAGE", Material.PAPER, "&e武器品质鉴定符", new String[] { "", "&b&k|&b- 给定物品随机一个攻击属性!", "&b&k|&b- 非常昂贵,一定要保存好...", "" })).get();
/*    */   }
/*    */   
/*    */   public Tools(SlimefunAddon plugin) {
/* 34 */     this.tools = new ItemGroup(new NamespacedKey(Bump.getPlugin(Bump.class), "Tools"), new CustomItemStack(Material.DIAMOND_PICKAXE, "&bBump-工具",  "", "&b&k|&b- 点击打开 >", "", "&7工具,工具!", "他们可能含有未知的力量..." ));
/* 35 */     this.tools.register(plugin);
/* 36 */     SlimefunItem appraisalPaperArmor = new SlimefunItem(this.tools, appraisalPaperArmor_, RecipeType.SMELTERY, new ItemStack[] { Armor.randomHelmet_ });
/* 37 */     SlimefunItem appraisalPaperDamage = new SlimefunItem(this.tools, appraisalPaperDamage_, RecipeType.SMELTERY, new ItemStack[] { Weapon.randomSword_ });
/* 38 */     SlimefunItem getgoldSpade = new SlimefunItem(this.tools, getGoldSpade_, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, SlimefunItems.GOLD_24K, new ItemStack(Material.STICK), new ItemStack(Material.STICK) });
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\bump测试\【魔法】Bump_v1.0.jar!\bxx2004\bump\sf\Tools.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */