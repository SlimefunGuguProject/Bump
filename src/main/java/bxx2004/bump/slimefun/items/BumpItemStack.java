package bxx2004.bump.slimefun.items;

import bxx2004.bump.Bump;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Material;

public class BumpItemStack extends SlimefunItemStack {
    public BumpItemStack(String id, Material material) {
        super(
            id,
            material,
            Bump.getLocalization().getItemName(id),
            Bump.getLocalization().getItemLore(id)
        );
    }
}
