package io.github.slimefunguguproject.bump.utils.tags;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;

import io.github.slimefunguguproject.bump.api.exceptions.TagMisconfigurationException;
import io.github.slimefunguguproject.bump.implementation.Bump;

/**
 * Tags for some materials.
 *
 * @author ybw0014
 */
@SuppressWarnings("ConstantConditions")
public enum BumpTag implements Tag<Material> {
    /**
     * This includes all swords.
     */
    SWORD,

    /**
     * This includes all helmets.
     */
    HELMET,

    /**
     * This includes all chestplates.
     */
    CHESTPLATE,

    /**
     * This includes all leggings.
     */
    LEGGINGS,

    /**
     * This includes all boots.
     */
    BOOTS,

    /**
     * This includes all horse armors.
     */
    HORSE_ARMOR,
    
    /**
     * This includes all axes.
     */
    AXE,

    /**
     * This includes all pickaxes.
     */
    PICKAXE,
    
    /**
     * This includes all shovels.
     */
    SHOVEL,
        
   /**
     * This includes all hoes.
     */
    
    HOE,
    
    /**
     * This includes all fishing rods.
     */
    
    FISHING_ROD,
    
    /**
     * This includes all bows.
     */
    
    BOW,
    
    /**
     * This includes all heads.
     */
    
    HEAD,
    
    /**
     * This includes all materials that is suitable for main hand.
     */
    
    HAND_SLOT,

    /**
     * This includes all materials that is suitable for off hand.
     */
    
    OFF_HAND_SLOT;
    
    /**
     * This includes all materials that is suitable for head.
     */
    
    HEAD_SLOT;

    /**
     * This includes all materials that is suitable for chest.
     */
    
    CHEST_SLOT;
    
    /**
     * This includes all materials that is suitable for legs.
     */
    
    LEGS_SLOT;
    
    /**
     * This includes all materials that is suitable for feet.
     */
    
    FEET_SLOT;
    
    /**
     * Cached values.
     */
    private static final BumpTag[] cachedValues = values();

    /**
     * Lookup table for tag names.
     */
    private static final Map<String, BumpTag> nameLookup = new HashMap<>();

    static {
        for (BumpTag tag : cachedValues) {
            nameLookup.put(tag.name(), tag);
        }
    }

    private final NamespacedKey key;
    private final Set<Material> materials = new HashSet<>();
    private final Set<Tag<Material>> additionalTags = new HashSet<>();

    BumpTag() {
        this.key = Bump.createKey(name().toLowerCase(Locale.ROOT));
        try {
            new TagParser(this).parse((materials, additionalTags) -> {
                this.materials.clear();
                this.materials.addAll(materials);

                this.additionalTags.clear();
                this.additionalTags.addAll(additionalTags);
            });
        } catch (TagMisconfigurationException ex) {
            Bump.log(Level.SEVERE, ex, "An error has occurred while trying to load Bump tag: " + name());
        }
    }

    /**
     * Get the {@link BumpTag} with specified name.
     *
     * @param value The name of {@link BumpTag}.
     * @return The {@link BumpTag}. Null if name doesn't exist.
     */
    @Nullable
    public static BumpTag getTag(@Nonnull String value) {
        Preconditions.checkArgument(value != null, "Tag cannot be null!");

        return nameLookup.get(value);
    }

    @Nonnull
    @Override
    public NamespacedKey getKey() {
        return this.key;
    }

    @Override
    public boolean isTagged(@Nonnull Material material) {
        if (materials.contains(material)) {
            return true;
        } else {
            for (Tag<Material> tag : additionalTags) {
                if (tag.isTagged(material)) {
                    return true;
                }
            }

            return false;
        }
    }

    @Override
    public @Nonnull
    Set<Material> getValues() {
        if (additionalTags.isEmpty()) {
            return Collections.unmodifiableSet(materials);
        } else {
            Set<Material> materials = EnumSet.noneOf(Material.class);
            materials.addAll(this.materials);

            for (Tag<Material> tag : additionalTags) {
                materials.addAll(tag.getValues());
            }

            return materials;
        }
    }

    public boolean isEmpty() {
        if (!materials.isEmpty()) {
            return false;
        } else {
            return getValues().isEmpty();
        }
    }
}
