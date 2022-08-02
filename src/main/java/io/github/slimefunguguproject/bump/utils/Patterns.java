package io.github.slimefunguguproject.bump.utils;

import java.util.regex.Pattern;

import lombok.experimental.UtilityClass;

/**
 * This class contains some {@link Pattern} used in Bump.
 *
 * @author ybw0014
 */
@UtilityClass
public final class Patterns {
    public static final Pattern MINECRAFT_NAMESPACEDKEY = Pattern.compile("minecraft:[a-z0-9/._-]+");
    public static final Pattern BUMP_TAG = Pattern.compile("#bump:[a-z_]+");
}
