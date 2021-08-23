/*     */ package bxx2004.bump.event;
/*     */ 
/*     */ import bxx2004.bump.sf.Stuff;
/*     */ import bxx2004.bump.sf.Tools;
/*     */ import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ 
/*     */ public class ToolsEvent
/*     */   implements Listener
/*     */ {
/*     */   @EventHandler
/*     */   public void onRight(PlayerInteractEvent event) {
/*     */     try {
/*  30 */       if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && 
/*  31 */         event.getItem().getItemMeta().getLore().contains("§b§k|§b- 非常昂贵,一定要保存好...")) {
/*  32 */         Player p = event.getPlayer();
/*  33 */         Inventory inventory = Bukkit.createInventory(null, 27, "合成界面");
/*  34 */         for (int i = 0; i < 27; i++) {
/*  35 */           if (i == 10 || i == 13 || i == 16) {
/*  36 */             inventory.setItem(13, new CustomItem(Material.GREEN_STAINED_GLASS_PANE, "&a确认鉴定",  "&e<- &b左面放入鉴定符", "&e-> &b右面放入物品" ));
/*     */           } else {
/*  38 */             inventory.setItem(i, new CustomItem(Material.BLACK_STAINED_GLASS_PANE, "---"));
/*     */           } 
/*     */         } 
/*  41 */         p.openInventory(inventory);
/*     */       } 
/*  43 */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onClick(InventoryClickEvent event) {
/*     */     try {
/*  49 */       if (event.getView().getTitle().equalsIgnoreCase("合成界面") && event.getInventory().getSize() == 27) {
/*  50 */         if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("---") || event.getRawSlot() == 13)
/*  51 */           event.setCancelled(true); 
/*  52 */         Player p = (Player)event.getWhoClicked();
/*  53 */         if (event.getInventory().getItem(10).getItemMeta() != null && event.getInventory().getItem(16).getItemMeta() != null && event.getInventory().getItem(10).getItemMeta().equals(Tools.appraisalPaperDamage_.getItemMeta()) && 
/*  54 */           event.getRawSlot() == 13) {
/*  55 */           ItemStack itemStack = event.getInventory().getItem(16);
/*  56 */           ItemMeta meta = itemStack.getItemMeta();
/*  57 */           List<String> list = new ArrayList<>();
/*  58 */           if (itemStack.getItemMeta().hasLore()) {
/*  59 */             list.addAll(meta.getLore());
/*     */           }
/*  61 */           if (itemStack.getItemMeta().getLore().contains("§b§k|§b- §7§o已鉴定")) {
/*  62 */             p.sendTitle("§b§k|§b- §7§o对不起该物品无法鉴定或已经鉴定过了!", null, 10, 70, 20);
/*  63 */             event.getInventory().clear();
/*  64 */             p.closeInventory();
/*  65 */             p.getInventory().addItem( itemStack );
/*  66 */             p.getInventory().addItem( Tools.appraisalPaperDamage_ );
/*     */           } else {
/*  68 */             meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
/*  69 */             meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
/*  70 */             list.remove("§b§k|§b- §7§o品质: §c⭐⭐⭐⭐⭐");
/*  71 */             list.remove("§b§k|§b- §7§o品质: §c⭐⭐⭐⭐");
/*  72 */             list.remove("§b§k|§b- §7§o品质: §c⭐⭐⭐");
/*  73 */             list.remove("§b§k|§b- §7§o品质: §c⭐⭐");
/*  74 */             list.remove("§b§k|§b- §7§o品质: §c⭐");
/*  75 */             list.remove("");
/*  76 */             list.remove("§b§k|§b- §7§o已经鉴定过了!");
/*  77 */             list.add("");
/*  78 */             list.add("§b§k|§b- §7§o武器尚未鉴定...");
/*  79 */             meta.setLore(list);
/*  80 */             itemStack.setItemMeta(meta);
/*  81 */             p.getInventory().addItem( itemStack );
/*  82 */             event.getInventory().clear();
/*  83 */             p.closeInventory();
/*  84 */             SlimefunPlugin.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o鉴定成功!", false);
/*     */           } 
/*     */         } 
/*  87 */         if (event.getInventory().getItem(10).getItemMeta() != null && event.getInventory().getItem(16).getItemMeta() != null && event.getInventory().getItem(10).getItemMeta().equals(Tools.appraisalPaperArmor_.getItemMeta()) && 
/*  88 */           event.getRawSlot() == 13) {
/*  89 */           ItemStack itemStack = event.getInventory().getItem(16);
/*  90 */           ItemMeta meta = itemStack.getItemMeta();
/*  91 */           List<String> list = new ArrayList<>();
/*  92 */           if (itemStack.getItemMeta().hasLore()) {
/*  93 */             list.addAll(meta.getLore());
/*     */           }
/*  95 */           if (itemStack.getItemMeta().getLore().contains("§b§k|§b- §7§o已鉴定")) {
/*  96 */             p.sendTitle("§b§k|§b- §7§o对不起该物品无法鉴定或已经鉴定过了!", null, 10, 70, 20);
/*  97 */             event.getInventory().clear();
/*  98 */             p.closeInventory();
/*  99 */             p.getInventory().addItem(itemStack );
/* 100 */             p.getInventory().addItem( Tools.appraisalPaperArmor_ );
/*     */           } else {
/* 102 */             meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
/* 103 */             meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
/* 104 */             list.remove("§b§k|§b- §7§o品质: §c⭐⭐⭐⭐⭐");
/* 105 */             list.remove("§b§k|§b- §7§o品质: §c⭐⭐⭐⭐");
/* 106 */             list.remove("§b§k|§b- §7§o品质: §c⭐⭐⭐");
/* 107 */             list.remove("§b§k|§b- §7§o品质: §c⭐⭐");
/* 108 */             list.remove("§b§k|§b- §7§o品质: §c⭐");
/* 109 */             list.remove("");
/* 110 */             list.remove("§b§k|§b- §7§o已经鉴定过了!");
/* 111 */             list.add("");
/* 112 */             list.add("§b§k|§b- §7§o护甲尚未鉴定...");
/* 113 */             meta.setLore(list);
/* 114 */             itemStack.setItemMeta(meta);
/* 115 */             p.getInventory().addItem( itemStack );
/* 116 */             event.getInventory().clear();
/* 117 */             p.closeInventory();
/* 118 */             SlimefunPlugin.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o鉴定成功!", false);
/*     */           } 
/*     */         } 
/*     */       } 
/* 122 */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBreak(BlockBreakEvent event) {
/*     */     try {
/* 128 */       Player p = event.getPlayer();
/* 129 */       if (p.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§b§k|§b- 在沙子里一定几率挖出破损的金币!") && 
/* 130 */         event.getBlock().getType().equals(Material.SAND)) {
/* 131 */         Random random = new Random();
/* 132 */         if (random.nextInt(4) == 0) {
/* 133 */           event.setDropItems(false);
/* 134 */           p.getWorld().dropItem(event.getBlock().getLocation(), Stuff.oldCoin_);
/* 135 */           SlimefunPlugin.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oOMG, 金子...", false);
/*     */         } 
/*     */       } 
/* 138 */     } catch (Exception exception) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\bump测试\【魔法】Bump_v1.0.jar!\bxx2004\bump\event\ToolsEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */