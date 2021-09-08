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


public class Food
{
public static SlimefunItemStack xueBi_ = (new SfItemStackCreate("XUEBI", Material.POTION, "&e雪碧", new String[] { "", "&b&k|&b- 很甜很甜, 还冒着气泡!", "" },  "MENDING-3" )).get();



 
public ItemGroup food = new ItemGroup(new NamespacedKey(Bump.getPlugin(Bump.class), "Food"), new CustomItemStack(Material.BREAD, "&bBump-食物", "", "&b&k|&b- 点击打开 >", "", "&7高级的食物往往...!", "Rua Rua Rua..." ));

  
  public static SlimefunItemStack keLe_ = (new SfItemStackCreate("KELE", Material.POTION, "&e可乐",  new String[]{ "&b&k|&b- 冰力十足,喝了真精神,还会飞呢!" })).get();
  public static SlimefunItemStack fangBianMian_ = (new SfItemStackCreate("FANGBIANMIAN", Material.STRING, "&e康师傅方便面", new String[] { "", "&b&k|&b- 这个味,对辣!", "" })).get(); public static SlimefunItemStack laTiao_; static {
  laTiao_ = (new SfItemStackCreate("LATIAO", Material.ROTTEN_FLESH, "&e辣条", new String[] { "", "&b&k|&b- 爽,吃了之后还不忘舔舔辣油...", "" })).get();
  kouXiangTang_ = (new SfItemStackCreate("KOUXIANGTANG", Material.SUGAR, "&e口香糖", new String[] { "", "&b&k|&b- 很粘稠的...", "" })).get();
   }
   public static SlimefunItemStack kouXiangTang_;
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


