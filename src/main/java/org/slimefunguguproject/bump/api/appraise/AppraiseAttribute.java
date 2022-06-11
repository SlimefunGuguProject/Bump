package org.slimefunguguproject.bump.api.appraise;

import com.google.common.base.Preconditions;
import org.bukkit.attribute.Attribute;

import javax.annotation.Nonnull;

/**
 * This class represents a single {@link Attribute} with range.
 *
 * @author ybw0014
 */
record AppraiseAttribute(@Nonnull Attribute attribute, double min, double max) {

    AppraiseAttribute {
        Preconditions.checkNotNull(attribute, "Attribute cannot be null");
    }

    @Override
    @Nonnull
    public String toString() {
        return "Attribute[" + attribute
            + ", " + min + " - " + max + "]";
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
