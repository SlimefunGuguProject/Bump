/*    */ package bxx2004.bump;
/*    */ 
/*    */ import bxx2004.bump.event.FoodEvent;
/*    */ import bxx2004.bump.event.MachineEvent;
/*    */ import bxx2004.bump.event.ToolsEvent;
/*    */ import bxx2004.bump.event.WeaponEvent;
/*    */ import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Bump
/*    */   extends JavaPlugin
/*    */   implements SlimefunAddon
/*    */ {
/*    */   public void onEnable() {
/* 22 */     ChatColor.translateAlternateColorCodes('&', "§");
/* 23 */     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§6§l  _____");
/* 24 */     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§6§l /\\   _`\\             - Slimefun");
/* 25 */     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§6§l \\ \\ \\L\\ \\  __  __    ___ ___   _____   ");
/* 26 */     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§6§l  \\ \\  _ <'/\\ \\/\\ \\ /' __` __`\\/\\ '__`\\ ");
/* 27 */     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§6§l   \\ \\ \\L\\ \\ \\ \\_\\ \\/\\ \\/\\ \\/\\ \\ \\ \\L\\ \\");
/* 28 */     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§6§l    \\ \\____/\\ \\____/\\ \\_\\ \\_\\ \\_\\ \\ ,__/");
/* 29 */     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§6§l     \\/___/  \\/___/  \\/_/\\/_/\\/_/\\ \\ \\/ ");
/* 30 */     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§6§l                                  \\ \\_\\ ");
/* 31 */     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§6§l                                   \\/_/ ");
/* 32 */     Bukkit.getConsoleSender().sendMessage("§b§l[Bump] ->§a§l                   - Powered By bxx2004 | QQ Group: 1153619913");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 39 */     Bukkit.getPluginManager().registerEvents(new WeaponEvent(), this);
/* 40 */     Bukkit.getPluginManager().registerEvents(new MachineEvent(), this);
/* 41 */     Bukkit.getPluginManager().registerEvents(new ToolsEvent(), this);
/* 42 */     Bukkit.getPluginManager().registerEvents(new FoodEvent(), this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {}
/*    */   
/*    */   public JavaPlugin getJavaPlugin() {
/* 49 */     return getJavaPlugin();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getBugTrackerURL() {
/* 54 */     return "bxx2004";
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\bump测试\【魔法】Bump_v1.0.jar!\bxx2004\bump\Bump.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */