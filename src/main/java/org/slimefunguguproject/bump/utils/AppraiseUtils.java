package org.slimefunguguproject.bump.utils;

import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import net.guizhanss.guizhanlib.minecraft.MinecraftTag;
import net.guizhanss.guizhanlib.utils.ChatUtil;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slimefunguguproject.bump.implementation.Bump;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility methods for appraise
 *
 * @author ybw0014
 * @author haiman233
 */
public final class AppraiseUtils {
    private AppraiseUtils() {}

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

        return PersistentDataAPI.getByte(itemStack.getItemMeta(), Keys.APPRAISABLE) == 1;
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

    /**
     * Apply appraisal result to {@link ItemStack}
     *
     * @param itemStack the {@link ItemStack} to be dealt with
     */
    public static void applyAppraise(@Nonnull ItemStack itemStack) {
        Validate.notNull(itemStack, "itemStack should not be null");
        Validate.notNull(itemStack.getItemMeta(), "itemMeta should not be null");

        ItemMeta im = itemStack.getItemMeta();
        EquipmentSlot slot = getEquipmentSlot(itemStack.getType());
        int stars = 0;

        if (MinecraftTag.SWORD.isTagged(itemStack)) {
            // swords can be applied with damage and attack apeed modifier
            double damage = ThreadLocalRandom.current().nextDouble(0, 30);
            double attackSpeed = ThreadLocalRandom.current().nextDouble(-3, 10);
            double speed = ThreadLocalRandom.current().nextDouble(-0.4, 0.6);
            double luck = ThreadLocalRandom.current().nextDouble(-3, 10);
            double attackKnkockback = ThreadLocalRandom.current().nextDouble(-2, 12);
            im.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "DAMAGE", damage, AttributeModifier.Operation.ADD_NUMBER, slot));
            im.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "ASPEED", attackSpeed, AttributeModifier.Operation.ADD_NUMBER, slot));
            im.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "SPEED", speed, AttributeModifier.Operation.ADD_NUMBER, slot));
            im.addAttributeModifier(Attribute.GENERIC_LUCK, new AttributeModifier(UUID.randomUUID(), "LUCK", luck, AttributeModifier.Operation.ADD_NUMBER, slot));
            im.addAttributeModifier(Attribute.GENERIC_ATTACK_KNOCKBACK, new AttributeModifier(UUID.randomUUID(), "ATTACKK_NOCKBACK", attackKnkockback, AttributeModifier.Operation.ADD_NUMBER, slot));
            
            // the star is determined by damage only
            stars = getLevelByLimit(damage, 0, 30);
        } else if (MinecraftTag.ARMOR.isTagged(itemStack)) {
            // armor can be applied with armor modifier
            double armor = ThreadLocalRandom.current().nextDouble(0, 30);
            double armorToughness = ThreadLocalRandom.current().nextDouble(-2, 12);
            double maxHealth = ThreadLocalRandom.current().nextDouble(-5, 12);
            double knockbackResistance = ThreadLocalRandom.current().nextDouble(-0.2, 0.8);
            double flyingSpeed = ThreadLocalRandom.current().nextDouble(-3, 5);
            im.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "ARMOR", armor, AttributeModifier.Operation.ADD_NUMBER, slot));
            im.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "ARMOR_TOUGHNESS", armorToughness, AttributeModifier.Operation.ADD_NUMBER, slot));
            im.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "MAX_HEALTH", maxHealth, AttributeModifier.Operation.ADD_NUMBER, slot));
            im.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "KNOCKBACK_RESISTANCE", knockbackResistance, AttributeModifier.Operation.ADD_NUMBER, slot));
            im.addAttributeModifier(Attribute.GENERIC_FLYING_SPEED, new AttributeModifier(UUID.randomUUID(), "FLYING_SPEED", flyingSpeed, AttributeModifier.Operation.ADD_NUMBER, slot));
            
            // the star is determined by armor only
            stars = getLevelByLimit(armor, 0, 30);
        } else if (MinecraftTag.HORSE_ARMOR.isTagged(itemStack)) {
            // horse armor can be applied with horse armor modifier
            double maxHealth = ThreadLocalRandom.current().nextDouble(0, 30);
            double armor = ThreadLocalRandom.current().nextDouble(-5, 30);
            double armorToughness = ThreadLocalRandom.current().nextDouble(-2, 12);
            double knockbackResistance = ThreadLocalRandom.current().nextDouble(-0.2, 0.8);
            double horseJump = ThreadLocalRandom.current().nextDouble(-0.5, 1.4);
            double speed = ThreadLocalRandom.current().nextDouble(-0.5, 1.2);
            double followRange = ThreadLocalRandom.current().nextDouble(-10, 250);
            im.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "MAX_HEALTH", maxHealth, AttributeModifier.Operation.ADD_NUMBER, slot));
            im.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "ARMOR", armor, AttributeModifier.Operation.ADD_NUMBER, slot));
            im.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "ARMOR_TOUGHNESS", armorToughness, AttributeModifier.Operation.ADD_NUMBER, slot));
            im.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "KNOCKBACK_RESISTANCE", knockbackResistance, AttributeModifier.Operation.ADD_NUMBER, slot));
            im.addAttributeModifier(Attribute.HORSE_JUMP_STRENGTH, new AttributeModifier(UUID.randomUUID(), "HORSE_JUMP_STRENGTH", horseJump, AttributeModifier.Operation.ADD_SCALAR, slot));
            im.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "SPEED", speed, AttributeModifier.Operation.ADD_SCALAR, slot));
            im.addAttributeModifier(Attribute.GENERIC_FOLLOW_RANGE, new AttributeModifier(UUID.randomUUID(), "FOLLOW_RANGE", followRange, AttributeModifier.Operation.ADD_NUMBER, slot));
            
            // the star is determined by max health only
            stars = getLevelByLimit(maxHealth, 0, 30);
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
        PersistentDataAPI.setByte(im, Keys.APPRAISE_LEVEL, (byte) stars);

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
        if (BumpTag.OFF_HAND_ITEM.isTagged(type)) {
            return EquipmentSlot.OFF_HAND;
        } else if (MinecraftTag.HELMET.isTagged(type)) {
            return EquipmentSlot.HEAD;
        } else if (MinecraftTag.CHESTPLATE.isTagged(type)
                || MinecraftTag.HORSE_ARMOR.isTagged(type)) {
            return EquipmentSlot.CHEST;
        } else if (MinecraftTag.LEGGINGS.isTagged(type)) {
            return EquipmentSlot.LEGS;
        } else if (MinecraftTag.BOOTS.isTagged(type)) {
            return EquipmentSlot.FEET;
        } else {
            return EquipmentSlot.HAND;
        }
    }
}
