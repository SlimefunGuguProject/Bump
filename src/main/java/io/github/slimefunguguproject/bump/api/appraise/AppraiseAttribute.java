package io.github.slimefunguguproject.bump.api.appraise;

import javax.annotation.Nonnull;

import org.bukkit.attribute.Attribute;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/**
 * This class represents a single {@link Attribute} with range and weight.
 *
 * @author ybw0014
 */
@AllArgsConstructor
@Getter
final class AppraiseAttribute {
    @NonNull
    private final Attribute attribute;
    private final double min;
    private final double max;

    private double weight;

    /**
     * Set the weight of this attribute.
     * <p>
     * Can only change when weight is not designated yet.
     *
     * @param weight The weight of this attribute.
     */
    public void setWeight(double weight) {
        if (weight != -1) {
            throw new UnsupportedOperationException("You cannot change the weight when it is set");
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
