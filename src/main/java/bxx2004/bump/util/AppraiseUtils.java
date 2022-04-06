package bxx2004.bump.util;

import bxx2004.bump.Bump;
import net.guizhanss.guizhanlib.minecraft.MinecraftTag;
import net.guizhanss.guizhanlib.utils.ChatUtil;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

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
    public static boolean isAppraisable(@Nonnull ItemStack itemStack) {
        Validate.notNull(itemStack, "itemStack should not be null");
        Validate.notNull(itemStack.getItemMeta(), "itemMeta should not be null");

        PersistentDataContainer pdc = itemStack.getItemMeta().getPersistentDataContainer();
        return pdc.has(Keys.APPRAISABLE, PersistentDataType.BYTE)
            && pdc.get(Keys.APPRAISABLE, PersistentDataType.BYTE) == 1;
    }

    /**
     * Set the {@link ItemStack} to be appraisable in appraisal machine
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
        PersistentDataContainer pdc = im.getPersistentDataContainer();
        pdc.set(Keys.APPRAISABLE, PersistentDataType.BYTE, (byte) 1);

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

        return itemStack.getItemMeta().getPersistentDataContainer().has(Keys.APPRAISE_LEVEL, PersistentDataType.BYTE);
    }

    /**
     * Apply appraisal result to {@link ItemStack}
     *
     * @param itemStack the {@link ItemStack} to be dealt with
     */
    public static void applyAppraise(@Nonnull ItemStack itemStack) {
        Validate.notNull(itemStack, "itemStack should not be null");
        Validate.notNull(itemStack.getItemMeta(), "itemMeta should not be null");

        ItemMeta im = itemStack.getItemMeta();
        PersistentDataContainer pdc = im.getPersistentDataContainer();
        EquipmentSlot slot = getEquipmentSlot(itemStack.getType());
        int stars = 0;

        if (MinecraftTag.SWORD.isTagged(itemStack)) {
            // swords can be applied with damage and attack apeed modifier
            double damage = ThreadLocalRandom.current().nextDouble(3, 15);
            double attackSpeed = ThreadLocalRandom.current().nextDouble(0, 0.8);
            im.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "DAMAGE", damage, AttributeModifier.Operation.ADD_NUMBER, slot));
            im.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "SPEED", attackSpeed, AttributeModifier.Operation.ADD_NUMBER, slot));

            // the star is determined by damage only
            stars = getLevelByLimit(damage, 3, 15);
        } else if (MinecraftTag.ARMOR.isTagged(itemStack)) {
            // armor can be applied with armor modifier
            double armor = ThreadLocalRandom.current().nextDouble(3, 15);
            im.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "ARMOR", armor, AttributeModifier.Operation.ADD_NUMBER, slot));
            im.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "ARMOR_TOUGHNESS", 0, AttributeModifier.Operation.ADD_NUMBER, slot));

            // the star is determined by armor only
            stars = getLevelByLimit(armor, 3, 15);
        }

        // set lore
        List<String> lore = im.getLore();
        for (int i = 0; i < lore.size(); i++) {
            if (lore.get(i).equals(ChatUtil.color(Bump.getLocalization().getString("lores.not-appraised")))) {
                String line = Bump.getLocalization().getString("lores.appraised", Utils.getStars(stars));
                lore.set(i, ChatUtil.color(line));
                break;
            }
        }
        im.setLore(lore);

        // set pdc
        pdc.set(Keys.APPRAISE_LEVEL, PersistentDataType.BYTE, (byte) stars);

        itemStack.setItemMeta(im);
    }

    /**
     * Get the available {@link EquipmentSlot} of modifier for a given {@link Material} type
     *
     * @param type the {@link Material} to be analyzed
     *
     * @return an available {@link EquipmentSlot}
     */
    private static EquipmentSlot getEquipmentSlot(Material type) {
        if (MinecraftTag.SWORD.isTagged(type)) {
            return EquipmentSlot.HAND;
        } else if (MinecraftTag.HELMET.isTagged(type)) {
            return EquipmentSlot.HEAD;
        } else if (MinecraftTag.CHESTPLATE.isTagged(type)) {
            return EquipmentSlot.CHEST;
        } else if (MinecraftTag.LEGGINGS.isTagged(type)) {
            return EquipmentSlot.LEGS;
        } else if (MinecraftTag.BOOTS.isTagged(type)) {
            return EquipmentSlot.FEET;
        } else {
            return EquipmentSlot.OFF_HAND;
        }
    }

    /**
     * Get the appraise level
     *
     * @param randomValue random generated value
     * @param min minimum value
     * @param max maximum value
     * @return appraise level
     */
    private static int getLevelByLimit(double randomValue, int min, int max) {
        double percent = (randomValue - min) / (max - min) * 100;
        if (percent >= 95) {
            return 5;
        } else if (percent >= 80) {
            return 4;
        } else if (percent >= 60) {
            return 3;
        } else if (percent >= 30) {
            return 2;
        } else {
            return 1;
        }
    }
}
