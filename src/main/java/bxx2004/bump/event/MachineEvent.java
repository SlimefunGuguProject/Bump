/*    */ package bxx2004.bump.event;
/*    */ 
/*    */ import bxx2004.bump.sf.Machine;
/*    */ import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import java.util.UUID;
/*    */ //import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
/*    */ import org.bukkit.attribute.Attribute;
/*    */ import org.bukkit.attribute.AttributeModifier;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.inventory.EquipmentSlot;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ public class MachineEvent
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onRightBlock(PlayerRightClickEvent event) {
/* 24 */     Player p = event.getPlayer();
/*    */     try {
/* 26 */       if (event.getSlimefunBlock().isPresent() && (event.getSlimefunBlock().get()).equals(Machine.appraisal))
/* 27 */         if (event.getItem().getItemMeta().getLore().contains("§b§k|§b- §7§o武器尚未鉴定...") || event.getItem().getItemMeta().getLore().contains("§b§k|§b- §7§o护甲尚未鉴定...")) {
/* 28 */           Random r = new Random();
/* 29 */           double ra = r.nextDouble() * 20.0D;
/* 30 */           double ra1 = r.nextDouble() * 20.0D;
/* 31 */           double ra3 = r.nextDouble() * 20.0D;
/* 32 */           ItemMeta meta = event.getItem().getItemMeta();
/* 33 */           if (event.getItem().getItemMeta().getLore().contains("§b§k|§b- §7§o武器尚未鉴定...")) {
/*    */             EquipmentSlot equipmentSlot;
/* 35 */             if (ra3 <= 10.0D) {
/* 36 */               equipmentSlot = EquipmentSlot.HAND;
/*    */             } else {
/* 38 */               equipmentSlot = EquipmentSlot.OFF_HAND;
/*    */             } 
/* 40 */             AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "随机属性", ra, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot);
/* 41 */             AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "随机属性1", ra1, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot);
/* 42 */             meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
/* 43 */             meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
/* 44 */             meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
/* 45 */             meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier1);
/* 46 */           } else if (event.getItem().getItemMeta().getLore().contains("§b§k|§b- §7§o护甲尚未鉴定...")) {
/*    */             EquipmentSlot equipmentSlot;
/* 48 */             if (ra3 <= 5.0D) {
/* 49 */               equipmentSlot = EquipmentSlot.HEAD;
/* 50 */             } else if (ra3 <= 10.0D) {
/* 51 */               equipmentSlot = EquipmentSlot.CHEST;
/* 52 */             } else if (ra3 <= 15.0D) {
/* 53 */               equipmentSlot = EquipmentSlot.LEGS;
/*    */             } else {
/* 55 */               equipmentSlot = EquipmentSlot.FEET;
/*    */             } 
/* 57 */             AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "随机属性", ra, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot);
/* 58 */             AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "随机属性1", ra1, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot);
/* 59 */             meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
/* 60 */             meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
/* 61 */             meta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier);
/* 62 */             meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, modifier1);
/*    */           } 
/* 64 */           List<String> list = new ArrayList<>();
/* 65 */           Iterator<String> iterator = meta.getLore().iterator();
/* 66 */           meta.setLore(list);
/* 67 */           while (iterator.hasNext()) {
/* 68 */             String next = iterator.next();
/* 69 */             list.remove("§b§k|§b- §7§o已鉴定");
/* 70 */             list.add(next.replaceAll("§7§o武器尚未鉴定...", "§7§o已鉴定").replaceAll("§7§o护甲尚未鉴定...", "§7§o已鉴定"));
/* 71 */             list.remove("§b§k|§b- §7§o品质: §c⭐⭐⭐⭐⭐");
/* 72 */             list.remove("§b§k|§b- §7§o品质: §c⭐⭐⭐⭐");
/* 73 */             list.remove("§b§k|§b- §7§o品质: §c⭐⭐⭐");
/* 74 */             list.remove("§b§k|§b- §7§o品质: §c⭐⭐");
/* 75 */             list.remove("§b§k|§b- §7§o品质: §c⭐");
/* 76 */             list.remove("");
/* 77 */             list.remove("§b§k|§b- §7§o已经鉴定过了!");
/*    */           } 
/* 79 */           list.add("");
/* 80 */           if (ra >= 15.0D) {
/* 81 */             list.add("§b§k|§b- §7§o品质: §c⭐⭐⭐⭐⭐");
/* 82 */           } else if (ra < 15.0D && ra >= 10.0D) {
/* 83 */             list.add("§b§k|§b- §7§o品质: §c⭐⭐⭐⭐");
/* 84 */           } else if (ra < 10.0D && ra >= 7.0D) {
/* 85 */             list.add("§b§k|§b- §7§o品质: §c⭐⭐⭐");
/* 86 */           } else if (ra < 7.0D && ra >= 5.0D) {
/* 87 */             list.add("§b§k|§b- §7§o品质: §c⭐⭐");
/*    */           } else {
/* 89 */             list.add("§b§k|§b- §7§o品质: §c⭐");
/*    */           } 
/* 91 */           list.add("");
/* 92 */           meta.setLore(list);
/* 93 */           event.getItem().setItemMeta(meta);
/* 94 */           p.sendTitle("§b§k|§b- §7§o鉴定成功!", null, 10, 70, 20);
/*    */         } else {
/* 96 */           p.sendTitle("§b§k|§b- §7§o对不起该物品无法鉴定或已经鉴定过了!", null, 10, 70, 20);
/*    */         }  
/* 98 */     } catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\bump测试\【魔法】Bump_v1.0.jar!\bxx2004\bump\event\MachineEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */