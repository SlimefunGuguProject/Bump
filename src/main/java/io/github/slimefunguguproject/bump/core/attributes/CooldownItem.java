package io.github.slimefunguguproject.bump.core.attributes;

import javax.annotation.Nonnull;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.slimefunguguproject.bump.utils.ValidateUtils;
import io.github.slimefunguguproject.bump.utils.constant.Keys;
import io.github.thebusybiscuit.slimefun4.core.attributes.ItemAttribute;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;

/**
 * This {@link ItemAttribute} indicates that this item has cooldown time.
 *
 * @author ybw0014
 */
public interface CooldownItem extends ItemAttribute {
    /**
     * This method returns the cooldown time in seconds.
     *
     * @return cooldown time in seconds.
     */
    int getCooldown();

    default void setCooldown(@Nonnull ItemStack itemStack) {
        if (ValidateUtils.noAirItem(itemStack)) {
            ItemMeta im = itemStack.getItemMeta();
            PersistentDataAPI.setLong(im, Keys.LAST_USED, System.currentTimeMillis());
            itemStack.setItemMeta(im);
        }
    }

    /**
     * This method checks if given {@link ItemStack} has cooled down.
     *
     * @param itemStack The {@link ItemStack} to be checked
     *
     * @return if the item can be used now
     */
    default boolean isCooldown(@Nonnull ItemStack itemStack) {
        if (ValidateUtils.noAirItem(itemStack)) {
            ItemMeta im = itemStack.getItemMeta();

            if (PersistentDataAPI.hasLong(im, Keys.LAST_USED)) {
                long lastUsed = PersistentDataAPI.getLong(im, Keys.LAST_USED);

                return lastUsed + getCooldown() * 1000L < System.currentTimeMillis();
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    default boolean checkCooldown(@Nonnull ItemStack itemStack) {
        if (ValidateUtils.noAirItem(itemStack)) {
            if (isCooldown(itemStack)) {
                setCooldown(itemStack);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
