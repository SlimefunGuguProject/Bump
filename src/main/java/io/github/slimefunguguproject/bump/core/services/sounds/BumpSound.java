package io.github.slimefunguguproject.bump.core.services.sounds;

import java.util.logging.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import io.github.slimefunguguproject.bump.implementation.Bump;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This enum contains almost all configurable sounds in Bump.
 */
@Getter
@RequiredArgsConstructor
@SuppressWarnings("ConstantConditions")
public enum BumpSound {
    APPRAISAL_INSTRUMENT_FAIL(Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F),
    APPRAISAL_INSTRUMENT_SUCCEED(Sound.ENTITY_VILLAGER_CELEBRATE, 1.0F, 1.0F),
    APPRAISE_TYPE_SELECT(Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F),
    APPRAISE_TYPE_SELECTOR_OPEN(Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1.0F, 1.0F),
    ATTRIBUTE_GRINDSTONE_FAIL(Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F),
    ATTRIBUTE_GRINDSTONE_SUCCEED(Sound.ENTITY_VILLAGER_CELEBRATE, 1.0F, 1.0F),
    QUALITY_IDENTIFIER_OPEN(Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1.0F, 1.0F),
    QUALITY_IDENTIFIER_FAIL(Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F),
    QUALITY_IDENTIFIER_SUCCEED(Sound.ENTITY_VILLAGER_CELEBRATE, 1.0F, 1.0F),
    DEVIL_SWORD_USE(Sound.ENTITY_BLAZE_SHOOT, 1.0F, 1.0F),
    SKY_SWORD_USE(Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F),
    SOUL_SWORD_USE(Sound.AMBIENT_CAVE, 1.0F, 1.0F),
    WITHER_SKULL_BOW_USE(Sound.ENTITY_WITHER_SHOOT, 1.0F, 1.0F);

    private final String sound;
    private final float volume;
    private final float pitch;

    BumpSound(@Nonnull Sound sound, float volume, float pitch) {
        Preconditions.checkArgument(sound != null, "Sound cannot be null");

        this.sound = sound.getKey().getKey();
        this.volume = volume;
        this.pitch = pitch;
    }

    @Nullable
    private SoundConfig getSoundConfig() {
        SoundConfig config = Bump.getSoundService().getSoundConfig(this);

        if (config == null) {
            Bump.getLocalization().log(Level.WARNING, "invalid-sound-config", name());
        }

        return config;
    }

    /**
     * Play this {@link BumpSound} at {@link Player}'s eye location.
     *
     * @param p The {@link Player} which to play the {@link BumpSound} to.
     */
    public void playFor(@Nonnull Player p) {
        Preconditions.checkArgument(p != null, "Player cannot be null.");

        SoundConfig config = getSoundConfig();

        if (config != null) {
            p.playSound(p.getEyeLocation(), config.sound(), SoundCategory.PLAYERS, config.volume(), config.pitch());
        }
    }

    /**
     * Play this {@link BumpSound} at the given {@link Location} using the
     * provided {@link SoundCategory}.
     *
     * @param loc      The {@link Location} at which to play the {@link BumpSound}.
     * @param category The {@link SoundCategory} that should be used.
     */
    public void playAt(@Nonnull Location loc, @Nonnull SoundCategory category) {
        Preconditions.checkArgument(loc != null, "The location cannot be null.");

        SoundConfig config = getSoundConfig();

        if (config != null) {
            loc.getWorld().playSound(loc, config.sound(), category, config.volume(), config.pitch());
        }
    }

    /**
     * Play this {@link BumpSound} at the given {@link Block}.
     *
     * @param block The {@link Block} at which to play the {@link BumpSound}.
     */
    public void playAt(@Nonnull Block block) {
        Preconditions.checkArgument(block != null, "The block cannot be null.");

        playAt(block.getLocation(), SoundCategory.BLOCKS);
    }
}
