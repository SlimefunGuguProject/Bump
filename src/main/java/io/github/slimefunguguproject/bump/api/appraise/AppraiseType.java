package io.github.slimefunguguproject.bump.api.appraise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.base.Preconditions;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.api.exceptions.AppraiseTypeIdConflictException;
import io.github.slimefunguguproject.bump.core.BumpRegistry;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.utils.ValidateUtils;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;

import net.guizhanss.guizhanlib.utils.RandomUtil;
import net.guizhanss.guizhanlib.utils.StringUtil;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * This class represents a type of appraise.
 * <p>
 * It holds all {@link AppraiseAttribute appraisal attributes} used in appraising,
 * with applicable equipment settings.
 *
 * @author ybw0014
 */
@Accessors(makeFinal = true)
@SuppressWarnings("ConstantConditions")
public class AppraiseType {
    /**
     * This is the {@link NamespacedKey} of {@link AppraiseType}.
     */
    @Getter
    private final NamespacedKey key;

    /**
     * This is the display name of {@link AppraiseType}.
     */
    @Getter
    private String name;

    /**
     * This is the description of {@link AppraiseType}.
     */
    @Getter
    private List<String> description = new ArrayList<>();

    /**
     * This holds all added {@link AppraiseAttribute}.
     */
    private Set<AppraiseAttribute> attributes = new LinkedHashSet<>();

    /**
     * This indicates the used percentile of added {@link AppraiseAttribute}.
     */
    private double usedPercentile;

    /**
     * This indicates whether check equipment material when appraising.
     */
    @Accessors(fluent = true)
    @Getter
    private boolean checkMaterial;

    /**
     * This indicates the acceptable type of equipment for appraising.
     */
    @Getter
    private EquipmentType equipmentType = EquipmentType.ANY;

    /**
     * This holds all valid {@link Material materials}.
     * <p>
     * When {@link #checkMaterial()} is enabled, the appraisal machine will check
     * the material of specified item.
     */
    @Getter
    private Set<Material> validMaterials = new HashSet<>();

    /**
     * This holds all valid acceptable {@link EquipmentSlot}.
     */
    @Getter
    private Set<EquipmentSlot> validEquipmentSlots = new HashSet<>();

    /**
     * This holds all valid Slimefun item IDs.
     */
    @Getter
    private Set<String> validSlimefunItemIds = new HashSet<>();

    /**
     * This indicates the register state of this {@link AppraiseType}.
     */
    @Accessors(fluent = true)
    @Getter
    private boolean isRegistered;

    /**
     * This indicates the {@link SlimefunAddon} that registered this {@link AppraiseType}.
     * <p>
     * Will be null if this {@link AppraiseType} has not been registered.
     */
    @Getter
    private SlimefunAddon addon;

    /**
     * Initialize {@link AppraiseType} with {@link NamespacedKey}.
     *
     * @param key The {@link NamespacedKey} of {@link AppraiseType}.
     */
    @ParametersAreNonnullByDefault
    public AppraiseType(NamespacedKey key) {
        Preconditions.checkArgument(key != null, "NamespacedKey cannot be null");

        this.key = key;
        this.name = StringUtil.humanize(key.getKey());
    }

    /**
     * Retrieve a {@link AppraiseType} by its {@link NamespacedKey}.
     *
     * @param key The {@link NamespacedKey} of the {@link AppraiseType}.
     * @return The {@link AppraiseType} associated with that {@link NamespacedKey}. {@code null} if not exist.
     */
    @Nullable
    public static AppraiseType getByKey(@Nonnull NamespacedKey key) {
        return Bump.getRegistry().getAppraiseTypeKeys().get(key);
    }

    /**
     * Set the name of this {@link AppraiseType}.
     *
     * @param name The name.
     * @return This {@link AppraiseType}.
     */
    @ParametersAreNonnullByDefault
    public final AppraiseType setName(String name) {
        checkState();
        Preconditions.checkArgument(name != null, "Name cannot be null");
        this.name = name;
        return this;
    }

    /**
     * Set the description of this {@link AppraiseType}.
     *
     * @param description The description, in array.
     * @return This {@link AppraiseType}.
     */
    @ParametersAreNonnullByDefault
    public final AppraiseType setDescription(String... description) {
        ValidateUtils.noNullElements(description);
        return setDescription(Arrays.asList(description));
    }

    /**
     * Set the description of this {@link AppraiseType}.
     *
     * @param description The description, in array.
     * @return This {@link AppraiseType}.
     */
    @ParametersAreNonnullByDefault
    public final AppraiseType setDescription(List<String> description) {
        checkState();
        ValidateUtils.noNullElements(description);
        this.description = description;
        return this;
    }

