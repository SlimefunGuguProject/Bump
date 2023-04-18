package io.github.slimefunguguproject.bump.implementation.items.machines;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.collect.Multimap;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.slimefunguguproject.bump.api.appraise.AppraiseType;
import io.github.slimefunguguproject.bump.core.services.sounds.BumpSound;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.implementation.BumpItems;
import io.github.slimefunguguproject.bump.implementation.groups.BumpItemGroups;
import io.github.slimefunguguproject.bump.utils.AppraiseUtils;
import io.github.slimefunguguproject.bump.utils.GuiItems;
import io.github.slimefunguguproject.bump.utils.ValidateUtils;
import io.github.slimefunguguproject.bump.utils.constant.Keys;
import io.github.slimefunguguproject.bump.utils.tags.BumpTag;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

import net.guizhanss.guizhanlib.minecraft.utils.ChatUtil;

/**
 * The {@link AttributeGrindstone} can purge the appraisal result from
 * appraised equipment.
 *
 * @author ybw0014
 */
public final class AttributeGrindstone extends SimpleMenuBlock {

    // energy
    public static final int ENERGY_CONSUMPTION = 1314;

    public AttributeGrindstone() {
        super(BumpItemGroups.MACHINE, BumpItems.ATTRIBUTE_GRINDSTONE, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            SlimefunItems.ELECTRO_MAGNET, BumpItems.APPRAISAL, SlimefunItems.ELECTRO_MAGNET,
            BumpItems.MECHA_GEAR, BumpItems.CPU, BumpItems.MECHA_GEAR,
            BumpItems.UPDATE_POWER, BumpItems.ZONGZI, BumpItems.UPDATE_POWER
        });
    }

    @Override
    @Nonnull
    public ItemStack getOperationSlotItem() {
        return GuiItems.GRIND_BUTTON;
    }

    @Override
    public int getCapacity() {
        return ENERGY_CONSUMPTION;
    }

    @ParametersAreNonnullByDefault
    @Override
    protected void onOperate(BlockMenu menu, Block b, Player p, ClickAction action) {
        grind(menu, p);
    }

    private void grind(@Nonnull BlockMenu blockMenu, @Nonnull Player p) {
        ItemStack item = blockMenu.getItemInSlot(getInputSlot());

        // null check
        if (!ValidateUtils.noAirItem(item)) {
            Bump.getLocalization().sendMessage(p, "no-input");
            BumpSound.ATTRIBUTE_GRINDSTONE_FAIL.playFor(p);
            return;
        }

        // check if input item is appraised
        if (!AppraiseUtils.isAppraised(item)) {
            Bump.getLocalization().sendMessage(p, "machine.attribute-grindstone.invalid");
            BumpSound.ATTRIBUTE_GRINDSTONE_FAIL.playFor(p);
            return;
        }

        // check output slot
        if (blockMenu.getItemInSlot(getOutputSlot()) != null) {
            Bump.getLocalization().sendMessage(p, "output-no-space");
            BumpSound.ATTRIBUTE_GRINDSTONE_FAIL.playFor(p);
            return;
        }

        // check energy
        int charge = getCharge(blockMenu.getLocation());
        if (charge < ENERGY_CONSUMPTION) {
            Bump.getLocalization().sendMessage(p, "not-enough-power");
            BumpSound.ATTRIBUTE_GRINDSTONE_FAIL.playFor(p);
            return;
        }

        ItemStack output = item.clone();
        clearAttributes(output);
        blockMenu.replaceExistingItem(getInputSlot(), null);
        blockMenu.pushItem(output, getOutputSlot());

        setCharge(blockMenu.getLocation(), 0);
        Bump.getLocalization().sendMessage(p, "machine.attribute-grindstone.success");
        BumpSound.ATTRIBUTE_GRINDSTONE_SUCCEED.playFor(p);
    }

    private void clearAttributes(@Nonnull ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        // check the appraising version
        byte version = PersistentDataAPI.getByte(meta, Keys.APPRAISE_VERSION, (byte) 1);

        removeModifiers(itemStack, meta, version);

        // set pdc
        PersistentDataAPI.setBoolean(meta, Keys.APPRAISABLE, true);
        PersistentDataAPI.remove(meta, Keys.APPRAISE_LEVEL);

        // set lore
        String appraisedLore = ChatUtil.color(Bump.getLocalization().getString("lores.appraised"));
        String appraisedLorePrefix = appraisedLore.substring(0, appraisedLore.indexOf("{0}"));
        List<String> lore;
        if (meta.hasLore()) {
            lore = meta.getLore();
        } else {
            lore = new ArrayList<>();
        }
        for (int i = 0; i < lore.size(); i++) {
            if (lore.get(i).startsWith(appraisedLorePrefix)) {
                lore.set(i, ChatUtil.color(Bump.getLocalization().getString("lores.not-appraised")));
                break;
            }
        }
        meta.setLore(lore);

        // done
        itemStack.setItemMeta(meta);
    }

    @ParametersAreNonnullByDefault
    private void removeModifiers(ItemStack itemStack, ItemMeta meta, byte version) {
        if (version == 1) {
            // legacy version, remove all attribute modifiers
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                if (BumpTag.getTag(slot.name() + "_SLOT").isTagged(itemStack.getType())) {
                    meta.removeAttributeModifier(slot);
                }
            }
        } else {
            // new version (for now), remove all bump attribute modifiers
            Multimap<Attribute, AttributeModifier> modifierMap = meta.getAttributeModifiers();
            if (modifierMap != null) {
                for (Map.Entry<Attribute, AttributeModifier> entry : modifierMap.entries()) {
                    Attribute attribute = entry.getKey();
                    AttributeModifier modifier = entry.getValue();
                    NamespacedKey key = NamespacedKey.fromString(modifier.getName(), Bump.getInstance());
                    if (key != null && AppraiseType.getByKey(key) != null) {
                        meta.removeAttributeModifier(attribute, modifier);
                    }
                }
            }
        }
    }
}
