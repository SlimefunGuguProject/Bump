package bxx2004.bump.util;


import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;


public class SfItemStackCreate {
    private SlimefunItemStack itemStack;

    public SlimefunItemStack get() {
        return this.itemStack;
    }

    public SfItemStackCreate(String id, Material material, String displayName, String[] lore, String... enchants) {
        SlimefunItemStack customItem = new SlimefunItemStack(id, material, displayName, lore);
        ItemMeta meta = customItem.getItemMeta();
        if (enchants != null) {
            for (int i = 0; i < enchants.length; i++) {
                String ench = enchants[i];
                String[] strs = ench.split("[-]");
                meta.addEnchant(Enchantment.getByName(ench.substring(0, ench.indexOf("-"))), Integer.parseInt(strs[1]), true);
            }
            customItem.setItemMeta(meta);
        }
        this.itemStack = customItem;
    }
}
