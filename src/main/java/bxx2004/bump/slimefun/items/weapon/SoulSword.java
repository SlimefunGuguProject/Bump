package bxx2004.bump.slimefun.items.weapon;

import bxx2004.bump.Bump;
import bxx2004.bump.slimefun.BumpItemGroups;
import bxx2004.bump.slimefun.BumpItems;
import bxx2004.bump.util.Utils;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class SoulSword extends SimpleSlimefunItem<ItemUseHandler> {

    public SoulSword() {
        super(BumpItemGroups.WEAPON, BumpItems.SOUL_SWORD, RecipeType.ARMOR_FORGE, new ItemStack[] {
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
                    Utils.changeFoodLevel(p, (int) (foodLevel - (maxHealth - health)));
                    p.setHealth(maxHealth);
                    Bump.getLocalization().sendActionbarMessage(p, "weapon.soul_sword.converted-part");
                } else {
                    // Food level can be all converted to health
                    Utils.changeFoodLevel(p, 0);
                    p.setHealth(health + foodLevel);
                    Bump.getLocalization().sendActionbarMessage(p, "weapon.soul_sword.converted-all");
                }
            } else {
                Bump.getLocalization().sendActionbarMessage(p, "weapon.low-food-level");
            }
        };
    }
}
