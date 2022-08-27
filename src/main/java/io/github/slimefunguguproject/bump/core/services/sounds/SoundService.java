package io.github.slimefunguguproject.bump.core.services.sounds;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.base.Preconditions;

import io.github.slimefunguguproject.bump.implementation.Bump;

import net.guizhanss.guizhanlib.slimefun.addon.AddonConfig;

/**
 * This service manages all sounds used in Bump.
 *
 * @author ybw0014
 */
public final class SoundService {

    private final AddonConfig config;

    private final Map<BumpSound, SoundConfig> soundMap = new EnumMap<>(BumpSound.class);

    /**
     * Initialize {@link SoundService}.
     *
     * @param soundConfig The config file of sounds.
     */
    public SoundService(@Nonnull AddonConfig soundConfig) {
        this.config = soundConfig;
    }

    /**
     * Load the configurations of all {@link BumpSound}.
     *
     * @param saveConfig Whether to save config after loading.
     */
    public void load(boolean saveConfig) {
        config.reload();

        for (BumpSound sound : BumpSound.values()) {
            try {
                loadSound(sound);
            } catch (Exception ex) {
                Bump.getLocalization().log(Level.SEVERE, "error-sound", sound.name());
            }
        }

        if (saveConfig) {
            config.save();
        }
    }

    /**
     * Get the {@link SoundConfig} for a specified {@link BumpSound}.
     *
     * @param sound The {@link BumpSound} to get {@link SoundConfig} for.
     *
     * @return The {@link SoundConfig} for the {@link BumpSound}. {@code null} if not exists.
     */
    @Nullable
    public SoundConfig getSoundConfig(@Nonnull BumpSound sound) {
        Preconditions.checkArgument(sound != null, "BumpSound cannot be null");

        return soundMap.get(sound);
    }

    private void loadSound(@Nonnull BumpSound sound) {
        setDefault(sound.name() + ".sound", sound.getSound());
        setDefault(sound.name() + ".volume", sound.getVolume());
        setDefault(sound.name() + ".pitch", sound.getPitch());

        String soundId = config.getString(sound.name() + ".sound");
        float volume = (float) config.getDouble(sound.name() + ".volume");
        float pitch = (float) config.getDouble(sound.name() + ".pitch");

        if (volume < 0) {
            Bump.getLocalization().log(Level.WARNING, "invalid-sound-volume", sound.name(), volume, 0.0F);
            volume = 0;
        }

        if (pitch < 0.5F) {
            Bump.getLocalization().log(Level.WARNING, "invalid-sound-pitch", sound.name(), volume, 0.5F);
            pitch = 0.5F;
        }

        SoundConfig soundConfig = new SoundConfig(soundId, volume, pitch);
        soundMap.put(sound, soundConfig);
    }

    @ParametersAreNonnullByDefault
    private void setDefault(String path, Object value) {
        if (!config.contains(path)) {
            config.set(path, value);
        }
    }
}
