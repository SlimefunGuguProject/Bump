package io.github.slimefunguguproject.bump.utils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;

import lombok.experimental.UtilityClass;

import net.guizhanss.guizhanlib.utils.ChatUtil;

/**
 * Utility methods for appraise
 *
 * @author ybw0014
 * @author haiman233
 */
@UtilityClass
public final class AppraiseUtils {
    /**
     * Check if the {@link ItemStack} is marked as appraisable,
     * which means it can be used in appraisal instrument
     *
     * @param itemStack if the {@link ItemStack} to be checked
     * @return if the {@link ItemStack} is marked as appraisable
     */
    public static boolean isAppraisable(@Nonnull ItemStack itemStack) {
        if (Utils.validateItem(itemStack)) {
            return PersistentDataAPI.getByte(itemStack.getItemMeta(), Keys.APPRAISABLE) == 1;
        } else {
            return false;
        }
    }

    /**
     * Set the {@link ItemStack} as appraisable in appraisal instrument
     *
     * @param itemStack the {@link ItemStack} to be set
     */
    public static void setAppraisable(@Nonnull ItemStack itemStack) {
        if (!Utils.validateItem(itemStack)) {
            return;
        }

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
     * @return if the {@link ItemStack} is appraised
     */
    public static boolean isAppraised(@Nonnull ItemStack itemStack) {
        if (Utils.validateItem(itemStack)) {
            return PersistentDataAPI.hasByte(itemStack.getItemMeta(), Keys.APPRAISE_LEVEL);
        } else {
            return false;
        }
    }

    @Nonnull
    public static AttributeModifier.Operation getOperation(@Nonnull Attribute attribute) {
        Preconditions.checkArgument(attribute != null, "Attribute should not be null");

        return switch (attribute) {
            case GENERIC_MOVEMENT_SPEED, HORSE_JUMP_STRENGTH -> AttributeModifier.Operation.ADD_SCALAR;
            default -> AttributeModifier.Operation.ADD_NUMBER;
        };
    }
}
