/*    */ package bxx2004.bump.sf;
/*    */ 
/*    */ import bxx2004.bump.Bump;
/*    */ import bxx2004.bump.util.SfItemStackCreate;
/*    */ import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
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
/*    */ public class Armor
/*    */ {
/* 19 */   public static SlimefunItemStack randomHelmet_ = (new SfItemStackCreate("RANDOM_HELMET", Material.DIAMOND_HELMET, "&6随机头盔", new String[] { "", "&b&k|&b- &7&o我的运气怎么样...", "&b&k|&b- &e&o鉴定仪 &7&o随机给予此一个属性...", "§b§k|§b- §7§o护甲尚未鉴定..." })).get();
/*    */   
/*    */   public Category Armor;
/*    */   
/*    */   public Armor(SlimefunAddon plugin) {
/* 24 */     this.Armor = new Category(new NamespacedKey(Bump.getPlugin(Bump.class), "Armor"), new CustomItem(Material.DIAMOND_HELMET, "&bBump-护甲", "", "&b&k|&b- 点击打开 >", "", "&7护甲,护甲!", "他们可能含有未知的力量..." ));
/* 25 */     this.Armor.register(plugin);
/* 26 */     SlimefunItem randomHelmet = new SlimefunItem(this.Armor, randomHelmet_, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { Stuff.oldCoin_, Stuff.oldCoin_, Stuff.oldCoin_, Stuff.make_, Stuff.oldCoin_, Stuff.oldCoin_, Stuff.oldCoin_, Stuff.oldCoin_ });
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\bump测试\【魔法】Bump_v1.0.jar!\bxx2004\bump\sf\Armor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */