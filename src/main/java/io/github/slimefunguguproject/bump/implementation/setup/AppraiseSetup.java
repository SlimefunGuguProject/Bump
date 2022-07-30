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
        final Map<Integer, Integer> starThreshold = Bump.getRegistry().getStarThresholds();
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

    private void setDefaultStarThreshold(@Nonnull Map<Integer, Integer> starThreshold) {
        starThreshold.clear();
        starThreshold.put(100, 20);
        starThreshold.put(98, 10);
        starThreshold.put(96, 9);
        starThreshold.put(92, 8);
        starThreshold.put(88, 7);
        starThreshold.put(82, 6);
        starThreshold.put(74, 5);
        starThreshold.put(64, 4);
        starThreshold.put(48, 3);
        starThreshold.put(30, 2);
        starThreshold.put(10, 1);
    }
}
