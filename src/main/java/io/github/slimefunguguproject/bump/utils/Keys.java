package io.github.slimefunguguproject.bump.utils;

import org.bukkit.NamespacedKey;

import io.github.slimefunguguproject.bump.implementation.Bump;

import lombok.experimental.UtilityClass;

/**
 * Some reusable {@link NamespacedKey Namespaced Keys}.
 *
 * @author ybw0014
 */
@UtilityClass
public final class Keys {
    public static final NamespacedKey APPRAISABLE = Bump.createKey("appraisable");
    public static final NamespacedKey APPRAISE_LEVEL = Bump.createKey("appraise_level");
    public static final NamespacedKey LAST_USED = Bump.createKey("last_used");
    public static final NamespacedKey SKY_SWORD_PROTECTED = Bump.createKey("sky_sword_protected");
    public static final NamespacedKey PROJECTILE = Bump.createKey("projectile");
}
