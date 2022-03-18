package bxx2004.bump.sf;



import bxx2004.bump.Bump;
import bxx2004.bump.util.Register;
import bxx2004.bump.util.SfItemStackCreate;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;



import org.bukkit.plugin.Plugin;


public class Food {
    public static SlimefunItemStack xueBi_ = new SfItemStackCreate("XUEBI", Material.POTION, "&eSprite", new String[]{"", "&b&k|&b- Very sweet and sweet, still bubbling!", ""}, "MENDING-3");
    public ItemGroup food = new ItemGroup(new NamespacedKey(plugin, "Food"), new CustomItemStack(Material.BREAD, "&bBump-Food", "", "&b&k|&b- Click to open >", "", "&7High-class food often...!", "Rua Rua Rua..."));
    public static SlimefunItemStack keLe_ = new SfItemStackCreate("KELE", Material.POTION, "&eCola", new String[]{"&b&k|&b- The ice is full of strength, and if you drink it, you can fly!"});
    public static SlimefunItemStack fangBianMian_ = new SfItemStackCreate("FANGBIANMIAN", Material.STRING, "&eMaster Kong Instant Noodles", new String[]{"", "&b&k|&b- This taste is spicy!", ""});
    public static SlimefunItemStack laTiao_;

    static {
        laTiao_ = new SfItemStackCreate("LATIAO", Material.ROTTEN_FLESH, "&eSpicy Strips", new String[]{"", "&b&k|&b- It's cool, I still don't forget to lick the spicy oil after eating...", ""});
        kouXiangTang_ = new SfItemStackCreate("KOUXIANGTANG", Material.SUGAR, "&eChewing Gum", new String[]{"", "&b&k|&b- Very sticky...", ""});
    }

    public static SlimefunItemStack kouXiangTang_;

    public Food() {
    public Food(SlimefunAddon plugin) {
     this.food.register(plugin);
     ItemStack[] itemStackArray = new ItemStack[9];
     itemStackArray[0] = SlimefunItems.MAGIC_SUGAR;
     SlimefunItem kouXiangTang = new SlimefunItem(this.food, kouXiangTang_, RecipeType.COMPRESSOR, itemStackArray);
     ItemStack[] itemStackArray2 = new ItemStack[9];
     itemStackArray2[0] = SlimefunItems.WHEAT_FLOUR;
     SlimefunItem laTiao = new SlimefunItem(this.food, laTiao_, RecipeType.COMPRESSOR, itemStackArray2);
     SlimefunItem fangBianMian = new SlimefunItem(this.food, fangBianMian_, RecipeType.MAGIC_WORKBENCH, new ItemStack[] { new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.WATER_BUCKET), Stuff.ksf_, Stuff.ksf_, Stuff.ksf_, SlimefunItems.WHEAT_FLOUR, SlimefunItems.WHEAT_FLOUR, SlimefunItems.WHEAT_FLOUR });
     SlimefunItem xueBi = new SlimefunItem(this.food, xueBi_, RecipeType.MAGIC_WORKBENCH, new ItemStack[] { Stuff.waterSugar_, new ItemStack(Material.WATER_BUCKET), Stuff.waterSugar_, new ItemStack(Material.WATER_BUCKET), Stuff.waterSugar_, new ItemStack(Material.WATER_BUCKET), Stuff.waterSugar_, new ItemStack(Material.WATER_BUCKET), Stuff.waterSugar_ });
     SlimefunItem keLe = new SlimefunItem(this.food, keLe_, RecipeType.MAGIC_WORKBENCH, new ItemStack[] { Stuff.waterSugar_, new ItemStack(Material.WATER_BUCKET), Stuff.waterSugar_, new ItemStack(Material.WATER_BUCKET), SlimefunItems.MAGIC_SUGAR, new ItemStack(Material.WATER_BUCKET), Stuff.waterSugar_, new ItemStack(Material.WATER_BUCKET), Stuff.waterSugar_ });
     new Register( xueBi, keLe, kouXiangTang, fangBianMian, laTiao );
   }
 }


