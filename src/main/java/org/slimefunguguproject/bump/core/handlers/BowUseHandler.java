package org.slimefunguguproject.bump.core.handlers;

import io.github.thebusybiscuit.slimefun4.api.exceptions.IncompatibleItemHandlerException;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.slimefunguguproject.bump.implementation.listeners.BowShootListener;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * This {@link ItemHandler} is triggered when a player right click with a bow.
 *
 * @see BowShootListener
 *
 * @author ybw0014
 */
public interface BowUseHandler extends ItemHandler {

    void onUse(EntityShootBowEvent e, Player p, ItemStack bow);

    @Override
    default @Nonnull Optional<IncompatibleItemHandlerException> validate(SlimefunItem item) {
        if (item.getItem().getType() != Material.BOW) {
            return Optional.of(new IncompatibleItemHandlerException("Only bows can have a BowUseHandler.", item, this));
        }

        return Optional.empty();
    }

    @Override
    default @Nonnull Class<? extends ItemHandler> getIdentifier() {
        return BowUseHandler.class;
    }
}
