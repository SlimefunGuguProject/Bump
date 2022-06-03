package org.slimefunguguproject.bump.utils;

import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import net.guizhanss.guizhanlib.utils.ChatUtil;
import org.apache.commons.lang.Validate;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slimefunguguproject.bump.implementation.Bump;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods for appraise
 *
 * @author ybw0014
 * @author haiman233
 */
public final class AppraiseUtils {
    private AppraiseUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Check if the {@link ItemStack} can be used in appraisal instrument
     *
     * @param itemStack if the {@link ItemStack} to be checked
     *
     * @return if the {@link ItemStack} can be used in appraisal instrument
     */
    public static boolean isAppraisable(@Nonnull ItemStack itemStack) {
        Validate.notNull(itemStack, "itemStack should not be null");
        Validate.notNull(itemStack.getItemMeta(), "itemMeta should not be null");

        return PersistentDataAPI.getByte(itemStack.getItemMeta(), Keys.APPRAISABLE) == 1;
    }

    /**
     * Set the {@link ItemStack} as appraisable in appraisal instrument
     *
     * @param itemStack the {@link ItemStack} to be set
     */
    public static void setAppraisable(@Nonnull ItemStack itemStack) {
        Validate.notNull(itemStack, "itemStack should not be null");
        Validate.notNull(itemStack.getItemMeta(), "itemMeta should not be null");

        ItemMeta im = itemStack.getItemMeta();

        // set lore
        List<String> lore;
        if (im.hasLore()) {
            lore = im.getLore();
        } else {
            lore = new ArrayList<>();
        }
        lore.add("");
        lore.add(ChatUtil.color(Bump.getLocalization().getString("lores.not-appraised")));
        im.setLore(lore);

        // set pdc
        PersistentDataAPI.setByte(im, Keys.APPRAISABLE, (byte) 1);

        itemStack.setItemMeta(im);
    }

    /**
     * Check if the {@link ItemStack} is appraised
     *
     * @param itemStack the {@link ItemStack} to be checked
     *
     * @return if the {@link ItemStack} is appraised
     */
    public static boolean isAppraised(@Nonnull ItemStack itemStack) {
        Validate.notNull(itemStack, "itemStack should not be null");
        Validate.notNull(itemStack.getItemMeta(), "itemMeta should not be null");

        return PersistentDataAPI.hasByte(itemStack.getItemMeta(), Keys.APPRAISE_LEVEL);
    }

    @Nonnull
    public static AttributeModifier.Operation getOperation(@Nonnull Attribute attribute) {
        Validate.notNull(attribute, "Attribute should not be null");

        switch (attribute) {
            case GENERIC_MOVEMENT_SPEED:
                return AttributeModifier.Operation.ADD_SCALAR;
            default:
                return AttributeModifier.Operation.ADD_NUMBER;
        }
    }
}
