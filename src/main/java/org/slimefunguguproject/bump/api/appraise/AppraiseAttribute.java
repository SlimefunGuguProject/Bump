package org.slimefunguguproject.bump.api.appraise;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

import org.bukkit.attribute.Attribute;

/**
 * This class represents a single {@link Attribute} with range.
 *
 * @author ybw0014
 */
record AppraiseAttribute(@Nonnull Attribute attribute, double min, double max) {

    AppraiseAttribute {
        Preconditions.checkArgument(attribute != null, "Attribute cannot be null");
    }

    @Override
    @Nonnull
    public String toString() {
        return "Attribute[" + attribute
            + ", " + min + " - " + max + "]";
    }

    /**
     * Get the percentile of result value within range.
     * <p>
     * Return range from 0 to 100.
     *
     * @param value The result value.
     * @return The percentile of the result value.
     */
    public double getPercent(double value) {
        if (value <= min) {
            return 0;
        } else if (value >= max) {
            return 100;
        } else {
            return (value - min) / (max - min) * 100.0D;
        }
    }
}
