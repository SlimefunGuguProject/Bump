package bxx2004.bump.handlers;

import io.github.thebusybiscuit.slimefun4.api.exceptions.IncompatibleItemHandlerException;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public interface BowUseHandler extends ItemHandler {

    void onShoot(EntityShootBowEvent e, Player p, ItemStack item);

    @Override
    default Optional<IncompatibleItemHandlerException> validate(SlimefunItem item) {
        if (item.getItem().getType() != Material.BOW) {
            return Optional.of(new IncompatibleItemHandlerException("Only bows can have a BowShootHandler.", item, this));
        }

        return Optional.empty();
    }

    @Override
    default Class<? extends ItemHandler> getIdentifier() {
        return BowUseHandler.class;
    }
}
