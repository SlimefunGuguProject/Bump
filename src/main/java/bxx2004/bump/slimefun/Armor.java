package bxx2004.bump.slimefun;


import bxx2004.bump.Bump;
import bxx2004.bump.util.SfItemStackCreate;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;


public class Armor {
    public static SlimefunItemStack randomHelmet_ = (new SfItemStackCreate("RANDOM_HELMET", Material.DIAMOND_HELMET, "&6随机头盔", new String[] {"", "&b&k|&b- &7&o我的运气怎么样...", "&b&k|&b- &e&o鉴定仪 &7&o随机给予此一个属性...", "§b§k|§b- §7§o护甲尚未鉴定..."})).get();

    public ItemGroup Armor;

    public Armor(SlimefunAddon plugin) {
        this.Armor = new ItemGroup(new NamespacedKey(Bump.getPlugin(Bump.class), "Armor"), new CustomItemStack(Material.DIAMOND_HELMET, "&bBump-护甲", "", "&b&k|&b- 点击打开 >", "", "&7护甲,护甲!", "他们可能含有未知的力量..."));
        this.Armor.register(plugin);
        SlimefunItem randomHelmet = new SlimefunItem(this.Armor, randomHelmet_, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {Stuff.oldCoin_, Stuff.oldCoin_, Stuff.oldCoin_, Stuff.make_, Stuff.oldCoin_, Stuff.oldCoin_, Stuff.oldCoin_, Stuff.oldCoin_});
    }
}

