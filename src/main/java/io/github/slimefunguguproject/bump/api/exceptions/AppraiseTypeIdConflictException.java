package io.github.slimefunguguproject.bump.api.exceptions;

import java.io.Serial;

import javax.annotation.ParametersAreNonnullByDefault;

import io.github.slimefunguguproject.bump.api.appraise.AppraiseType;

/**
 * An {@link AppraiseTypeIdConflictException} is thrown whenever two {@link AppraiseType}
 * are trying to register with same id.
 *
 * @author ybw0014
 */
public class AppraiseTypeIdConflictException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1145141919810L;

    /**
     * Constructs a new {@link AppraiseTypeIdConflictException} with given {@link AppraiseType}.
     *
     * @param type1 The first {@link AppraiseType} with this id
     * @param type2 The second {@link AppraiseType} with this id
     */
    @ParametersAreNonnullByDefault
    public AppraiseTypeIdConflictException(AppraiseType type1, AppraiseType type2) {
        super("Two appraise types have conflicting ids: " + type1 + " and " + type2);
    }

}
