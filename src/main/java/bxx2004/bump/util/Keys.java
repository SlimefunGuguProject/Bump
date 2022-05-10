package bxx2004.bump.util;

import bxx2004.bump.Bump;
import org.bukkit.NamespacedKey;

/**
 * Some reusable {@link NamespacedKey}s.
 *
 * @author ybw0014
 */
public final class Keys {
    private Keys() {}

    public static final NamespacedKey APPRAISABLE = Bump.createKey("appraisable");
    public static final NamespacedKey APPRAISE_LEVEL = Bump.createKey("appraise_level");
    public static final NamespacedKey LAST_USED = Bump.createKey("last_used");
}
