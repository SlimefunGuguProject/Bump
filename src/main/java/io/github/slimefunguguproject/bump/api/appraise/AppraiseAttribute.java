package io.github.slimefunguguproject.bump.api.appraise;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.base.Preconditions;

import org.bukkit.attribute.Attribute;

import lombok.Getter;
import lombok.NonNull;

/**
 * This class represents a single {@link Attribute} with range and weight.
 *
 * @author ybw0014
 */
@Getter
public final class AppraiseAttribute {
    @NonNull
    private final Attribute attribute;
    private final double min;
    private final double max;

    private double weight = -1;

    @ParametersAreNonnullByDefault
    AppraiseAttribute(Attribute attribute, double min, double max) {
        Preconditions.checkArgument(attribute != null, "Attribute cannot be null");
        Preconditions.checkArgument(min <= max, "The minimum value cannot be larger than the maximum value");

        this.attribute = attribute;
        this.min = min;
        this.max = max;
    }

    @ParametersAreNonnullByDefault
    AppraiseAttribute(Attribute attribute, double min, double max, double weight) {
        this(attribute, min, max);

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
        if (this.weight != -1) {
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.attribute.hashCode();
        hash = 13 * hash + Double.hashCode(this.min);
        hash = 13 * hash + Double.hashCode(this.max);
        hash = 13 * hash + Double.hashCode(this.weight);
        return hash;
    }

    /**
     * Get the percentile of result value within range.
     * <p>
     * Return range from 0 to 100.
     *
     * @param value The result value.
     *
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
     *
     * @return The weighted percentile of the result value.
     */
    public double getWeightedPercentile(double value) {
        return getPercentile(value) * weight / 100.D;
    }
}
