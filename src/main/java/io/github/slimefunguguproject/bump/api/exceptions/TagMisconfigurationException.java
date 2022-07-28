package io.github.slimefunguguproject.bump.api.exceptions;

import java.io.Serial;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.NamespacedKey;

import io.github.slimefunguguproject.bump.utils.tags.BumpTag;

/**
 * A {@link TagMisconfigurationException} is thrown whenever a {@link BumpTag}
 * contains illegal, invalid or unknown values.
 *
 * @author TheBusyBiscuit
 * @author ybw0014
 */
public class TagMisconfigurationException extends Exception {

    @Serial
    private static final long serialVersionUID = 1145141919810L;

    /**
     * This constructs a new {@link TagMisconfigurationException} for the given
     * {@link }'s {@link NamespacedKey} with the provided context.
     *
     * @param key The {@link NamespacedKey} of {@link BumpTag}
     * @param message The message to display
     */
    @ParametersAreNonnullByDefault
    public TagMisconfigurationException(NamespacedKey key, String message) {
        super("Tag '" + key + "' has been misconfigured: " + message);
    }

    /**
     * This constructs a new {@link io.github.thebusybiscuit.slimefun4.api.exceptions.TagMisconfigurationException} for the given
     * {@link BumpTag}'s {@link NamespacedKey} with the provided context.
     *
     * @param key The {@link NamespacedKey} of {@link BumpTag}
     * @param cause The {@link Throwable} which has caused this to happen
     */
    @ParametersAreNonnullByDefault
    public TagMisconfigurationException(NamespacedKey key, Throwable cause) {
        super("Tag '" + key + "' has been misconfigured (" + cause.getMessage() + ')', cause);
    }
}
