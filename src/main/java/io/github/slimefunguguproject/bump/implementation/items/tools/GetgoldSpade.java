package io.github.slimefunguguproject.bump.implementation.items.tools;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.implementation.BumpItems;
import io.github.slimefunguguproject.bump.implementation.setup.BumpItemGroups;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;

import net.guizhanss.guizhanlib.utils.RandomUtil;

/**
 * A {@link GetgoldSpade getting-gold shovel} will give player old coin
 * with chance if player is breaking sand.
 *
 * @author ybw0014
 */
public class GetgoldSpade extends SimpleSlimefunItem<ToolUseHandler> {

    public GetgoldSpade() {
        super(BumpItemGroups.TOOL, BumpItems.GETGOLD_SPADE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
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

            if (!RandomUtil.testChance(1, 4)) {
                return;
            }

            drops.clear();
            drops.add(BumpItems.OLD_COIN);

            Bump.getLocalization().sendActionbarMessage(e.getPlayer(), "tool.getgold_spade");
        };
    }
}
