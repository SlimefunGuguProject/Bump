package io.github.slimefunguguproject.bump.implementation.appraise;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.annotation.Nonnull;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.slimefunguguproject.bump.api.appraise.AppraiseResult;
import io.github.slimefunguguproject.bump.api.appraise.AppraiseType;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.utils.Keys;
import io.github.slimefunguguproject.bump.utils.Utils;
import io.github.slimefunguguproject.bump.utils.ValidateUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;

import net.guizhanss.guizhanlib.minecraft.utils.ChatUtil;

/**
 * The {@link AppraiseManager} hold all {@link AppraiseType} for each equipment.
 * <p>
 * TODO: make values configurable, api based.
 *
 * @author ybw0014
 * @author haiman233
 */
public final class AppraiseManager {

    private final Map<AppraiseTypes, AppraiseType> attributesMap = new EnumMap<>(AppraiseTypes.class);
    private final String appraisedLorePrefix;

    public AppraiseManager() {
        setup();

        String lore = ChatUtil.color(Bump.getLocalization().getString("lores.appraised"));
        appraisedLorePrefix = lore.substring(0, lore.indexOf("{0}"));
    }

    private void setup() {
//        attributesMap.put(AppraiseTypes.WEAPON, new AppraiseType()
//            .addAttribute(Attribute.GENERIC_ATTACK_DAMAGE, 0, 30, 40)
//            .addAttribute(Attribute.GENERIC_ATTACK_SPEED, -3, 10, 25)
//            .addAttribute(Attribute.GENERIC_MOVEMENT_SPEED, -0.4, 0.6, 15)
//            .addAttribute(Attribute.GENERIC_LUCK, -3, 10, 10)
//            .addAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK, -2, 12, 10)
//            .build());
//
//        attributesMap.put(AppraiseTypes.ARMOR, new AppraiseType()
//            .addAttribute(Attribute.GENERIC_ARMOR, 0, 30, 40)
//            .addAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS, -2, 12, 25)
//            .addAttribute(Attribute.GENERIC_MAX_HEALTH, -5, 12, 15)
//            .addAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE, -0.2, 0.8, 10)
//            .addAttribute(Attribute.GENERIC_FLYING_SPEED, -3, 5, 7)
//            .addAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS, -5, 5, 3)
//            .build());
//
//        attributesMap.put(AppraiseTypes.HORSE_ARMOR, new AppraiseType()
//            .addAttribute(Attribute.GENERIC_MAX_HEALTH, 0, 30, 30)
//            .addAttribute(Attribute.GENERIC_ARMOR, -5, 30, 15)
//            .addAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS, -2, 12, 10)
//            .addAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE, -0.2, 0.8, 5)
//            .addAttribute(Attribute.HORSE_JUMP_STRENGTH, -0.5, 1.4, 20)
//            .addAttribute(Attribute.GENERIC_MOVEMENT_SPEED, -0.5, 1.2, 15)
//            .addAttribute(Attribute.GENERIC_FOLLOW_RANGE, -50, 250, 5)
//            .build());
    }

    /**
     * This method applies the {@link AppraiseResult appraisal result} to {@link ItemStack}.
     *
     * @param itemStack The {@link ItemStack} to be appraised
     * @return If the item is appraised
     */
    public boolean appraiseItem(@Nonnull ItemStack itemStack) {
        if (!ValidateUtils.noAirItem(itemStack)) {
            throw new IllegalArgumentException("ItemStack should not be empty");
        }

        ItemMeta im = itemStack.getItemMeta();
        AppraiseTypes appraiseTypes;

        // Get appraisal type
        try {
            appraiseTypes = AppraiseTypes.getFromMaterial(itemStack.getType());
        } catch (IllegalArgumentException ex) {
            Bump.log(Level.SEVERE, "An ItemStack with invalid material was trying to be appraised.");
            return false;
        }

        // Check attributes
        if (!attributesMap.containsKey(appraiseTypes)) {
            Bump.log(Level.SEVERE, "This appraisal type is not configured correctly. Please report this issue.");
            return false;
        }

        // Apply result
        AppraiseResult result = attributesMap.get(appraiseTypes).appraise();
        result.apply(im, appraiseTypes.getEquipmentSlot(itemStack.getType()));

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
        if (!ValidateUtils.noAirItem(itemStack)) {
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

        // attributes
        AppraiseTypes appraiseTypes;
        try {
            appraiseTypes = AppraiseTypes.getFromMaterial(itemStack.getType());
        } catch (IllegalArgumentException ex) {
            return false;
        }

        im.removeAttributeModifier(appraiseTypes.getEquipmentSlot(itemStack.getType()));

        itemStack.setItemMeta(im);
        return true;
    }
}
