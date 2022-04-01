package bxx2004.bump.slimefun.items.weapon;

import bxx2004.bump.Bump;
import bxx2004.bump.slimefun.BumpItemGroups;
import bxx2004.bump.slimefun.BumpItems;
import bxx2004.bump.util.Utils;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import net.guizhanss.guizhanlib.common.Scheduler;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;

public class SkyDevilSword extends SimpleSlimefunItem<ItemUseHandler> {

    public SkyDevilSword() {
        super(BumpItemGroups.WEAPON, BumpItems.SKY_DEVIL_SWORD, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
            null, null, null,
            BumpItems.SKY_SWORD, BumpItems.UPDATE_POWER, BumpItems.DEVIL_SWORD,
            null, null, null
        });
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Player p = e.getPlayer();

            if (p.getFoodLevel() >= 5) {
                Utils.changeFoodLevel(p, p.getFoodLevel() - 5);

                Bump.getLocalization().sendActionbarMessage(p, "weapon.sky_devil_sword.activate");

                p.setGlowing(true);
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 3));
                p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 3));

                Scheduler.repeatAsync(100, new BukkitRunnable() {
                    int i = 0;

                    @Override
                    public void run() {
                        if (this.i < 3) {
                            p.launchProjectile(DragonFireball.class);
                            this.i++;
                        } else {
                            cancel();
                            this.i = 0;
                            p.setGlowing(false);
                            Bump.getLocalization().sendActionbarMessage(p, "weapon.sky_devil_sword.end");
                            p.removePotionEffect(PotionEffectType.SPEED);
                        }
                    }
                });
            } else {
                // Too low food level
                Bump.getLocalization().sendActionbarMessage(p, "weapon.low-food-level");
            }
        };
    }
}
