package bxx2004.bump.appraise;

import org.apache.commons.lang.Validate;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * An {@link AppraiseResult} represents an appraise result.
 *
 * @author ybw0014
 */
final class AppraiseResult {
    private final Map<AppraiseAttribute, Double> result = new LinkedHashMap<>();
    private double overallPercentage;

    public AppraiseResult() {
        overallPercentage = 0;
    }

    public AppraiseResult add(AppraiseAttribute attribute, double value, double overallPercentageage) {
        if (result.containsKey(attribute)) {
            return this;
        }

        result.put(attribute, value);
        overallPercentage += attribute.getPercent(value) * overallPercentageage / 100.0D;
        return this;
    }

    public int getStarts() {
        if (overallPercentage >= 100) {
            return 8;
        } else if (overallPercentage >= 98) {
            return 7;
        } else if (overallPercentage >= 95) {
            return 6;
        } else if (overallPercentage >= 91) {
            return 5;
        } else if (overallPercentage >= 85) {
            return 4;
        } else if (overallPercentage >= 69) {
            return 3;
        } else if (overallPercentage >= 48) {
            return 2;
        } else if (overallPercentage >= 25) {
            return 1;
        } else {
            return 0;
        }
    }

    public void apply(@Nonnull ItemStack itemStack) {
        Validate.notNull(itemStack, "ItemStack cannot be null");

    }
}
