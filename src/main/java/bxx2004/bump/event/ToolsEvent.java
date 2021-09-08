 package bxx2004.bump.event;
 


import bxx2004.bump.sf.Stuff;
import bxx2004.bump.sf.Tools;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



 public class ToolsEvent
   implements Listener
 {
   @EventHandler
   public void onRight(PlayerInteractEvent event) {
     try {
       if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && 
         event.getItem().getItemMeta().getLore().contains("§b§k|§b- 非常昂贵,一定要保存好...")) {
         Player p = event.getPlayer();
         Inventory inventory = Bukkit.createInventory(null, 27, "合成界面");
         for (int i = 0; i < 27; i++) {
           if (i == 10 || i == 13 || i == 16) {
             inventory.setItem(13, new CustomItemStack(Material.GREEN_STAINED_GLASS_PANE, "&a确认鉴定",  "&e<- &b左面放入鉴定符", "&e-> &b右面放入物品" ));
           } else {
             inventory.setItem(i, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, "---"));
           } 
         } 
         p.openInventory(inventory);
       } 
     } catch (Exception exception) {}
   }
   
   @EventHandler
   public void onClick(InventoryClickEvent event) {
     try {
       if (event.getView().getTitle().equalsIgnoreCase("合成界面") && event.getInventory().getSize() == 27) {
         if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("---") || event.getRawSlot() == 13)
           event.setCancelled(true); 
         Player p = (Player)event.getWhoClicked();
         if (event.getInventory().getItem(10).getItemMeta() != null && event.getInventory().getItem(16).getItemMeta() != null && event.getInventory().getItem(10).getItemMeta().equals(Tools.appraisalPaperDamage_.getItemMeta()) && 
           event.getRawSlot() == 13) {
           ItemStack itemStack = event.getInventory().getItem(16);
           ItemMeta meta = itemStack.getItemMeta();
           List<String> list = new ArrayList<>();
           if (itemStack.getItemMeta().hasLore()) {
             list.addAll(meta.getLore());
           }
           if (itemStack.getItemMeta().getLore().contains("§b§k|§b- §7§o已鉴定")) {
             p.sendTitle("§b§k|§b- §7§o对不起该物品无法鉴定或已经鉴定过了!", null, 10, 70, 20);
             event.getInventory().clear();
             p.closeInventory();
             p.getInventory().addItem( itemStack );
             p.getInventory().addItem( Tools.appraisalPaperDamage_ );
           } else {
             meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
             meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
             list.remove("§b§k|§b- §7§o品质: §c⭐⭐⭐⭐⭐");
             list.remove("§b§k|§b- §7§o品质: §c⭐⭐⭐⭐");
             list.remove("§b§k|§b- §7§o品质: §c⭐⭐⭐");
             list.remove("§b§k|§b- §7§o品质: §c⭐⭐");
             list.remove("§b§k|§b- §7§o品质: §c⭐");
             list.remove("");
             list.remove("§b§k|§b- §7§o已经鉴定过了!");
             list.add("");
             list.add("§b§k|§b- §7§o武器尚未鉴定...");
             meta.setLore(list);
             itemStack.setItemMeta(meta);
             p.getInventory().addItem( itemStack );
             event.getInventory().clear();
             p.closeInventory();
             Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o鉴定成功!", false);
           } 
         } 
         if (event.getInventory().getItem(10).getItemMeta() != null && event.getInventory().getItem(16).getItemMeta() != null && event.getInventory().getItem(10).getItemMeta().equals(Tools.appraisalPaperArmor_.getItemMeta()) && 
           event.getRawSlot() == 13) {
           ItemStack itemStack = event.getInventory().getItem(16);
           ItemMeta meta = itemStack.getItemMeta();
           List<String> list = new ArrayList<>();
           if (itemStack.getItemMeta().hasLore()) {
             list.addAll(meta.getLore());
           }
           if (itemStack.getItemMeta().getLore().contains("§b§k|§b- §7§o已鉴定")) {
             p.sendTitle("§b§k|§b- §7§o对不起该物品无法鉴定或已经鉴定过了!", null, 10, 70, 20);
             event.getInventory().clear();
             p.closeInventory();
             p.getInventory().addItem(itemStack );
             p.getInventory().addItem( Tools.appraisalPaperArmor_ );
           } else {
             meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
             meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
             list.remove("§b§k|§b- §7§o品质: §c⭐⭐⭐⭐⭐");
             list.remove("§b§k|§b- §7§o品质: §c⭐⭐⭐⭐");
             list.remove("§b§k|§b- §7§o品质: §c⭐⭐⭐");
             list.remove("§b§k|§b- §7§o品质: §c⭐⭐");
             list.remove("§b§k|§b- §7§o品质: §c⭐");
             list.remove("");
             list.remove("§b§k|§b- §7§o已经鉴定过了!");
             list.add("");
             list.add("§b§k|§b- §7§o护甲尚未鉴定...");
             meta.setLore(list);
             itemStack.setItemMeta(meta);
             p.getInventory().addItem( itemStack );
             event.getInventory().clear();
             p.closeInventory();
             Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o鉴定成功!", false);
           } 
         } 
       } 
     } catch (Exception exception) {}
   }
   
   @EventHandler
   public void onBreak(BlockBreakEvent event) {
     try {
       Player p = event.getPlayer();
       if (p.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§b§k|§b- 在沙子里一定几率挖出破损的金币!") && 
         event.getBlock().getType().equals(Material.SAND)) {
         Random random = new Random();
         if (random.nextInt(4) == 0) {
           event.setDropItems(false);
           p.getWorld().dropItem(event.getBlock().getLocation(), Stuff.oldCoin_);
           Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oOMG, 金子...", false);
         } 
       } 
     } catch (Exception exception) {}
   }
 }
