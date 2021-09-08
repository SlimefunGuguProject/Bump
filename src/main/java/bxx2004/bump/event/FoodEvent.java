 package bxx2004.bump.event;
 


import bxx2004.bump.sf.Food;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;



 public class FoodEvent
   implements Listener {
   @EventHandler
   public void onRight(PlayerInteractEvent event) {
     try {
       if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
         Player p = event.getPlayer();
         if (event.getItem().getItemMeta().equals(Food.xueBi_.getItemMeta())) {
           p.setFoodLevel(20);
           Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o清凉一夏!", false);
           p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
         } 
         if (event.getItem().getItemMeta().equals(Food.keLe_.getItemMeta())) {
           Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o仙气飘飘!", false);
           p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2000, 5));
           p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
         } 
         if (event.getItem().getItemMeta().equals(Food.kouXiangTang_.getItemMeta())) {
           Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o仙气飘飘!", false);
           p.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 2000, 5));
           p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
         } 
         if (event.getItem().getItemMeta().equals(Food.laTiao_.getItemMeta())) {
           Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o仙气飘飘!", false);
           p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 2000, 5));
           p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
         } 
         if (event.getItem().getItemMeta().equals(Food.fangBianMian_.getItemMeta())) {
           Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o仙气飘飘!", false);
           p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
         } 
       } 
     } catch (Exception exception) {}
   }
 }
