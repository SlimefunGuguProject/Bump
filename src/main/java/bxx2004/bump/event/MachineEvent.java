package bxx2004.bump.event;

import bxx2004.bump.slimefun.Machine;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MachineEvent
    implements Listener {
    @EventHandler
    public void onRightBlock(PlayerRightClickEvent event) {
        Player p = event.getPlayer();
        try {
            if (event.getSlimefunBlock().isPresent() && (event.getSlimefunBlock().get()).equals(Machine.appraisal))
                if (event.getItem().getItemMeta().getLore().contains("§b§k|§b- §7§o武器尚未鉴定...") || event.getItem().getItemMeta().getLore().contains("§b§k|§b- §7§o护甲尚未鉴定...")) {
                    Random r = new Random();
                    double ra = r.nextDouble() * 20.0D;
                    double ra1 = r.nextDouble() * 20.0D;
                    double ra3 = r.nextDouble() * 20.0D;
                    ItemMeta meta = event.getItem().getItemMeta();
                    if (event.getItem().getItemMeta().getLore().contains("§b§k|§b- §7§o武器尚未鉴定...")) {
                        EquipmentSlot equipmentSlot;
                        if (ra3 <= 10.0D) {
                            equipmentSlot = EquipmentSlot.HAND;
                        } else {
                            equipmentSlot = EquipmentSlot.OFF_HAND;
                        }
                        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "随机属性", ra, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot);
                        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "随机属性1", ra1, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot);
                        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
                        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
                        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
                        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier1);
                    } else if (event.getItem().getItemMeta().getLore().contains("§b§k|§b- §7§o护甲尚未鉴定...")) {
                        EquipmentSlot equipmentSlot;
                        if (ra3 <= 5.0D) {
                            equipmentSlot = EquipmentSlot.HEAD;
                        } else if (ra3 <= 10.0D) {
                            equipmentSlot = EquipmentSlot.CHEST;
                        } else if (ra3 <= 15.0D) {
                            equipmentSlot = EquipmentSlot.LEGS;
                        } else {
                            equipmentSlot = EquipmentSlot.FEET;
                        }
                        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "随机属性", ra, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot);
                        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "随机属性1", ra1, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot);
                        meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
                        meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
                        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier);
                        meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, modifier1);
                    }
                    List<String> list = new ArrayList<>();
                    Iterator<String> iterator = meta.getLore().iterator();
                    meta.setLore(list);
                    while (iterator.hasNext()) {
                        String next = iterator.next();
                        list.remove("§b§k|§b- §7§o已鉴定");
                        list.add(next.replaceAll("§7§o武器尚未鉴定...", "§7§o已鉴定").replaceAll("§7§o护甲尚未鉴定...", "§7§o已鉴定"));
                        list.remove("§b§k|§b- §7§o品质: §c⭐⭐⭐⭐⭐");
                        list.remove("§b§k|§b- §7§o品质: §c⭐⭐⭐⭐");
                        list.remove("§b§k|§b- §7§o品质: §c⭐⭐⭐");
                        list.remove("§b§k|§b- §7§o品质: §c⭐⭐");
                        list.remove("§b§k|§b- §7§o品质: §c⭐");
                        list.remove("");
                        list.remove("§b§k|§b- §7§o已经鉴定过了!");
                    }
                    list.add("");
                    if (ra >= 15.0D) {
                        list.add("§b§k|§b- §7§o品质: §c⭐⭐⭐⭐⭐");
                    } else if (ra < 15.0D && ra >= 10.0D) {
                        list.add("§b§k|§b- §7§o品质: §c⭐⭐⭐⭐");
                    } else if (ra < 10.0D && ra >= 7.0D) {
                        list.add("§b§k|§b- §7§o品质: §c⭐⭐⭐");
                    } else if (ra < 7.0D && ra >= 5.0D) {
                        list.add("§b§k|§b- §7§o品质: §c⭐⭐");
                    } else {
                        list.add("§b§k|§b- §7§o品质: §c⭐");
                    }
                    list.add("");
                    meta.setLore(list);
                    event.getItem().setItemMeta(meta);
                    p.sendTitle("§b§k|§b- §7§o鉴定成功!", null, 10, 70, 20);
                } else {
                    p.sendTitle("§b§k|§b- §7§o对不起该物品无法鉴定或已经鉴定过了!", null, 10, 70, 20);
                }
        } catch (Exception exception) {
        }
    }
}