    /**
     * Set if this {@link AppraiseType} check item material when appraising.
     *
     * @param value Whether to check item material.
     * @return This {@link AppraiseType}.
     */
    public final AppraiseType checkMaterial(boolean value) {
        checkState();
        this.checkMaterial = value;
        return this;
    }

    /**
     * Set the {@link EquipmentType} of this {@link AppraiseType}.
     *
     * @param type The {@link EquipmentType}.
     * @return This {@link AppraiseType}.
     */
    @ParametersAreNonnullByDefault
    public final AppraiseType setEquipmentType(EquipmentType type) {
        checkState();
        this.equipmentType = type;
        return this;
    }

    /**
     * This method adds an {@link Attribute} as {@link AppraiseAttribute}.
     *
     * @param attribute The {@link Attribute} to be added
     * @param min       The minimum value of attribute
     * @param max       The maximum value of attribute
     * @param weight    The weight used to calculate overall star rate
     *                  (between 0 and 100, -1 means dividing remaining weight)
     * @return This {@link AppraiseType}.
     */
    @ParametersAreNonnullByDefault
    public final AppraiseType addAttribute(Attribute attribute, double min, double max, double weight) {
        checkState();

        Preconditions.checkArgument(attribute != null, "Attribute cannot be null");
        Preconditions.checkArgument(min <= max, "The minimum value cannot be larger than the maximum value");
        Preconditions.checkArgument(weight == -1 || (weight >= 0 && weight <= 100), "The weight should be -1 or between 0 and 100");
        if (weight != -1) {
            Preconditions.checkArgument(usedPercentile + weight <= 100, "The overall weight exceeds 100");
        }

        AppraiseAttribute attr = new AppraiseAttribute(attribute, min, max, weight);
        attributes.add(attr);

        if (weight != -1) {
            usedPercentile += weight;
        }

        return this;
    }

    /**
     * This method adds an {@link Attribute} as {@link AppraiseAttribute}, without weight.
     *
     * @param attribute The {@link Attribute} to be changed
     * @param min       The minimum value of attribute
     * @param max       The maximum value of attribute
     * @return This {@link AppraiseType}.
     */
    @ParametersAreNonnullByDefault
    public final AppraiseType addAttribute(Attribute attribute, double min, double max) {
        return addAttribute(attribute, min, max, -1);
    }

    /**
     * Add valid materials.
     *
     * @param materials The array of valid {@link Material}.
     * @return This {@link AppraiseType}.
     */
    @ParametersAreNonnullByDefault
    public final AppraiseType addValidMaterials(Material... materials) {
        return addValidMaterials(Arrays.asList(materials));
    }

    /**
     * Add valid materials.
     *
     * @param materials The {@link Collection} of valid {@link Material}.
     * @return This {@link AppraiseType}.
     */
    @ParametersAreNonnullByDefault
    public final AppraiseType addValidMaterials(Collection<Material> materials) {
        checkState();
        for (Material mat : materials) {
            if (mat == Material.AIR) {
                throw new IllegalArgumentException("Material cannot be AIR");
            }
        }
        validMaterials.addAll(materials);
        return this;
    }

    /**
     * Add valid Slimefun item ids.
     * <p>
     * Note that this will set the {@link EquipmentType} to SLIMEFUN.
     *
     * @param slimefunItemIds The array of valid Slimefun item id.
     * @return This {@link AppraiseType}.
     */
    @ParametersAreNonnullByDefault
    public final AppraiseType addValidSlimefunItemIds(String... slimefunItemIds) {
        ValidateUtils.noNullElements(slimefunItemIds);
        return addValidSlimefunItemIds(Arrays.asList(slimefunItemIds));
    }

    /**
     * Add valid Slimefun item ids.
     * <p>
     * Note that this will set the {@link EquipmentType} to SLIMEFUN.
     *
     * @param slimefunItemIds The {@link List} of valid Slimefun item id.
     * @return This {@link AppraiseType}.
     */
    @ParametersAreNonnullByDefault
    public final AppraiseType addValidSlimefunItemIds(List<String> slimefunItemIds) {
        checkState();
        ValidateUtils.noNullElements(slimefunItemIds);
        equipmentType = EquipmentType.SLIMEFUN;
        validSlimefunItemIds.addAll(slimefunItemIds);
        return this;
    }

    /**
     * Add valid equipment slots.
     *
     * @param equipmentSlots The {@link List} of valid equipment slots.
     * @return This {@link AppraiseType}.
     */
    @ParametersAreNonnullByDefault
    public final AppraiseType addValidEquipmentSlots(EquipmentSlot... equipmentSlots) {
        ValidateUtils.noNullElements(equipmentSlots);
        return addValidEquipmentSlots(Arrays.asList(equipmentSlots));
    }

