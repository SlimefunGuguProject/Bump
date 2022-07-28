package io.github.slimefunguguproject.bump.api.appraise;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

import org.bukkit.attribute.Attribute;

import lombok.Getter;

/**
 * This class represents a single {@link Attribute} with range and weight.
 *
 * @author ybw0014
 */
@SuppressWarnings("ConstantConditions")
@Getter
final class AppraiseAttribute {

    private final Attribute attribute;
    private final double min;
    private final double max;

    private double weight;

    /**
     * Initialize this attribute.
     *
     * @param attribute The {@link Attribute}.
     * @param min       The minimum value of attribute.
     * @param max       The maximum value of attribute.
     * @param weight    The weight of this attribute. Set to -1 for further changes.
     */
    public AppraiseAttribute(@Nonnull Attribute attribute, double min, double max, double weight) {
        Preconditions.checkArgument(attribute != null, "Attribute cannot be null");

        this.attribute = attribute;
        this.min = min;
        this.max = max;
        this.weight = weight;
    }

    /**
     * Set the weight of this attribute.
     * <p>
     * Can only change when weight is not designated yet.
     *
     * @param weight The weight of this attribute.
     */
    public void setWeight(double weight) {
        if (weight != -1) {
            throw new UnsupportedOperationException("You cannot change the weight");
        }
        this.weight = weight;
    }

    @Override
    @Nonnull
    public String toString() {
        return "AppraiseAttribute[" + attribute
            + ", " + min + " - " + max + ", " + weight + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AppraiseAttribute anotherAttribute) {
            return getAttribute() == anotherAttribute.getAttribute()
                && getMin() == anotherAttribute.getMin()
                && getMax() == anotherAttribute.getMax()
                && getWeight() == anotherAttribute.getWeight();
        } else {
            return false;
        }
    }

    /**
     * Get the percentile of result value within range.
     * <p>
     * Return range from 0 to 100.
     *
     * @param value The result value.
     * @return The percentile of the result value.
     */
    public double getPercentile(double value) {
        if (value <= min) {
            return 0;
        } else if (value >= max) {
            return 100;
        } else {
            return (value - min) / (max - min) * 100.0D;
        }
    }

    /**
     * Get the weighted percentile of result value.
     *
     * @param value The result value.
     * @return The weighted percentile of the result value.
     */
    public double getWeightedPercentile(double value) {
        return getPercentile(value) * weight / 100.D;
    }
}
