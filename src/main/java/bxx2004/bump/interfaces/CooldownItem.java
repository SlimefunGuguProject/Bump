package bxx2004.bump.interfaces;

import bxx2004.bump.util.Keys;
import org.apache.commons.lang.Validate;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;

/**
 * This interface indicates that this item has cooldown time.
 *
 * @author ybw0014
 */
public interface CooldownItem {
    /**
     * This method returns the cooldown time in seconds.
     *
     * @return cooldown time in seconds.
     */
    int getCooldown();

    /**
     * This method checks if given {@link ItemStack} has cooled down.
     *
     * @param itemStack The {@link ItemStack} to be checked
     *
     * @return if the item can be used now
     */
    default boolean isCooldown(@Nonnull ItemStack itemStack) {
        Validate.notNull(itemStack, "ItemStack should not be null");
        Validate.isTrue(itemStack.hasItemMeta(), "ItemMeta should not be null");

        PersistentDataContainer pdc = itemStack.getItemMeta().getPersistentDataContainer();

        if (pdc.has(Keys.LAST_USED, PersistentDataType.LONG)) {
            long lastUsed = pdc.get(Keys.LAST_USED, PersistentDataType.LONG);

            return lastUsed + getCooldown() * 1000L <= System.currentTimeMillis();
        } else {
            return false;
        }
    }

    default void setCooldown(@Nonnull ItemStack itemStack) {
        Validate.notNull(itemStack, "ItemStack should not be null");
        Validate.isTrue(itemStack.hasItemMeta(), "ItemMeta should not be null");

        PersistentDataContainer pdc = itemStack.getItemMeta().getPersistentDataContainer();
        pdc.set(Keys.LAST_USED, PersistentDataType.LONG, System.currentTimeMillis());
    }

    default boolean checkCooldown(@Nonnull ItemStack itemStack) {
        Validate.notNull(itemStack, "ItemStack should not be null");
        Validate.isTrue(itemStack.hasItemMeta(), "ItemMeta should not be null");

        if (isCooldown(itemStack)) {
            setCooldown(itemStack);
            return true;
        } else {
            return false;
        }
    }
}
