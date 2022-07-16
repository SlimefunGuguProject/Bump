package org.slimefunguguproject.bump.api.appraise;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.base.Preconditions;

import org.bukkit.attribute.Attribute;

import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;

import net.guizhanss.guizhanlib.utils.RandomUtil;

/**
 * This class hold all appraisal attributes used in appraising.
 * <p>
 * Must add attributes first, and call build().
 *
 * @author ybw0014
 */
@SuppressWarnings("ConstantConditions")
public final class AppraiseAttributes {
    // The set contains all attributes
    private final Set<Pair<AppraiseAttribute, Double>> attributes = new LinkedHashSet<>();
    // The set contains all attributes without percentage
    private final Set<AppraiseAttribute> noPercentAttributes = new LinkedHashSet<>();

    private boolean isLocked;
    private double usedPercentage;

    /**
     * This method adds an attribute.
     *
     * @param attribute The {@link Attribute} to be changed
     * @param min       The minimum value of attribute
     * @param max       The maximum value of attribute
     * @param weight    The weight used to calculate overall star rate
     *                  (between 0 and 100, -1 means dividing remaining weight)
     * @return {@link AppraiseAttributes} itself
     */
    @ParametersAreNonnullByDefault
    public AppraiseAttributes add(Attribute attribute, double min, double max, double weight) {
        if (isLocked) {
            throw new IllegalStateException("No longer accept new attributes");
        }

        Preconditions.checkArgument(attribute != null, "Attribute cannot be null");
        Preconditions.checkArgument(min <= max, "The minimum value should less than or equal to maximum value");
        Preconditions.checkArgument(weight == -1 || (weight >= 0 && weight <= 100), "The weight should be -1 or between 0 and 100");
        if (weight != -1) {
            Preconditions.checkArgument(usedPercentage + weight <= 100, "The overall weight exceeds 100");
        }

        AppraiseAttribute attr = new AppraiseAttribute(attribute, min, max);
        if (weight == -1) {
            noPercentAttributes.add(attr);
        } else {
            attributes.add(new Pair<>(attr, weight));
            usedPercentage += weight;
        }

        return this;
    }

    /**
     * This method adds an attribute, without weight.
     *
     * @param attribute The attribute to be changed
     * @param min       The minimum value of attribute
     * @param max       The maximum value of attribute
     * @return {@link AppraiseAttributes} itself
     */
    @ParametersAreNonnullByDefault
    public AppraiseAttributes add(Attribute attribute, double min, double max) {
        return add(attribute, min, max, -1);
    }

    /**
     * This method will calculate the attributes without weight,
     * and divide the remaining overall weight.
     * <p>
     * Also, it will mark {@link AppraiseAttributes} no longer accept new attributes.
     *
     * @return {@link AppraiseAttributes} itself
     */
    public AppraiseAttributes build() {
        if (isLocked) {
            throw new IllegalStateException("No longer accept new attributes");
        }

        if (usedPercentage < 100 && noPercentAttributes.isEmpty()) {
            throw new IllegalArgumentException("Used percentage is less than 100");
        }

        isLocked = true;

        // split all attributes without percentage
        int num = noPercentAttributes.size();
        double percentage = (100 - usedPercentage) / num;
        for (AppraiseAttribute attr : noPercentAttributes) {
            attributes.add(new Pair<>(attr, percentage));
            usedPercentage += percentage;
        }
        noPercentAttributes.clear();

        return this;
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
}
