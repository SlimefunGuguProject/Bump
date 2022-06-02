package bxx2004.bump.appraise;

import org.bukkit.attribute.Attribute;

import javax.annotation.Nonnull;

final class AppraiseAttribute {
    private final Attribute attribute;
    private final double min;
    private final double max;

    public AppraiseAttribute(@Nonnull Attribute attribute, double min, double max) {
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
}
