package io.github.slimefunguguproject.bump.implementation.setup;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.inventory.EquipmentSlot;

import io.github.slimefunguguproject.bump.api.appraise.AppraiseType;
import io.github.slimefunguguproject.bump.api.exceptions.AppraiseTypeKeyConflictException;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.utils.ConfigUtils;

import net.guizhanss.guizhanlib.slimefun.addon.AddonConfig;

import lombok.experimental.UtilityClass;

/**
 * This class set up the default appraisal types.
 * <p>
 * The logs in this class should be localizable.
 *
 * @author ybw0014
 */
@UtilityClass
public final class AppraiseSetup {
    public static void setupTypes() {
        AddonConfig config = new AddonConfig("appraise-types.yml");
        config.save();
        config.reload();

        Bump.getLocalization().log(Level.INFO, "loading-appraise-types");
        Set<String> types = config.getKeys(false);
        for (String type : types) {
            Bump.getLocalization().log(Level.INFO, "loading-appraise-type", type);
            if (!config.getBoolean(type + ".enabled")) {
                Bump.getLocalization().log(Level.INFO, "disabled-appraise-type", type);
                continue;
            }

            try {
                // raw values
                String name = config.getString(type + ".name");
                List<String> description = config.getStringList(type + ".description");
                String permission = config.getString(type + ".permission");
                String equipmentTypeStr = config.getString(type + ".equipment-type");
                boolean checkMaterial = config.getBoolean(type + ".check-material");
                List<String> validMaterials = config.getStringList(type + ".materials");
                List<String> validSlimefunItemIds = config.getStringList(type + ".slimefun-items");
                List<String> validEquipmentSlots = config.getStringList(type + ".equipment-slots");
                ConfigurationSection attributesSection = config.getConfigurationSection(type + ".attributes");
                Set<String> attributes = attributesSection.getKeys(false);

                // parsed values
                AppraiseType.EquipmentType equipmentType = AppraiseType.EquipmentType.valueOf(equipmentTypeStr);
                Set<EquipmentSlot> equipmentSlots = ConfigUtils.parseEquipmentSlots(validEquipmentSlots);
                Set<Material> materials = ConfigUtils.parseMaterials(validMaterials);

                AppraiseType appraiseType = new AppraiseType(Bump.createKey(type))
                    .setName(name)
                    .setPermission(permission)
                    .setDescription(description)
                    .setEquipmentType(equipmentType)
                    .addValidEquipmentSlots(equipmentSlots)
                    .checkMaterial(checkMaterial)
                    .addValidMaterials(materials)
                    .addValidSlimefunItemIds(validSlimefunItemIds);

                for (String attr : attributes) {
                    Attribute attribute = Attribute.valueOf(attr);
                    double min = attributesSection.getDouble(attr + ".min");
                    double max = attributesSection.getDouble(attr + ".max");
                    String weightStr = attributesSection.getString(attr + ".weight");
                    double weight = -1;
                    if (weightStr != null) {
                        try {
                            weight = Double.parseDouble(weightStr);
                        } catch (NumberFormatException ignored) {
                        }
                    }
                    appraiseType.addAttribute(attribute, min, max, weight);
                }

                appraiseType.register(Bump.getInstance());
                Bump.getLocalization().log(Level.INFO, "loaded-appraise-type", type);
            } catch (NullPointerException | IllegalArgumentException | AppraiseTypeKeyConflictException |
                     InvalidConfigurationException ex) {
                Bump.getLocalization().log(Level.SEVERE, "error-loading-appraise-type", type, ex.getMessage());
            }
        }
    }

    public static void setupStars() {
        final AddonConfig config = Bump.getRegistry().getConfig();
        final Map<Byte, Byte> starThreshold = Bump.getRegistry().getStarThresholds();
        ConfigurationSection section = config.getConfigurationSection("appraise.stars");
        if (section == null) {
            Bump.getLocalization().log(Level.INFO, "missing-appraise-stars");
            Bump.getLocalization().log(Level.INFO, "use-default-appraise-stars");
            setDefaultStarThreshold(starThreshold);
            return;
        }
        Set<String> keys = section.getKeys(false);
        try {
            for (String keyStr : keys) {
                byte key = Byte.parseByte(keyStr);
                if (key < 0 || key > 100) {
                    throw new IllegalArgumentException();
                }
                int intValue = section.getInt(keyStr);
                if (intValue < 0 || intValue > 127) {
                    throw new IllegalArgumentException();
                }
                byte value = (byte) intValue;
                starThreshold.put(key, value);
            }
        } catch (IllegalArgumentException ex) {
            Bump.getLocalization().log(Level.INFO, "invalid-appraise-stars");
            Bump.getLocalization().log(Level.INFO, "use-default-appraise-stars");
            setDefaultStarThreshold(starThreshold);
        }
    }

    private void setDefaultStarThreshold(@Nonnull Map<Byte, Byte> starThreshold) {
        starThreshold.clear();
        starThreshold.put((byte) 100, (byte) 20);
        starThreshold.put((byte) 98, (byte) 10);
        starThreshold.put((byte) 96, (byte) 9);
        starThreshold.put((byte) 92, (byte) 8);
        starThreshold.put((byte) 88, (byte) 7);
        starThreshold.put((byte) 82, (byte) 6);
        starThreshold.put((byte) 74, (byte) 5);
        starThreshold.put((byte) 64, (byte) 4);
        starThreshold.put((byte) 48, (byte) 3);
        starThreshold.put((byte) 30, (byte) 2);
        starThreshold.put((byte) 10, (byte) 1);
    }
}
