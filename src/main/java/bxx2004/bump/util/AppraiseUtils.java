package bxx2004.bump.util;

import bxx2004.bump.Bump;
import net.guizhanss.guizhanlib.minecraft.MinecraftTag;
import net.guizhanss.guizhanlib.utils.ChatUtil;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Utility methods for appraise
 *
 * @author ybw0014
 */
public final class AppraiseUtils {
    private AppraiseUtils() {}

    /**
     * Check if the {@link Material} can be appraised
     *
     * @param type the {@link Material} to be checked
     *
     * @return if the {@link Material} can be appraised
     */
    public static boolean isAppraisableMaterial(@Nonnull Material type) {
        Validate.notNull(type, "type should not be null");

        return MinecraftTag.ARMOR.isTagged(type)
            || MinecraftTag.SWORD.isTagged(type);
    }

    /**
     * Check if the {@link ItemStack} can be used in appraisal machine
     *
     * @param itemStack if the {@link ItemStack} to be checked
     *
     * @return if the {@link ItemStack} can be used in appraisal machine
     */
    public static boolean isAppraiseable(@Nonnull ItemStack itemStack) {
        Validate.notNull(itemStack, "itemStack should not be null");
        Validate.notNull(itemStack.getItemMeta(), "itemMeta should not be null");

        PersistentDataContainer pdc = itemStack.getItemMeta().getPersistentDataContainer();
        return pdc.has(Keys.APPRAISE_LEVEL, PersistentDataType.BYTE) &&
            pdc.get(Keys.APPRAISE_LEVEL, PersistentDataType.BYTE) == 1;
    }

    /**
     * Set the {@link ItemStack} to be appraisable in appraisal machine
     *
     * @param itemStack the {@link ItemStack} to be set
     */
    public static void setAppraisable(@Nonnull ItemStack itemStack) {
        Validate.notNull(itemStack, "itemStack should not be null");
        Validate.notNull(itemStack.getItemMeta(), "itemMeta should not be null");

        ItemMeta meta = itemStack.getItemMeta();

        // TODO: 未完成
        // set lore
        if (meta.hasLore()) {
            List<String> lore = meta.getLore();
            for (int i = 0; i < lore.size(); i++) {
                if (lore.get(i).equals(ChatUtil.color(Bump.getLocalization().getString("lores.not-appraised")))) {

                }
            }
        }
    }

    public static boolean isAppraised(@Nonnull ItemStack itemStack) {
        Validate.notNull(itemStack, "itemStack should not be null");
        Validate.notNull(itemStack.getItemMeta(), "itemMeta should not be null");

        return itemStack.getItemMeta().getPersistentDataContainer().has(Keys.APPRAISE_LEVEL, PersistentDataType.INTEGER);
    }
}
