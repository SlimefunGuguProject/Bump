/*    */ package bxx2004.bump.event;
/*    */ 
/*    */

import bxx2004.bump.sf.Food;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class FoodEvent
/*    */   implements Listener {
/*    */   @EventHandler
/*    */   public void onRight(PlayerInteractEvent event) {
/*    */     try {
/* 18 */       if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
/* 19 */         Player p = event.getPlayer();
/* 20 */         if (event.getItem().getItemMeta().equals(Food.xueBi_.getItemMeta())) {
/* 21 */           p.setFoodLevel(20);
/* 22 */           Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o清凉一夏!", false);
/* 23 */           p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
/*    */         } 
/* 25 */         if (event.getItem().getItemMeta().equals(Food.keLe_.getItemMeta())) {
/* 26 */           Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o仙气飘飘!", false);
/* 27 */           p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2000, 5));
/* 28 */           p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
/*    */         } 
/* 30 */         if (event.getItem().getItemMeta().equals(Food.kouXiangTang_.getItemMeta())) {
/* 31 */           Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o仙气飘飘!", false);
/* 32 */           p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2000, 5));
/* 33 */           p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
/*    */         } 
/* 35 */         if (event.getItem().getItemMeta().equals(Food.laTiao_.getItemMeta())) {
/* 36 */           Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o仙气飘飘!", false);
/* 37 */           p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 2000, 5));
/* 38 */           p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
/*    */         } 
/* 40 */         if (event.getItem().getItemMeta().equals(Food.fangBianMian_.getItemMeta())) {
/* 41 */           Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o仙气飘飘!", false);
/* 42 */           p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
/*    */         } 
/*    */       } 
/* 45 */     } catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\bump测试\【魔法】Bump_v1.0.jar!\bxx2004\bump\event\FoodEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */