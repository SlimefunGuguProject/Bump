package io.github.slimefunguguproject.bump.implementation.items.weapons;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.core.handlers.BowUseHandler;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.implementation.BumpItems;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

/**
 * {@link LightBow God's Punishment Bow} will strike lightning at player's pointed block.
 *
 * @author ybw0014
 */
public class LightBow extends BumpBow {

    public LightBow() {
        super(10, BumpItems.LIGHT_BOW, RecipeType.ARMOR_FORGE, new ItemStack[]{
            SlimefunItems.LIGHTNING_RUNE, SlimefunItems.STAFF_STORM, SlimefunItems.LIGHTNING_RUNE,
            SlimefunItems.POWER_CRYSTAL, SlimefunItems.STAFF_STORM, SlimefunItems.LIGHTNING_RUNE,
            SlimefunItems.STAFF_STORM
        });
    }

    @Nonnull
    @Override
    public BowUseHandler getItemHandler() {
        return (e, p, item) -> {
            e.setCancelled(true);

            Block target = p.getTargetBlock(null, 200);
            if (target.getType() == Material.AIR) {
                return;
            }

            Location targetLocation = target.getLocation();

            if (costHunger(p)) {
                damageItem(p, item);

                Bump.getLocalization().sendActionbarMessage(p, "weapon.light_bow");

                for (int i = 0; i < 10; i++) {
                    p.getWorld().strikeLightning(targetLocation);
                }
            } else {
                Bump.getLocalization().sendActionbarMessage(p, "weapon.low-food-level");
            }
        };
    }
}
