package io.github.slimefunguguproject.bump.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.slimefunguguproject.bump.api.appraise.AppraiseType;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;

import net.guizhanss.guizhanlib.minecraft.utils.ChatUtil;

import lombok.experimental.UtilityClass;

/**
 * Utility methods for appraise.
 *
 * @author ybw0014
 * @author haiman233
 */
@SuppressWarnings("ConstantConditions")
@UtilityClass
public final class AppraiseUtils {
    /**
     * Check if the {@link ItemStack} is marked as appraisable,
     * which means it can be used in appraisal instrument.
     *
     * @param itemStack The {@link ItemStack} to be checked.
     *
     * @return Whether the {@link ItemStack} is marked as appraisable.
     */
    public static boolean isAppraisable(@Nonnull ItemStack itemStack) {
        if (ValidateUtils.noAirItem(itemStack)) {
            return PersistentDataAPI.getBoolean(itemStack.getItemMeta(), Keys.APPRAISABLE);
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
        if (!ValidateUtils.noAirItem(itemStack)) {
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
        PersistentDataAPI.setBoolean(im, Keys.APPRAISABLE, true);

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
        if (ValidateUtils.noAirItem(itemStack)) {
            return PersistentDataAPI.hasByte(itemStack.getItemMeta(), Keys.APPRAISE_LEVEL);
        } else {
            return false;
        }
    }

    @Nonnull
    public static AttributeModifier.Operation getOperation(@Nonnull Attribute attribute) {
        Preconditions.checkArgument(attribute != null, "Attribute cannot be null");

        return switch (attribute) {
            case GENERIC_MOVEMENT_SPEED, HORSE_JUMP_STRENGTH -> AttributeModifier.Operation.ADD_SCALAR;
            default -> AttributeModifier.Operation.ADD_NUMBER;
        };
    }

    @Nonnull
    public static List<String> getDescriptionLore(@Nonnull AppraiseType type) {
        Preconditions.checkArgument(type != null, "Appraise type cannot be null");

        return type.getDescription().stream()
            .map(line -> ChatUtil.color(ChatColor.GRAY + line))
            .collect(Collectors.toList());
    }
}
