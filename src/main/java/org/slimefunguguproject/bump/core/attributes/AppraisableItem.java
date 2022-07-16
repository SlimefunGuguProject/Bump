package org.slimefunguguproject.bump.core.attributes;

import io.github.thebusybiscuit.slimefun4.core.attributes.ItemAttribute;

import org.slimefunguguproject.bump.implementation.items.machines.AppraisalInstrument;

/**
 * This {@link ItemAttribute} indicates that this item has cooldown time.
 * <p>
 * Implement this interface if the item can be appraised in {@link AppraisalInstrument} directly.
 *
 * @author ybw0014
 */
public interface AppraisableItem extends ItemAttribute {
}
