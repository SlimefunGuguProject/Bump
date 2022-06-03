package org.slimefunguguproject.bump.implementation.appraise;

import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slimefunguguproject.bump.api.appraise.AppraiseAttributes;
import org.slimefunguguproject.bump.api.appraise.AppraiseResult;
import org.slimefunguguproject.bump.utils.BumpTag;

import javax.annotation.Nonnull;

/**
 * The {@link AppraiseManager} hold all {@link AppraiseAttributes} for each equipment.
 *
 * @author ybw0014
 * @author haiman233
 */
public final class AppraiseManager {

    private AppraiseAttributes weaponAttrs;
    private AppraiseAttributes armorAttrs;
    private AppraiseAttributes horseArmorAttrs;

    public AppraiseManager() {
        setup();
    }

    private void setup() {
        weaponAttrs = new AppraiseAttributes()
            .add(Attribute.GENERIC_ATTACK_DAMAGE, 0, 30, 40)
            .add(Attribute.GENERIC_ATTACK_SPEED, -3, 10, 25)
            .add(Attribute.GENERIC_MOVEMENT_SPEED, -0.4, 0.6, 15)
            .add(Attribute.GENERIC_LUCK, -3, 10, 10)
            .add(Attribute.GENERIC_ATTACK_KNOCKBACK, -2, 12, 10)
            .build();

        // TODO: armor & horse armor
        armorAttrs = new AppraiseAttributes()
            .add(Attribute.GENERIC_ARMOR, 0, 30, 40)
            .add(Attribute.GENERIC_ARMOR_TOUGHNESS, -2, 12, 25)
            .add(Attribute.GENERIC_MAX_HEALTH, -5, 12, 15)
            .add(Attribute.GENERIC_KNOCKBACK_RESISTANCE, -0.2, 0.8, 10)
            .add(Attribute.GENERIC_FLYING_SPEED, -3, 5, 7)
            .add(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS, -5, 5, 3)
            .build();

        horseArmorAttrs = new AppraiseAttributes()
            .add(Attribute.GENERIC_MAX_HEALTH, 0, 30, 30)
            .add(Attribute.GENERIC_ARMOR, -5, 30, 15)
            .add(Attribute.GENERIC_ARMOR_TOUGHNESS, -2, 12, 10)
            .add(Attribute.GENERIC_KNOCKBACK_RESISTANCE, -0.2, 0.8, 5)
            .add(Attribute.HORSE_JUMP_STRENGTH, -0.5, 1.4, 20)
            .add(Attribute.GENERIC_MOVEMENT_SPEED, -0.5, 1.2, 15)
            .add(Attribute.GENERIC_FOLLOW_RANGE, -50, 250, 5)
            .build();
    }

    public void appraiseItem(@Nonnull ItemStack itemStack) {
        Validate.notNull(itemStack, "ItemStack should not be null");
        if (itemStack.getType() == Material.AIR) {
            throw new IllegalArgumentException("ItemStack should not be empty");
        }

        ItemMeta im = itemStack.getItemMeta();
        AppraiseResult result;

        if (BumpTag.WEAPON.isTagged(itemStack)) {
            result = weaponAttrs.appraise();
        } else if (BumpTag.ARMOR.isTagged(itemStack)) {
            result = armorAttrs.appraise();
        } else if (BumpTag.HORSE_ARMOR.isTagged(itemStack)) {
            result = horseArmorAttrs.appraise();
        } else {
            throw new IllegalArgumentException("This item is invalid");
        }

        result.apply(im);
    }
}
