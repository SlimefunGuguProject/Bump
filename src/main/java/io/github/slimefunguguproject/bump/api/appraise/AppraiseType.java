package io.github.slimefunguguproject.bump.api.appraise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.base.Preconditions;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;

import io.github.slimefunguguproject.bump.api.exceptions.AppraiseTypeIdConflictException;
import io.github.slimefunguguproject.bump.core.BumpRegistry;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.utils.ValidateUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;

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
    private static final Pattern ID_PATTERN = Pattern.compile("^[0-9a-z_]+$");

    /**
     * This is the ID of {@link AppraiseType}.
     */
    @Getter
    private final String id;

    /**
     * This holds all added {@link AppraiseAttribute} with weight.
     */
    private final Set<Pair<AppraiseAttribute, Double>> attributes = new LinkedHashSet<>();

    /**
     * This holds all added {@link AppraiseAttribute} without weight.
     */
    private final Set<AppraiseAttribute> noPercentAttributes = new HashSet<>();

    /**
     * This holds all valid materials.
     * <p>
     * When {@link #checkMaterial()} is enabled, this will be used.
     */
    @Getter
    private final Set<Material> validMaterials = new HashSet<>();

    /**
     * This is the name of {@link AppraiseType}.
     */
    @Getter
    private String name;

    /**
     * This is the description of {@link AppraiseType}.
     */
    @Getter
    private List<String> description = new ArrayList<>();

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

    // item type
    @Getter
    private EquipmentType equipmentType = EquipmentType.ANY;
    // register state
    @Accessors(fluent = true)
    @Getter
    private boolean isRegistered;

    /**
     * Initialize {@link AppraiseType} with ID.
     *
     * @param id The ID of {@link AppraiseType}.
     */
    @ParametersAreNonnullByDefault
    public AppraiseType(String id) {
        Preconditions.checkArgument(id != null, "ID cannot be null");
        Preconditions.checkArgument(ID_PATTERN.matcher(id).matches(), "ID must be [0-9a-z_]+");

        this.id = id;
        this.name = StringUtil.humanize(id);
    }

    /**
     * Retrieve a {@link AppraiseType} by its id.
     *
     * @param id The ID of the {@link AppraiseType}.
     * @return The {@link AppraiseType} associated with that id. Null if not exist.
     */
    @Nullable
    public static AppraiseType getById(@Nonnull String id) {
        return Bump.getRegistry().getAppraiseTypeIds().get(id);
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
        checkState();
        ValidateUtils.noNullElements(description);
        this.description = Arrays.asList(description);
        return this;
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

        AppraiseAttribute attr = new AppraiseAttribute(attribute, min, max);
        if (weight == -1) {
            noPercentAttributes.add(attr);
        } else {
            attributes.add(new Pair<>(attr, weight));
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
        checkState();
        for (Material mat : materials) {
            if (mat == Material.AIR) {
                throw new IllegalArgumentException("Material cannot be AIR");
            }
        }
        validMaterials.addAll(Arrays.asList(materials));
        return this;
    }

    /**
     * This method will register this {@link AppraiseType}.
     * It will calculate the attributes without weight,
     * and divide the remaining overall weight.
     *
     * @return {@link AppraiseType} itself
     */
    public final AppraiseType register() {

        final BumpRegistry registry = Bump.getRegistry();

        // check id
        AppraiseType existing = registry.getAppraiseTypeIds().get(id);
        if (existing != null) {
            throw new AppraiseTypeIdConflictException(this, existing);
        }

        // check percentile
        if (usedPercentile < 100 && noPercentAttributes.isEmpty()) {
            throw new IllegalArgumentException("Used percentile is less than 100");
        }

        // split all attributes without weight
        int num = noPercentAttributes.size();
        double percentile = (100 - usedPercentile) / num;
        for (AppraiseAttribute attr : noPercentAttributes) {
            attributes.add(new Pair<>(attr, percentile));
            usedPercentile += percentile;
        }
        noPercentAttributes.clear();

        // registry
        registry.getAppraiseTypeIds().put(id, this);
        registry.getAppraiseTypes().add(this);

        isRegistered = true;
        return this;
    }

    /**
     * Check if this {@link AppraiseType} is registered.
     */
    private void checkState() {
        if (isRegistered()) {
            throw new IllegalStateException("This appraise type is already registered");
        }
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof AppraiseType type) {
            return type.getId().equals(getId());
        } else {
            return false;
        }
    }

    @Override
    public final int hashCode() {
        return getId().hashCode();
    }

    @Nonnull
    @Override
    public String toString() {
        return "AppraiseType[id = " + getId() + ", name = " + getName() + "]";
    }

    /**
     * This method will generate random values as appraisal result.
     *
     * @return The {@link AppraiseResult appraisal result}
     */
    @Nonnull
    public AppraiseResult appraise() {
        AppraiseResult result = new AppraiseResult();

        for (Pair<AppraiseAttribute, Double> pair : attributes) {
            AppraiseAttribute attr = pair.getFirstValue();
            double val = RandomUtil.randomDouble(attr.min(), attr.max());
            result.add(attr, val, pair.getSecondValue());
        }

        return result;
    }

    /**
     * This enum holds the valid type of appraisable items.
     */
    enum EquipmentType {
        ANY,
        SLIMEFUN,
        VANILLA
    }
}
