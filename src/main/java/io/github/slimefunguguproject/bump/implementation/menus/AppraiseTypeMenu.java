package io.github.slimefunguguproject.bump.implementation.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import io.github.bakedlibs.dough.items.CustomItemStack;
import io.github.slimefunguguproject.bump.api.appraise.AppraiseAttribute;
import io.github.slimefunguguproject.bump.api.appraise.AppraiseType;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.utils.AppraiseUtils;
import io.github.slimefunguguproject.bump.utils.Strings;
import io.github.slimefunguguproject.bump.utils.Utils;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * This menu displays the details of {@link AppraiseType}
 */
@RequiredArgsConstructor
public final class AppraiseTypeMenu {
    private static final int GUIDE_BACK = 0;

    private static final int INFO_SLOT = 4;

    private static final int EQUIPMENT_TYPE_SLOT = 12;
    private static final int MATERIAL_SLOT = 13;
    private static final int EQUIPMENT_SLOT_SLOT = 14;

    private static final int ATTRIBUTES_START = 27;

    @NonNull
    public final AppraiseType appraiseType;
    @NonNull
    public final Runnable backCallback;

    /**
     * Open the menu to {@link Player}.
     *
     * @param p The {@link Player} to open the menu to.
     */
    public void open(@Nonnull Player p) {
        final ChestMenu chestMenu = new ChestMenu(appraiseType.getName());
        chestMenu.setEmptySlotsClickable(false);
        setupMenu(p, chestMenu);
        chestMenu.open(p);
    }

    @ParametersAreNonnullByDefault
    private void setupMenu(Player player, ChestMenu menu) {
        // Sound
        menu.addMenuOpeningHandler(p -> p.playSound(p.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0F, 1.0F));

        // Back
        menu.addItem(GUIDE_BACK, ChestMenuUtils.getBackButton(
            player,
            "",
            ChatColor.GRAY + Slimefun.getLocalization().getMessage(player, "guide.back.guide")
        ));
        menu.addMenuClickHandler(GUIDE_BACK, (p, slot, itemStack, clickAction) -> {
            backCallback.run();
            return false;
        });

        menu.addItem(INFO_SLOT, getAppraiseInfoItem(appraiseType));
        menu.addMenuClickHandler(INFO_SLOT, ChestMenuUtils.getEmptyClickHandler());

        menu.addItem(EQUIPMENT_TYPE_SLOT, getEquipmentTypeItem(appraiseType));
        menu.addMenuClickHandler(EQUIPMENT_TYPE_SLOT, ChestMenuUtils.getEmptyClickHandler());

        menu.addItem(MATERIAL_SLOT, getMaterialItem(appraiseType));
        menu.addMenuClickHandler(MATERIAL_SLOT, ChestMenuUtils.getEmptyClickHandler());

        menu.addItem(EQUIPMENT_SLOT_SLOT, getEquipmentSlotItem(appraiseType));
        menu.addMenuClickHandler(EQUIPMENT_SLOT_SLOT, ChestMenuUtils.getEmptyClickHandler());

        int slot = ATTRIBUTES_START;
        for (AppraiseAttribute attribute : appraiseType.getAttributes()) {
            menu.addItem(slot, getAppraiseAttributeItem(attribute));
            menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());

            slot++;
            if (slot > 53) {
                break;
            }
        }
    }

    @Nonnull
    private ItemStack getAppraiseInfoItem(@Nonnull AppraiseType type) {
        return new CustomItemStack(
            Material.ANVIL,
            Bump.getLocalization().getString("appraise_info.name", type.getName()),
            AppraiseUtils.getDescriptionLore(type)
        );
    }

    @Nonnull
    private ItemStack getEquipmentTypeItem(@Nonnull AppraiseType type) {
        String equipmentType = type.getEquipmentType().toString();
        return new CustomItemStack(
            Material.DIAMOND_SWORD,
            Bump.getLocalization().getString("appraise_info.equipment_type.name", equipmentType),
            Bump.getLocalization().getString("appraise_info.equipment_type." + equipmentType.toLowerCase(Locale.ROOT))
        );
    }

    @Nonnull
    private ItemStack getMaterialItem(@Nonnull AppraiseType type) {
        if (type.checkMaterial()) {
            // Enabled checking material, display materials as well (cap at 10)
            List<String> lore = Bump.getLocalization().getStringList("appraise_info.material.lore_enabled");
            lore.add("");

            final List<Material> materials = new ArrayList<>(type.getValidMaterials());
            int size = Math.min(materials.size(), 10);
            final List<Material> subList = materials.subList(0, size);

            for (Material material : subList) {
                lore.add(ChatColor.GRAY + Utils.getMaterialName(material));
            }

            if (materials.size() > 10) {
                lore.add(Bump.getLocalization().getString("appraise_info.material.lore_enabled_more", materials.size() - size));
            }

            return new CustomItemStack(
                Material.FILLED_MAP,
                Bump.getLocalization().getString("appraise_info.material.name", Strings.CHECK),
                lore
            );
        } else {
            return new CustomItemStack(
                Material.MAP,
                Bump.getLocalization().getString("appraise_info.material.name", Strings.CROSS),
                Bump.getLocalization().getStringList("appraise_info.material.lore_disabled")
            );
        }
    }

    @Nonnull
    private ItemStack getEquipmentSlotItem(@Nonnull AppraiseType type) {
        List<String> lore = Bump.getLocalization().getStringList("appraise_info.equipment_slot.lore");
        lore.add("");

        for (EquipmentSlot slot : type.getValidEquipmentSlots()) {
            lore.add(ChatColor.GRAY + slot.toString());
        }

        return new CustomItemStack(
            Material.IRON_CHESTPLATE,
            Bump.getLocalization().getString("appraise_info.equipment_slot.name"),
            lore
        );
    }

    @Nonnull
    private ItemStack getAppraiseAttributeItem(@Nonnull AppraiseAttribute attribute) {
        return new CustomItemStack(
            Material.PAPER,
            Bump.getLocalization().getString("appraise_info.attribute.name", attribute.getAttribute().toString()),
            Bump.getLocalization().getString("appraise_info.attribute.range", attribute.getMin(), attribute.getMax()),
            Bump.getLocalization().getString("appraise_info.attribute.weight", attribute.getWeight())
        );
    }

}
