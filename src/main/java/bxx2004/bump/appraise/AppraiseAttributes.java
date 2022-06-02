package bxx2004.bump.appraise;

import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import org.apache.commons.lang.Validate;
import org.bukkit.attribute.Attribute;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This class hold all appraisal attributes used in appraising.
 *
 * Must add attributes first.
 *
 * @author ybw0014
 */
public final class AppraiseAttributes {
    // The set contains all attributes
    private final Set<Pair<AppraiseAttribute, Double>> attributes = new LinkedHashSet<>();
    // The set contains all attributes without percentage
    private final Set<AppraiseAttribute> noPercentAttributes = new LinkedHashSet<>();

    private boolean isLocked;
    private double usedPercentage;

    /**
     * This method adds an attribute, with percentage used to calculate overall star rate.
     *
     * @param attribute The attribute to be changed
     * @param min The minimum value of attribute
     * @param max The maximum value of attribute
     * @param percentage The percentage used to calculate overall star rate
     *
     * @return {@link AppraiseAttributes} itself
     */
    public AppraiseAttributes add(Attribute attribute, double min, double max, double percentage) {
        if (isLocked) {
            throw new IllegalStateException("No longer accept new attributes");
        }

        Validate.notNull(attribute, "attribute cannot be null");
        Validate.isTrue(min <= max, "The minimum value should less than or equal to maximum value");
        Validate.isTrue(percentage >= 0 && percentage <= 100, "The percentage should be between 0 and 100");
        Validate.isTrue(usedPercentage + percentage <= 100, "The overall percentage exceeds 100");

        AppraiseAttribute attr = new AppraiseAttribute(attribute, min, max);
        attributes.add(new Pair<>(attr, percentage));
        usedPercentage += percentage;

        return this;
    }

    /**
     * This method adds an attribute, without percentage.
     *
     * All attributes without a percentage will take average of remaining percentage.
     *
     * @param attribute The attribute to be changed
     * @param min The minimum value of attribute
     * @param max The maximum value of attribute
     *
     * @return {@link AppraiseAttributes} itself
     */
    public AppraiseAttributes add(Attribute attribute, double min, double max) {
        if (isLocked) {
            throw new IllegalStateException("No longer accept new attributes");
        }

        Validate.notNull(attribute, "attribute cannot be null");
        Validate.isTrue(min <= max, "The minimum value should less than or equal to maximum value");

        AppraiseAttribute attr = new AppraiseAttribute(attribute, min, max);
        noPercentAttributes.add(attr);

        return this;
    }

    public AppraiseAttributes build() {
        if (isLocked) {
            throw new IllegalStateException("No longer accept new attributes");
        }

        if (usedPercentage < 100 && noPercentAttributes.size() == 0) {
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

        return this;
    }
}
