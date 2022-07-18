package io.github.slimefunguguproject.bump.implementation.items.weapons;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.implementation.BumpItems;
import io.github.slimefunguguproject.bump.implementation.setup.BumpItemGroups;
import io.github.slimefunguguproject.bump.utils.FoodLevelUtils;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;

/**
 * {@link SoulSword Soul sword} will convert hunger to health.
 *
 * @author ybw0014
 */
public class SoulSword extends SimpleSlimefunItem<ItemUseHandler> {

    public SoulSword() {
        super(BumpItemGroups.WEAPON, BumpItems.SOUL_SWORD, RecipeType.ARMOR_FORGE, new ItemStack[]{
            null, null, null,
            BumpItems.SOUL_PAPER, new ItemStack(Material.DIAMOND_SWORD), BumpItems.SOUL_PAPER,
            null, null, null
        });
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Player p = e.getPlayer();
            double health = p.getHealth();
            double maxHealth = p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
            int foodLevel = p.getFoodLevel();

            if (maxHealth <= health) {
                Bump.getLocalization().sendActionbarMessage(p, "weapon.unavailable");
                return;
            }

            if (foodLevel >= 2) {
                if (maxHealth - health <= foodLevel) {
                    // Food level can be partially converted to full health
                    FoodLevelUtils.set(p, (int) (foodLevel - (maxHealth - health)));
                    p.setHealth(maxHealth);
                    Bump.getLocalization().sendActionbarMessage(p, "weapon.soul_sword.converted-part");
                } else {
                    // Food level can be all converted to health
                    FoodLevelUtils.set(p, 0);
                    p.setHealth(health + foodLevel);
                    Bump.getLocalization().sendActionbarMessage(p, "weapon.soul_sword.converted-all");
                }

                p.playSound(p.getLocation(), Sound.AMBIENT_CAVE, 1.0F, 1.0F);
            } else {
                Bump.getLocalization().sendActionbarMessage(p, "weapon.low-food-level");
            }
        };
    }
}
