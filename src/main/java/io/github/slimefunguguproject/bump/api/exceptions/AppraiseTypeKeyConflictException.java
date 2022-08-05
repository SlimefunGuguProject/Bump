package io.github.slimefunguguproject.bump.api.exceptions;

import java.io.Serial;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.NamespacedKey;

import io.github.slimefunguguproject.bump.api.appraise.AppraiseType;

/**
 * An {@link AppraiseTypeKeyConflictException} is thrown whenever two {@link AppraiseType}
 * are trying to register with same {@link NamespacedKey}.
 *
 * @author ybw0014
 */
public class AppraiseTypeKeyConflictException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1145141919810L;

    /**
     * Constructs a new {@link AppraiseTypeKeyConflictException} with given {@link AppraiseType}.
     *
     * @param type1 The first {@link AppraiseType} with this {@link NamespacedKey}.
     * @param type2 The second {@link AppraiseType} with this {@link NamespacedKey}.
     */
    @ParametersAreNonnullByDefault
    public AppraiseTypeKeyConflictException(AppraiseType type1, AppraiseType type2) {
        super("Two appraise types have conflicting NamespacedKey: " + type1 + " and " + type2);
    }

}
