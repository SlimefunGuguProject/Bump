package org.slimefunguguproject.bump.api.items;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.slimefunguguproject.bump.implementation.Bump;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;

import net.guizhanss.guizhanlib.utils.ChatUtil;

/**
 * This is an extended {@link SlimefunItemStack} that loads name and lore
 * from {@link org.slimefunguguproject.bump.core.services.LocalizationService}.
 *
 * @author ybw0014
 */
public class LocalizedItemStack extends SlimefunItemStack {
    @ParametersAreNonnullByDefault
    public LocalizedItemStack(String id, Material material) {
        super(
            id,
            material,
            Bump.getLocalization().getItemName(id),
            Bump.getLocalization().getItemLore(id)
        );
    }

    @ParametersAreNonnullByDefault
    public LocalizedItemStack(String id, Material material, String... appendLore) {
        super(
            id,
            material,
            Bump.getLocalization().getItemName(id),
            Bump.getLocalization().getItemLore(id)
        );

        ItemMeta im = getItemMeta();
        List<String> lore;
        if (im.hasLore()) {
            lore = im.getLore();
        } else {
            lore = new ArrayList<>();
        }
        for (String line : appendLore) {
            lore.add(ChatUtil.color(line));
        }
        im.setLore(lore);
        setItemMeta(im);
    }
}
