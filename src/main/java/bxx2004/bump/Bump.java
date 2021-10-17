 package bxx2004.bump;
 
 import bxx2004.bump.event.FoodEvent;
 import bxx2004.bump.event.MachineEvent;
 import bxx2004.bump.event.ToolsEvent;
 import bxx2004.bump.event.WeaponEvent;
 import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
 import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.plugin.java.JavaPlugin;
 
 public class Bump
   extends JavaPlugin
   implements SlimefunAddon
 {
   public void onEnable() {
     ChatColor.translateAlternateColorCodes('&', "§");
     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§6§l  _____");
     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§6§l /\\   _`\\             ");
     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§6§l \\ \\ \\L\\ \\  __  __    ___ ___   _____   ");
     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§6§l  \\ \\  _ <'/\\ \\/\\ \\ /' __` __`\\/\\ '__`\\ ");
     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§6§l   \\ \\ \\L\\ \\ \\ \\_\\ \\/\\ \\/\\ \\/\\ \\ \\ \\L\\ \\");
     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§6§l    \\ \\____/\\ \\____/\\ \\_\\ \\_\\ \\_\\ \\ ,__/");
     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§6§l     \\/___/  \\/___/  \\/_/\\/_/\\/_/\\ \\ \\/ ");
     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§6§l                                  \\ \\_\\ ");
     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§6§l                                   \\/_/ ");
     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§a§l     - Slimefun4 RC-27 - Powered By bxx2004 | Website https://github.com/haiman233/Slimefun-Bump");

     
     Bukkit.getPluginManager().registerEvents(new WeaponEvent(), this);
     Bukkit.getPluginManager().registerEvents(new MachineEvent(), this);
     Bukkit.getPluginManager().registerEvents(new ToolsEvent(), this);
     Bukkit.getPluginManager().registerEvents(new FoodEvent(), this);
   }
 
   
   public void onDisable() {}
   
   public JavaPlugin getJavaPlugin() {
     return getJavaPlugin();
   }
 
   
   public String getBugTrackerURL() {
     return "bxx2004";
   }
 }
