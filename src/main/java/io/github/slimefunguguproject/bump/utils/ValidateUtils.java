package io.github.slimefunguguproject.bump.utils;

import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

import org.bukkit.Material;
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
     * @return if the {@link ItemStack} is valid.
     */
    public static boolean noAirItem(@Nullable ItemStack itemStack) {
        try {
            Preconditions.checkArgument(itemStack != null, "ItemStack cannot be null");
            Preconditions.checkArgument(itemStack.getType() != Material.AIR, "ItemStack cannot be empty");
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * Validate that the specified array is not {@code null},
     * and does not contain null elements.
     *
     * @param array The array to be validated
     */
    public static void noNullElements(@Nullable Object[] array) {
        Preconditions.checkArgument(array != null, "Array cannot be null");

        for (Object obj : array) {
            Preconditions.checkArgument(obj != null, "Array cannot contain null elements.");
        }
    }

    /**
     * Validate that the specified {@link Collection} is not {@code null},
     * and does not contain null elements.
     *
     * @param collection The {@link Collection} to be validated
     */
    public static void noNullElements(Collection<?> collection) {
        Preconditions.checkArgument(collection != null, "Collection cannot be null");

        Iterator<?> it = collection.iterator();

        do {
            if (!it.hasNext()) {
                return;
            }
        } while(it.next() != null);

        throw new IllegalArgumentException("Collection cannot contain null elements");
    }
}
