package io.github.slimefunguguproject.bump.utils;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

import org.bukkit.inventory.ItemStack;

import lombok.experimental.UtilityClass;

/**
 * This utility class holds validation methods.
 *
 * @author ybw0014
 */
@UtilityClass
public final class ValidateUtils {
    /**
     * Validate the given {@link ItemStack} is not {@code null} or an air item.
     *
     * @param itemStack The {@link ItemStack} to be validated.
     *
     * @return if the {@link ItemStack} is valid.
     */
    public static boolean noAirItem(@Nullable ItemStack itemStack) {
        try {
            Preconditions.checkArgument(itemStack != null, "ItemStack cannot be null");
            Preconditions.checkArgument(!itemStack.getType().isAir(), "ItemStack cannot be empty");
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
