package org.slimefunguguproject.bump.implementation.appraise;

import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import net.guizhanss.guizhanlib.utils.ChatUtil;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slimefunguguproject.bump.api.appraise.AppraiseAttributes;
import org.slimefunguguproject.bump.api.appraise.AppraiseResult;
import org.slimefunguguproject.bump.implementation.Bump;
import org.slimefunguguproject.bump.utils.Keys;
import org.slimefunguguproject.bump.utils.Utils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * The {@link AppraiseManager} hold all {@link AppraiseAttributes} for each equipment.
 *
 * @author ybw0014
 * @author haiman233
 */
public final class AppraiseManager {

    private final Map<AppraiseType, AppraiseAttributes> attributesMap = new HashMap<>();
    private final String appraisedLorePrefix;

    public AppraiseManager() {
        setup();

        String lore = ChatUtil.color(Bump.getLocalization().getString("lores.appraised"));
        appraisedLorePrefix = lore.substring(0, lore.indexOf("{0}"));
    }

    private void setup() {
        attributesMap.put(AppraiseType.WEAPON, new AppraiseAttributes()
            .add(Attribute.GENERIC_ATTACK_DAMAGE, 0, 30, 40)
            .add(Attribute.GENERIC_ATTACK_SPEED, -3, 10, 25)
            .add(Attribute.GENERIC_MOVEMENT_SPEED, -0.4, 0.6, 15)
            .add(Attribute.GENERIC_LUCK, -3, 10, 10)
            .add(Attribute.GENERIC_ATTACK_KNOCKBACK, -2, 12, 10)
            .build());

        attributesMap.put(AppraiseType.ARMOR, new AppraiseAttributes()
            .add(Attribute.GENERIC_ARMOR, 0, 30, 40)
            .add(Attribute.GENERIC_ARMOR_TOUGHNESS, -2, 12, 25)
            .add(Attribute.GENERIC_MAX_HEALTH, -5, 12, 15)
            .add(Attribute.GENERIC_KNOCKBACK_RESISTANCE, -0.2, 0.8, 10)
            .add(Attribute.GENERIC_FLYING_SPEED, -3, 5, 7)
            .add(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS, -5, 5, 3)
            .build());

        attributesMap.put(AppraiseType.HORSE_ARMOR, new AppraiseAttributes()
            .add(Attribute.GENERIC_MAX_HEALTH, 0, 30, 30)
            .add(Attribute.GENERIC_ARMOR, -5, 30, 15)
            .add(Attribute.GENERIC_ARMOR_TOUGHNESS, -2, 12, 10)
            .add(Attribute.GENERIC_KNOCKBACK_RESISTANCE, -0.2, 0.8, 5)
            .add(Attribute.HORSE_JUMP_STRENGTH, -0.5, 1.4, 20)
            .add(Attribute.GENERIC_MOVEMENT_SPEED, -0.5, 1.2, 15)
            .add(Attribute.GENERIC_FOLLOW_RANGE, -50, 250, 5)
            .build());
    }

    /**
     * This method applies the {@link AppraiseResult appraisal result} to {@link ItemStack}.
     *
     * @param itemStack The {@link ItemStack} to be appraised
     * @return If the item is appraised
     */
    public boolean appraiseItem(@Nonnull ItemStack itemStack) {
        Validate.notNull(itemStack, "ItemStack should not be null");
        if (itemStack.getType() == Material.AIR) {
            throw new IllegalArgumentException("ItemStack should not be empty");
        }

        ItemMeta im = itemStack.getItemMeta();
        AppraiseType appraiseType;

        // Get appraisal type
        try {
            appraiseType = AppraiseType.getFromMaterial(itemStack.getType());
        } catch (IllegalArgumentException ex) {
            Bump.log(Level.SEVERE, "An ItemStack with invalid material was trying to be appraised.");
            return false;
        }

        // Check attributes
        if (!attributesMap.containsKey(appraiseType)) {
            Bump.log(Level.SEVERE, "This appraisal type is not configured correctly. Please report this issue.");
            return false;
        }

        // Apply result
        AppraiseResult result = attributesMap.get(appraiseType).appraise();
        result.apply(im, appraiseType.getEquipmentSlot(itemStack.getType()));

        // Set lore
        List<String> lore = im.getLore();
        for (int i = 0; i < lore.size(); i++) {
            if (lore.get(i).equals(ChatUtil.color(Bump.getLocalization().getString("lores.not-appraised")))) {
                String line = Bump.getLocalization().getString("lores.appraised", Utils.getStars(result.getStars()));
                lore.set(i, ChatUtil.color(line));
                break;
            }
        }
        im.setLore(lore);

        // Set pdc
        PersistentDataAPI.setByte(im, Keys.APPRAISE_LEVEL, (byte) result.getStars());

        // wrap up
        itemStack.setItemMeta(im);
        return true;
    }

    /**
     * Purge appraisal result (all attribute modifiers).
     *
     * @param itemStack The {@link ItemStack} to be handled.
     * @return If the purge process succeeds.
     */
    public boolean clearAttributes(@Nonnull ItemStack itemStack) {
        if (!Utils.validateItem(itemStack)) {
            return false;
        }

        ItemMeta im = itemStack.getItemMeta();

        // pdc
        PersistentDataAPI.setByte(im, Keys.APPRAISABLE, (byte) 1);
        PersistentDataAPI.remove(im, Keys.APPRAISE_LEVEL);

        // lore
        List<String> lore;
        if (im.hasLore()) {
            lore = im.getLore();
        } else {
            lore = new ArrayList<>();
        }
        for (int i = 0; i < lore.size(); i++) {
            if (lore.get(i).startsWith(appraisedLorePrefix)) {
                lore.set(i, ChatUtil.color(Bump.getLocalization().getString("lores.not-appraised")));
                break;
            }
        }
        im.setLore(lore);
        // check existing

        // attributes
        AppraiseType appraiseType;
        try {
            appraiseType = AppraiseType.getFromMaterial(itemStack.getType());
        } catch (IllegalArgumentException ex) {
            return false;
        }

        im.removeAttributeModifier(appraiseType.getEquipmentSlot(itemStack.getType()));

        itemStack.setItemMeta(im);
        return true;
    }
}
