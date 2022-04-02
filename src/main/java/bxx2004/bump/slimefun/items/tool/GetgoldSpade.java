package bxx2004.bump.slimefun.items.tool;

import bxx2004.bump.Bump;
import bxx2004.bump.slimefun.BumpItemGroups;
import bxx2004.bump.slimefun.BumpItems;
import bxx2004.bump.util.Utils;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class GetgoldSpade extends SimpleSlimefunItem<ToolUseHandler> {

    public GetgoldSpade() {
        super(BumpItemGroups.TOOL, BumpItems.GETGOLD_SPADE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
            null, SlimefunItems.GOLD_24K, null,
            null, new ItemStack(Material.STICK), null,
            null, new ItemStack(Material.STICK), null
        });
    }

    @Nonnull
    @Override
    public ToolUseHandler getItemHandler() {
        return (e, tool, fortune, drops) -> {
            if (e.getBlock().getType() != Material.SAND) {
                return;
            }

            if (!Utils.testChance(1, 4)) {
                return;
            }

            drops.clear();
            drops.add(BumpItems.OLD_COIN);

            Bump.getLocalization().sendActionbarMessage(e.getPlayer(), "tool.getgold_spade");
        };
    }
}