/*    */ package bxx2004.bump.util;
/*    */ 
/*    */

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;

/*    */
/*    */
/*    */

/*    */
/*    */ public class SfItemStackCreate {
/*    */   private SlimefunItemStack itemStack;
/*    */   
/*    */   public SlimefunItemStack get() {
/* 12 */     return this.itemStack;
/*    */   }
/*    */   
/*    */   public SfItemStackCreate(String id, Material material, String displayName, String[] lore, String... enchants) {
/* 16 */     SlimefunItemStack customItem = new SlimefunItemStack(id, material, displayName, lore);
/* 17 */     ItemMeta meta = customItem.getItemMeta();
/* 18 */     if (enchants != null) {
/* 19 */       for (int i = 0; i < enchants.length; i++) {
/* 20 */         String ench = enchants[i];
/* 21 */         String[] strs = ench.split("[-]");
/* 22 */         meta.addEnchant(Enchantment.getByName(ench.substring(0, ench.indexOf("-"))), Integer.parseInt(strs[1]), true);
/*    */       } 
/* 24 */       customItem.setItemMeta(meta);
/*    */     } 
/* 26 */     this.itemStack = customItem;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\bump测试\【魔法】Bump_v1.0.jar!\bxx2004\bum\\util\SfItemStackCreate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */