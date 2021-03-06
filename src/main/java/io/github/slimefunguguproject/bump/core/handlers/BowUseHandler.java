package io.github.slimefunguguproject.bump.core.handlers;

import java.util.Optional;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.implementation.listeners.BowShootListener;
import io.github.thebusybiscuit.slimefun4.api.exceptions.IncompatibleItemHandlerException;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;

/**
 * This {@link ItemHandler} is triggered when a player right click with a bow.
 *
 * @author ybw0014
 * @see BowShootListener
 */
public interface BowUseHandler extends ItemHandler {

    void onUse(EntityShootBowEvent e, Player p, ItemStack bow);

    @Nonnull
    @Override
    default Optional<IncompatibleItemHandlerException> validate(SlimefunItem item) {
        if (item.getItem().getType() != Material.BOW) {
            return Optional.of(new IncompatibleItemHandlerException("Only bows can have a BowUseHandler.", item, this));
        }

        return Optional.empty();
    }

    @Nonnull
    @Override
    default Class<? extends ItemHandler> getIdentifier() {
        return BowUseHandler.class;
    }
}
