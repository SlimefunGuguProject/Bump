/*    */ package bxx2004.bump.util;
/*    */ 
/*    */

import bxx2004.bump.Bump;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class Register
/*    */   implements SlimefunAddon
/*    */ {
/*    */   @Nonnull
/*    */   public JavaPlugin getJavaPlugin() {
/* 15 */     return Bump.getPlugin(Bump.class);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String getBugTrackerURL() {
/* 20 */     return "bxx2004";
/*    */   }
/*    */   
/*    */   public Register(SlimefunItem... slimefunItems) {
/* 24 */     for (int i = 0; i < slimefunItems.length; i++)
/* 25 */       slimefunItems[i].register(this); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\bump测试\【魔法】Bump_v1.0.jar!\bxx2004\bum\\util\Register.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */