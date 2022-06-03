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
            .add(Attribute.GENERIC_ATTACK_DAMAGE, 0, 30)
            .add(Attribute.GENERIC_ATTACK_SPEED, -3, 10)
            .add(Attribute.GENERIC_MOVEMENT_SPEED, -0.4, 0.6)
            .add(Attribute.GENERIC_LUCK, -3, 10)
            .add(Attribute.GENERIC_ATTACK_KNOCKBACK, -2, 12)
            .build();

        // TODO: armor & horse armor
        armorAttrs = new AppraiseAttributes()
            .build();

        horseArmorAttrs = new AppraiseAttributes()
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