    /**
     * Add valid equipment slots.
     *
     * @param equipmentSlots The {@link List} of valid equipment slots.
     * @return This {@link AppraiseType}.
     */
    @ParametersAreNonnullByDefault
    public final AppraiseType addValidEquipmentSlots(List<EquipmentSlot> equipmentSlots) {
        checkState();
        ValidateUtils.noNullElements(equipmentSlots);
        validEquipmentSlots.addAll(equipmentSlots);
        return this;
    }

    /**
     * This method will register this {@link AppraiseType}.
     * It will calculate the attributes without weight,
     * and divide the remaining overall weight.
     *
     * @return {@link AppraiseType} itself
     */
    public final AppraiseType register(@Nonnull SlimefunAddon addon) {
        Preconditions.checkArgument(addon != null, "Addon cannot be null");

        final BumpRegistry registry = Bump.getRegistry();

        // check id
        AppraiseType existing = registry.getAppraiseTypeKeys().get(key);
        if (existing != null) {
            throw new AppraiseTypeIdConflictException(this, existing);
        }

        final Set<AppraiseAttribute> noWeightAttributes = attributes.stream()
            .filter(appraiseAttribute -> appraiseAttribute.getWeight() == -1)
            .collect(Collectors.toSet());

        // check percentile
        if (usedPercentile < 100 && noWeightAttributes.isEmpty()) {
            throw new IllegalArgumentException("Used percentile is less than 100");
        }

        // split remaining weight
        int num = noWeightAttributes.size();
        double percentile = (100 - usedPercentile) / num;
        for (AppraiseAttribute attr : noWeightAttributes) {
            attr.setWeight(percentile);
            usedPercentile += percentile;
        }

        // registry
        registry.getAppraiseTypeKeys().put(key, this);
        registry.getAppraiseTypes().add(this);

        // unmodifiable
        attributes = Set.copyOf(attributes);
        validMaterials = Set.copyOf(validMaterials);
        validEquipmentSlots = Set.copyOf(validEquipmentSlots);
        validSlimefunItemIds = Set.copyOf(validSlimefunItemIds);

        isRegistered = true;
        this.addon = addon;
        return this;
    }

    /**
     * Check if this {@link AppraiseType} is registered.
     */
    protected final void checkState() {
        if (isRegistered()) {
            throw new IllegalStateException("This appraise type is already registered");
        }
    }

    /**
     * This method checks if specified {@link ItemStack} fit this {@link AppraiseType}.
     *
     * @param itemStack The {@link ItemStack} to be checked.
     * @return If the {@link ItemStack} fit this {@link AppraiseType}.
     */
    public boolean isValidItem(@Nonnull ItemStack itemStack) {
        // Material check
        if (checkMaterial() && !validMaterials.contains(itemStack.getType())) {
            return false;
        }

        // Equipment type check
        Optional<SlimefunItem> sfItem = Optional.ofNullable(SlimefunItem.getByItem(itemStack));
        switch (getEquipmentType()) {
            case VANILLA -> {
                if (sfItem.isPresent()) {
                    return false;
                }
            }
            case SLIMEFUN -> {
                if (sfItem.isEmpty()) {
                    return false;
                }
                // Valid slimefun item
                if (!isApplicableSlimefunItem(sfItem.get())) {
                    return false;
                }
            }
        }
        // if passes check then return true
        return true;
    }

    /**
     * This is the default method to check if a {@link SlimefunItem} fit this {@link AppraiseType}.
     *
     * @param sfItem The {@link SlimefunItem} to be checked.
     * @return If the {@link SlimefunItem} fit this {@link AppraiseType}.
     */
    public boolean isApplicableSlimefunItem(@Nonnull SlimefunItem sfItem) {
        String id = sfItem.getId();
        return validSlimefunItemIds.isEmpty() || validSlimefunItemIds.contains(id);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof AppraiseType other) {
            return this.getKey().equals(other.getKey());
        } else {
            return false;
        }
    }

    @Override
    public final int hashCode() {
        return getKey().hashCode();
    }

    @Nonnull
    @Override
    public String toString() {
        return "AppraiseType[" + getKey() + "]";
    }

    /**
     * This method will generate random values as {@link AppraiseResult}.
     *
     * @return The {@link AppraiseResult}.
     */
    @Nonnull
    public AppraiseResult appraise() {
        AppraiseResult.Builder builder = new AppraiseResult.Builder(this);

        for (AppraiseAttribute attr : attributes) {
            double value = RandomUtil.randomDouble(attr.getMin(), attr.getMax());
            builder.add(attr, value);
        }

        return builder.build();
    }

    /**
     * This enum holds the acceptable type of appraisable items.
     */
    enum EquipmentType {
        ANY,
        SLIMEFUN,
        VANILLA
    }
}
