package org.slimefunguguproject.bump.api.appraise;

import org.bukkit.attribute.Attribute;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This class represents a single {@link Attribute} with range.
 *
 * @author ybw0014
 */
record AppraiseAttribute(Attribute attribute, double min, double max) {

    @Override
    @Nonnull
    public String toString() {
        return "Attribute = " + attribute
            + "[" + min + " - " + max + "]";
    }

    @Nonnull
    public Attribute getAttribute() {
        return attribute;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getPercent(double val) {
        if (val <= min) {
            return 0;
        } else if (val >= max) {
            return 100;
        } else {
            return (val - min) / (max - min) * 100.0D;
        }
    }
}
