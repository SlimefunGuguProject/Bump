package org.slimefunguguproject.bump.utils;

import org.bukkit.NamespacedKey;

import org.slimefunguguproject.bump.implementation.Bump;

/**
 * Some reusable {@link NamespacedKey Namespaced Keys}.
 *
 * @author ybw0014
 */
public final class Keys {
    public static final NamespacedKey APPRAISABLE = Bump.createKey("appraisable");
    public static final NamespacedKey APPRAISE_LEVEL = Bump.createKey("appraise_level");
    public static final NamespacedKey LAST_USED = Bump.createKey("last_used");
    public static final NamespacedKey SKY_SWORD_PROTECTED = Bump.createKey("sky_sword_protected");
    private Keys() {
        throw new IllegalStateException("Utility class");
    }
}
