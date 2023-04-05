package io.github.slimefunguguproject.bump.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.inventory.EquipmentSlot;

import io.github.slimefunguguproject.bump.utils.constant.Patterns;
import io.github.slimefunguguproject.bump.utils.tags.BumpTag;
import io.github.thebusybiscuit.slimefun4.libraries.commons.lang.Validate;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.CommonPatterns;

import lombok.experimental.UtilityClass;

import net.guizhanss.guizhanlib.slimefun.addon.AddonConfig;

/**
 * This utility class contains methods about configuration files.
 *
 * @author ybw0014
 */
@UtilityClass
public final class ConfigUtils {
    /**
     * Add the missing config options in {@link AddonConfig}.
     *
     * @param config The {@link AddonConfig} to deal with.
     */
    public static void addMissingOptions(AddonConfig config) {
        Configuration defaultConfig = config.getDefaults();
        for (String key : defaultConfig.getKeys(true)) {
            if (!config.contains(key)) {
                config.set(key, defaultConfig.get(key));
            }
        }
    }

    /**
     * This method will parse {@link List} of material {@link String} into
     * {@link Set} of {@link Material}.
     * <br>
     * Acceptable elements: minecraft materials, bump tags.
     *
     * @param materialList The {@link List} of material {@link String} to be parsed.
     *
     * @return The parsed {@link Set} of {@link Material}.
     *
     * @throws IllegalArgumentException      when the list is {@code null} or contains null elements.
     * @throws InvalidConfigurationException when the list contains invalid material or BumpTag.
     * @see BumpTag
     */
    @Nonnull
    public static Set<Material> parseMaterials(@Nonnull List<String> materialList) throws InvalidConfigurationException {
        Validate.noNullElements(materialList);

        Set<Material> materials = new HashSet<>();
        for (String value : materialList) {
            if (Patterns.MINECRAFT_NAMESPACEDKEY.matcher(value).matches()) {
                Material material = Material.matchMaterial(value);

                if (material != null) {
                    materials.add(material);
                } else {
                    throw new InvalidConfigurationException("Invalid minecraft material: " + value);
                }
            } else if (Patterns.BUMP_TAG_CONFIG.matcher(value).matches()) {
                String keyValue = CommonPatterns.COLON.split(value)[1].toUpperCase(Locale.ROOT);
                BumpTag tag = BumpTag.getTag(keyValue);

                if (tag != null) {
                    materials.addAll(tag.getValues());
                } else {
                    throw new InvalidConfigurationException("Invalid BumpTag: " + keyValue);
                }
            }
        }
        return materials;
    }

    /**
     * This method will parse {@link List} of equipment slot {@link String} into
     * {@link Set} of {@link EquipmentSlot}.
     *
     * @param slotList The {@link List} of equipment slot {@link String} to be parsed.
     *
     * @return The parsed {@link Set} of {@link EquipmentSlot}.
     *
     * @throws IllegalArgumentException      when the list is {@code null} or contains null elements.
     * @throws InvalidConfigurationException when the list contains invalid EquipmentSlot.
     */
    @Nonnull
    public static Set<EquipmentSlot> parseEquipmentSlots(@Nonnull List<String> slotList) throws InvalidConfigurationException {
        Validate.noNullElements(slotList);

        Set<EquipmentSlot> equipmentSlots = new HashSet<>();
        for (String value : slotList) {
            try {
                EquipmentSlot slot = EquipmentSlot.valueOf(value.toUpperCase(Locale.ROOT));
                equipmentSlots.add(slot);
            } catch (IllegalArgumentException ex) {
                throw new InvalidConfigurationException("Invalid EquipmentSlot value: " + value);
            }
        }
        return equipmentSlots;
    }
}
