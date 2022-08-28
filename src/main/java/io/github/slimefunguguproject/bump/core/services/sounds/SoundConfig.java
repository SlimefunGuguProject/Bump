package io.github.slimefunguguproject.bump.core.services.sounds;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

/**
 * A wrapper of sound configuration.
 *
 * @author ybw0014
 */
record SoundConfig(@Nonnull String sound, float volume, float pitch) {
    public SoundConfig {
        Preconditions.checkArgument(sound != null, "Sound cannot be null");
    }
}
