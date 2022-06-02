package bxx2004.bump.appraise;

import bxx2004.bump.util.BumpTag;
import org.apache.commons.lang.Validate;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * The {@link AppraiseManager} hold all {@link AppraiseAttributes} for each equipment.
 *
 * @author ybw0014
 * @author haiman233
 */
public final class AppraiseManager {

    private static AppraiseManager instance;

    private AppraiseAttributes swordAttrs;
    private AppraiseAttributes armorAttrs;
    private AppraiseAttributes horseArmorAttrs;

    public AppraiseManager() {
        Validate.isTrue(instance == null, "AppraiseManager has already been initialized");
        instance = this;
        setup();
    }

    private void setup() {
        swordAttrs = new AppraiseAttributes()
            .add(Attribute.GENERIC_ATTACK_DAMAGE, 0, 30)
            .add(Attribute.GENERIC_ATTACK_SPEED, -3, 10)
            .add(Attribute.GENERIC_MOVEMENT_SPEED, -0.4, 0.6)
            .add(Attribute.GENERIC_LUCK, -3, 10)
            .add(Attribute.GENERIC_ATTACK_KNOCKBACK, -2, 12)
            .build();
        // TODO: armor & horse armor
    }

    public void appraiseItem(@Nonnull ItemStack itemStack) {
        Validate.notNull(itemStack, "ItemStack cannot be null");

        if (BumpTag.WEAPON.isTagged(itemStack)) {

        } else if (BumpTag.ARMOR.isTagged(itemStack)) {

        } else if (BumpTag.HORSE_ARMOR.isTagged(itemStack)) {

        }
    }
}
