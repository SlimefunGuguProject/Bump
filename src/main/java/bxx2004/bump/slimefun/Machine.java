package bxx2004.bump.slimefun;


import bxx2004.bump.Bump;
import bxx2004.bump.util.SfItemStackCreate;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;


public class Machine {
    public static SlimefunItem appraisal;
    public ItemGroup machine;

    public Machine(SlimefunAddon plugin) {
        this.machine = new ItemGroup(new NamespacedKey(Bump.getPlugin(Bump.class), "Machine"), new CustomItemStack(Material.ANVIL, "&bBump-机器", "", "&b&k|&b- 点击打开 >", "", "&7我们还可以继续工作!", "兹拉,兹拉,滋滋滋..."));
        this.machine.register(plugin);
        appraisal = new SlimefunItem(this.machine, (new SfItemStackCreate("APPRAISAL", Material.BELL, "&B鉴定仪", new String[] {"", "&c&k|&7- 哦,你这是个残次品...", ""})).get(), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {SlimefunItems.BATTERY, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.BATTERY, Stuff.mechaGeat_, Stuff.CPU_, Stuff.mechaGeat_, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.COOLING_UNIT, SlimefunItems.ADVANCED_CIRCUIT_BOARD});
    }
}


