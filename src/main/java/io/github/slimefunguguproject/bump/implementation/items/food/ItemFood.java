package io.github.slimefunguguproject.bump.implementation.items.food;

import java.util.Locale;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.implementation.setup.BumpItemGroups;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;

import net.guizhanss.guizhanlib.common.Cooldown;
import net.guizhanss.guizhanlib.common.Scheduler;

/**
 * A {@link ItemFood} is a {@link SlimefunItem} that is based on a non-food item.
 * Player can get effects after consuming.
 *
 * @author ybw0014
 */
public abstract class ItemFood extends UnplaceableBlock {

    private final Cooldown<UUID> cooldown = new Cooldown<>();

    protected ItemFood(SlimefunItemStack itemStack, RecipeType recipeType, ItemStack[] recipe) {
        super(BumpItemGroups.FOOD, itemStack, recipeType, recipe);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            Player p = e.getPlayer();

            if (cooldown.has(p.getUniqueId())) {
                Bump.getLocalization().sendActionbarMessage(p, "food.cooldown");
                return;
            }

            String messageKey = getId().toLowerCase(Locale.ROOT);
            Bump.getLocalization().sendActionbarMessage(p, "food." + messageKey);

            if (p.getGameMode() != GameMode.CREATIVE) {
                ItemUtils.consumeItem(e.getItem(), false);
            }

            new BukkitRunnable() {
                int count = 7;

                @Override
                public void run() {
                    if (count > 0) {
                        p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 1, 1);
                        count--;
                    } else {
                        cancel();
                    }
                }
            }.runTaskTimer(Bump.getInstance(), 1L, 4L);

            applyFoodEffects(p);
            cooldown.set(p.getUniqueId(), 2000L);
        };
    }

    public abstract void applyFoodEffects(@Nonnull Player p);
}
