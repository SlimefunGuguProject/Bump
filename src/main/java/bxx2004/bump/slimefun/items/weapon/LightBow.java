package bxx2004.bump.slimefun.items.weapon;

import bxx2004.bump.Bump;
import bxx2004.bump.abstracts.BumpBow;
import bxx2004.bump.handlers.BowUseHandler;
import bxx2004.bump.slimefun.BumpItems;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class LightBow extends BumpBow {

    public LightBow() {
        super(10, BumpItems.LIGHT_BOW, RecipeType.ARMOR_FORGE, new ItemStack[] {
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
            if (costHunger(p)) {
                damageItem(p, item);

                Bump.getLocalization().sendActionbarMessage(p, "weapon.light_bow");

                for (int i = 0; i < 10; i++) {
                    p.getWorld().strikeLightning(p.getTargetBlock(null, 200).getLocation());
                }

            } else {
                Bump.getLocalization().sendActionbarMessage(p, "weapon.low-food-level");
            }
        };
    }
}
