package bxx2004.bump.event;

import bxx2004.bump.Bump;
import bxx2004.bump.slimefun.Weapon;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import org.bukkit.Particle;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class WeaponEvent
    implements Listener {
    @EventHandler
    public void onRight(PlayerInteractEvent event) {
        try {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                final Player p = event.getPlayer();
                if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Weapon.soulSword_.getItemMeta().getDisplayName()) && event.getItem().getItemMeta().getLore().containsAll(Weapon.soulSword_.getItemMeta().getLore())) {
                    int health = (int) p.getHealth();
                    int healths = (int) p.getHealthScale();
                    int foodLevel = p.getFoodLevel();
                    if (health == healths || foodLevel == 0) {
                        Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o你现在无法发动该技能!", false);
                    } else if (foodLevel < 2) {
                        Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o你的饱食度不足以转换生命值!", false);
                    } else if (foodLevel > 2 && healths - health <= foodLevel) {
                        Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o已将你的饱食度部分转换为生命值!", false);
                        p.setFoodLevel(foodLevel - healths - health);
                        p.setHealth((health + healths - health));
                    } else if (healths - health > foodLevel) {
                        Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o已将你的饱食度全部转换生命值!", false);
                        p.setHealth((health + foodLevel));
                        p.setFoodLevel(0);
                    }
                }
                if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Weapon.devilSword_.getItemMeta().getDisplayName()) && event.getItem().getItemMeta().getLore().containsAll(Weapon.devilSword_.getItemMeta().getLore()))
                    if (p.getFoodLevel() < 5) {
                        Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o你现在无法发动该技能!", false);
                    } else {
                        p.setFoodLevel(p.getFoodLevel() - 5);
                        Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o斩魔, 一刀致命!", false);
                        for (int i = 0; i < 20; i++) {
                            p.launchProjectile(SmallFireball.class);
                            Particle particle = Particle.ENCHANTMENT_TABLE;
                            p.spawnParticle(particle, p.getLocation(), 1);
                        }
                    }
                if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Weapon.skySword_.getItemMeta().getDisplayName()) && event.getItem().getItemMeta().getLore().containsAll(Weapon.skySword_.getItemMeta().getLore()))
                    if (p.getFoodLevel() < 5) {
                        Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o你现在无法发动该技能!", false);
                    } else {
                        p.setFoodLevel(p.getFoodLevel() - 5);
                        Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o破天, 一飞冲天!", false);
                        Vector direction = p.getLocation().toVector();
                        p.setVelocity(p.getVelocity().add(direction.multiply(0.5D)));
                        for (int i = 0; i < 20; i++) {
                            Particle particle = Particle.EXPLOSION_HUGE;
                            p.spawnParticle(particle, p.getLocation(), 1);
                        }
                    }
                if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Weapon.skydevilSword_.getItemMeta().getDisplayName()) && event.getItem().getItemMeta().getLore().containsAll(Weapon.skydevilSword_.getItemMeta().getLore()))
                    if (p.getFoodLevel() < 5) {
                        Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o你现在无法发动该技能!", false);
                    } else {
                        p.setFoodLevel(p.getFoodLevel() - 5);
                        Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o破天斩魔,天降正义!", false);
                        p.setGlowing(true);
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 3));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 3));
                        (new BukkitRunnable() {
                            int i = 0;

                            public void run() {
                                if (this.i < 3) {
                                    p.launchProjectile(DragonFireball.class);
                                    this.i++;
                                } else {
                                    cancel();
                                    this.i = 0;
                                    p.setGlowing(false);
                                    Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o强化时间已经结束!", false);
                                    p.removePotionEffect(PotionEffectType.SPEED);
                                }
                            }
                        }).runTaskTimer(Bump.getPlugin(Bump.class), 0L, 100L);
                    }
            }
        } catch (Exception exception) {
        }
    }

    @EventHandler
    public void onRightBow(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if (event.getBow().getItemMeta().getDisplayName().equalsIgnoreCase(Weapon.witherSkullRow_.getItemMeta().getDisplayName()) && event.getBow().getItemMeta().getLore().containsAll(Weapon.witherSkullRow_.getItemMeta().getLore())) {
                event.setCancelled(true);
                if (p.getFoodLevel() < 5) {
                    Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o你暂时无法使用它!", false);
                } else {
                    p.setFoodLevel(p.getFoodLevel() - 5);
                    Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o凋零!", false);
                    p.launchProjectile(WitherSkull.class);
                }
            }
            if (event.getBow().getItemMeta().getDisplayName().equalsIgnoreCase(Weapon.LightBow_.getItemMeta().getDisplayName()) && event.getBow().getItemMeta().getLore().containsAll(Weapon.LightBow_.getItemMeta().getLore())) {
                event.setCancelled(true);
                if (p.getFoodLevel() < 10) {
                    Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o你暂时无法使用它!", false);
                } else {
                    p.setFoodLevel(p.getFoodLevel() - 10);
                    for (int i = 0; i < 10; i++) {
                        Slimefun.getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§o神罚!", false);
                        p.getWorld().strikeLightning(p.getTargetBlock(null, 200).getLocation());
                    }
                }
            }
        }
    }
}
