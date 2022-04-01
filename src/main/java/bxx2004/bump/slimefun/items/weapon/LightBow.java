package bxx2004.bump.slimefun.items.weapon;

import bxx2004.bump.Bump;
import bxx2004.bump.slimefun.BumpItemGroups;
import bxx2004.bump.slimefun.BumpItems;
import bxx2004.bump.util.Utils;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BowShootHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class LightBow extends BumpBow {

    public LightBow() {
        super(BumpItemGroups.WEAPON, BumpItems.LIGHT_BOW, RecipeType.ARMOR_FORGE, new ItemStack[] {
            SlimefunItems.LIGHTNING_RUNE, SlimefunItems.STAFF_STORM, SlimefunItems.LIGHTNING_RUNE,
            SlimefunItems.POWER_CRYSTAL, SlimefunItems.STAFF_STORM, SlimefunItems.LIGHTNING_RUNE,
            SlimefunItems.STAFF_STORM
        });
    }

    @Nonnull
    @Override
    public BowShootHandler onShoot() {
        return (e, n) -> {
            Player p = (Player) e.getDamager();

            if (p.getFoodLevel() >= 10) {
                Utils.changeFoodLevel(p, p.getFoodLevel() - 10);

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
