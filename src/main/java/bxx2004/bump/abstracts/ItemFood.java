package bxx2004.bump.abstracts;

import bxx2004.bump.Bump;
import bxx2004.bump.slimefun.BumpItemGroups;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.Locale;

/**
 * A {@link ItemFood} is a {@link SlimefunItem} that is based on a non-food item.
 * Player can get effects after consuming.
 *
 * @author ybw0014
 */
public abstract class ItemFood extends SimpleSlimefunItem<ItemUseHandler> {

    protected ItemFood(SlimefunItemStack itemStack, RecipeType recipeType, ItemStack[] recipe) {
        super(BumpItemGroups.FOOD, itemStack, recipeType, recipe);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Player p = e.getPlayer();

            String messageKey = this.getId().toLowerCase(Locale.ROOT);
            Bump.getLocalization().sendActionbarMessage(p, "food." + messageKey);

            if (p.getGameMode() != GameMode.CREATIVE) {
                ItemUtils.consumeItem(e.getItem(), false);
            }

            p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 1, 1);
            this.applyFoodEffects(p);
        };
    }

    public abstract void applyFoodEffects(Player p);
}
