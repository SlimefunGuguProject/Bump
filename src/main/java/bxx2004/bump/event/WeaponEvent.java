/*     */ package bxx2004.bump.event;
/*     */ 
/*     */ import bxx2004.bump.Bump;
/*     */ import bxx2004.bump.sf.Weapon;
/*     */ import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.entity.DragonFireball;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.SmallFireball;
/*     */ import org.bukkit.entity.WitherSkull;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.entity.EntityShootBowEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ //import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class WeaponEvent
/*     */   implements Listener {
/*     */   @EventHandler
/*     */   public void onRight(PlayerInteractEvent event) {
/*     */     try {
/*  27 */       if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
/*  28 */         final Player p = event.getPlayer();
/*  29 */         if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Weapon.soulSword_.getItemMeta().getDisplayName()) && event.getItem().getItemMeta().getLore().containsAll(Weapon.soulSword_.getItemMeta().getLore())) {
/*  30 */           int health = (int)p.getHealth();
/*  31 */           int healths = (int)p.getHealthScale();
/*  32 */           int foodLevel = p.getFoodLevel();
/*  33 */           if (health == healths || foodLevel == 0) {
/*  34 */             Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o你现在无法发动该技能!", false);
/*  35 */           } else if (foodLevel < 2) {
/*  36 */             Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o你的饱食度不足以转换生命值!", false);
/*  37 */           } else if (foodLevel > 2 && healths - health <= foodLevel) {
/*  38 */             Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o已将你的饱食度部分转换为生命值!", false);
/*  39 */             p.setFoodLevel(foodLevel - healths - health);
/*  40 */             p.setHealth((health + healths - health));
/*  41 */           } else if (healths - health > foodLevel) {
/*  42 */             Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o已将你的饱食度全部转换生命值!", false);
/*  43 */             p.setHealth((health + foodLevel));
/*  44 */             p.setFoodLevel(0);
/*     */           } 
/*     */         } 
/*  47 */         if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Weapon.devilSword_.getItemMeta().getDisplayName()) && event.getItem().getItemMeta().getLore().containsAll(Weapon.devilSword_.getItemMeta().getLore()))
/*  48 */           if (p.getFoodLevel() < 5) {
/*  49 */             Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o你现在无法发动该技能!", false);
/*     */           } else {
/*  51 */             p.setFoodLevel(p.getFoodLevel() - 5);
/*  52 */             Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o斩魔, 一刀致命!", false);
/*  53 */             for (int i = 0; i < 20; i++) {
/*  54 */               p.launchProjectile(SmallFireball.class);
/*  55 */               Particle particle = Particle.ENCHANTMENT_TABLE;
/*  56 */               p.spawnParticle(particle, p.getLocation(), 1);
/*     */             } 
/*     */           }  
/*  59 */         if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Weapon.skySword_.getItemMeta().getDisplayName()) && event.getItem().getItemMeta().getLore().containsAll(Weapon.skySword_.getItemMeta().getLore()))
/*  60 */           if (p.getFoodLevel() < 5) {
/*  61 */             Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o你现在无法发动该技能!", false);
/*     */           } else {
/*  63 */             p.setFoodLevel(p.getFoodLevel() - 5);
/*  64 */             Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o破天, 一飞冲天!", false);
/*  65 */             Vector direction = p.getLocation().toVector();
/*  66 */             p.setVelocity(p.getVelocity().add(direction.multiply(0.5D)));
/*  67 */             for (int i = 0; i < 20; i++) {
/*  68 */               Particle particle = Particle.EXPLOSION_HUGE;
/*  69 */               p.spawnParticle(particle, p.getLocation(), 1);
/*     */             } 
/*     */           }  
/*  72 */         if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Weapon.skydevilSword_.getItemMeta().getDisplayName()) && event.getItem().getItemMeta().getLore().containsAll(Weapon.skydevilSword_.getItemMeta().getLore()))
/*  73 */           if (p.getFoodLevel() < 5) {
/*  74 */             Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o你现在无法发动该技能!", false);
/*     */           } else {
/*  76 */             p.setFoodLevel(p.getFoodLevel() - 5);
/*  77 */             Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o破天斩魔,天降正义!", false);
/*  78 */             p.setGlowing(true);
/*  79 */             p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 3));
/*  80 */             p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 3));
/*  81 */             (new BukkitRunnable() {
/*  82 */                 int i = 0;
/*     */                 
/*     */                 public void run() {
/*  85 */                   if (this.i < 3) {
/*  86 */                     p.launchProjectile(DragonFireball.class);
/*  87 */                     this.i++;
/*     */                   } else {
/*  89 */                     cancel();
/*  90 */                     this.i = 0;
/*  91 */                     p.setGlowing(false);
/*  92 */                     Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o强化时间已经结束!", false);
/*  93 */                     p.removePotionEffect(PotionEffectType.SPEED);
/*     */                   } 
/*     */                 }
/*  96 */               }).runTaskTimer(Bump.getPlugin(Bump.class), 0L, 100L);
/*     */           }  
/*     */       } 
/*  99 */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onRightBow(EntityShootBowEvent event) {
/* 104 */     if (event.getEntity() instanceof Player) {
/* 105 */       Player p = (Player)event.getEntity();
/* 106 */       if (event.getBow().getItemMeta().getDisplayName().equalsIgnoreCase(Weapon.witherSkullRow_.getItemMeta().getDisplayName()) && event.getBow().getItemMeta().getLore().containsAll(Weapon.witherSkullRow_.getItemMeta().getLore())) {
/* 107 */         event.setCancelled(true);
/* 108 */         if (p.getFoodLevel() < 5) {
/* 109 */           Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o你暂时无法使用它!", false);
/*     */         } else {
/* 111 */           p.setFoodLevel(p.getFoodLevel() - 5);
/* 112 */           Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o凋零!", false);
/* 113 */           p.launchProjectile(WitherSkull.class);
/*     */         } 
/*     */       } 
/* 116 */       if (event.getBow().getItemMeta().getDisplayName().equalsIgnoreCase(Weapon.LightBow_.getItemMeta().getDisplayName()) && event.getBow().getItemMeta().getLore().containsAll(Weapon.LightBow_.getItemMeta().getLore())) {
/* 117 */         event.setCancelled(true);
/* 118 */         if (p.getFoodLevel() < 10) {
/* 119 */           Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o你暂时无法使用它!", false);
/*     */         } else {
/* 121 */           p.setFoodLevel(p.getFoodLevel() - 10);
/* 122 */           for (int i = 0; i < 10; i++) {
/* 123 */             Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o神罚!", false);
/* 124 */             p.getWorld().strikeLightning(p.getTargetBlock(null, 200).getLocation());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\bump测试\【魔法】Bump_v1.0.jar!\bxx2004\bump\event\WeaponEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */