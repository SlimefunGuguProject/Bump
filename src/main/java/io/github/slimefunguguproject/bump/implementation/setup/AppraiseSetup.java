package io.github.slimefunguguproject.bump.implementation.setup;

import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import javax.annotation.Nonnull;

import org.bukkit.configuration.ConfigurationSection;

import io.github.slimefunguguproject.bump.api.appraise.AppraiseType;
import io.github.slimefunguguproject.bump.implementation.Bump;

import net.guizhanss.guizhanlib.slimefun.addon.AddonConfig;

import lombok.experimental.UtilityClass;

/**
 * This class set up the default appraisal types.
 *
 * @author ybw0014
 */
@UtilityClass
public final class AppraiseSetup {
    public static void setupTypes(@Nonnull AddonConfig config) {
        Set<String> types = config.getKeys(false);
        for (String type : types) {
            try {
                AppraiseType appraiseType = new AppraiseType(type)
                    .setName(config.getString(type + ".name"));
            } catch (IllegalArgumentException ex) {
                Bump.log(Level.SEVERE, "An error occured while trying to register appraise type {0}: {1}", type, ex.getMessage());
            }
        }
    }

    public static void setupStars() {
        final AddonConfig config = Bump.getRegistry().getConfig();
        final Map<Byte, Byte> starThreshold = Bump.getRegistry().getStarThresholds();
        ConfigurationSection section = config.getConfigurationSection("appraise.stars");
        if (section == null) {
            Bump.log(Level.WARNING, "Config section 'appraise.stars' is missing in config. Using default star thresholds.");
            setDefaultStarThreshold(starThreshold);
            return;
        }
        Set<String> keys = section.getKeys(false);
        try {
            for (String keyStr : keys) {
                int key = Integer.parseInt(keyStr);
                int value = section.getInt(keyStr);
            }
        } catch (NumberFormatException ex) {
            Bump.log(Level.SEVERE, ex, "An error occured while trying to load appraise star thresholds.");
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
