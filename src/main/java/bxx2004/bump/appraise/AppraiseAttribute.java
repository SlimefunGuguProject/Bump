package bxx2004.bump.appraise;

import org.apache.commons.lang.Validate;
import org.bukkit.attribute.Attribute;

import javax.annotation.Nonnull;

/**
 * This class represents a single attribute with range.
 *
 * @author ybw0014
 */
final class AppraiseAttribute {
    private final Attribute attribute;
    private final double min;
    private final double max;

    public AppraiseAttribute(@Nonnull Attribute attribute, double min, double max) {
        Validate.notNull(attribute, "Attribute cannot be null");

        this.attribute = attribute;
        this.min = min;
        this.max = max;
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
