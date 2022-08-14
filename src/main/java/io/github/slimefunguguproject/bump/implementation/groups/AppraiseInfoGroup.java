package io.github.slimefunguguproject.bump.implementation.groups;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import io.github.bakedlibs.dough.items.CustomItemStack;
import io.github.slimefunguguproject.bump.api.appraise.AppraiseAttribute;
import io.github.slimefunguguproject.bump.api.appraise.AppraiseType;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.slimefunguguproject.bump.utils.Strings;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;

import net.guizhanss.guizhanlib.minecraft.helper.MaterialHelper;
import net.guizhanss.guizhanlib.minecraft.utils.ChatUtil;
import net.guizhanss.guizhanlib.utils.StringUtil;

/**
 * A flex item group that displays apprase types.
 *
 * @author ybw0014
 */
public final class AppraiseInfoGroup extends FlexItemGroup {

    private static final int PAGE_SIZE = 36;

    private static final int GUIDE_BACK = 1;

    private static final int PAGE_PREVIOUS = 46;
    private static final int PAGE_NEXT = 52;

    private static final int[] HEADER = new int[]{
        0, 1, 2, 3, 4, 5, 6, 7, 8
    };
    private static final int[] FOOTER = new int[]{
        45, 46, 47, 48, 49, 50, 51, 52, 53
    };

    private static final int INFO_SLOT = 13;

    private static final int EQUIPMENT_TYPE_SLOT = 19;
    private static final int MATERIAL_SLOT = 20;
    private static final int EQUIPMENT_SLOT_SLOT = 21;

    private static final int ATTRIBUTE_LEFT_BORDER = 27;
    private static final int ATTRIBUTE_RIGHT_BORDER = 35;

    private final String name;

    @ParametersAreNonnullByDefault
    public AppraiseInfoGroup(String name, NamespacedKey key, ItemStack item) {
        super(key, item);

        this.name = name;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isVisible(Player player, PlayerProfile playerProfile, SlimefunGuideMode guideMode) {
        return false;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void open(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        final ChestMenu chestMenu = new ChestMenu(name);

        for (int slot : HEADER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }
        for (int slot : FOOTER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }

        chestMenu.setEmptySlotsClickable(false);
        displayCollection(p, profile, mode, chestMenu, 1);
        chestMenu.open(p);
    }

    @ParametersAreNonnullByDefault
    private void displayCollection(Player player, PlayerProfile profile, SlimefunGuideMode mode, ChestMenu menu, int page) {
        final List<AppraiseType> appraiseTypes = new ArrayList<>(Bump.getRegistry().getAppraiseTypes());
        final int total = appraiseTypes.size();
        final int totalPages = (int) Math.ceil(total / (double) PAGE_SIZE);
        final int start = (page - 1) * PAGE_SIZE;
        final int end = Math.min(start + PAGE_SIZE, total);

        final List<AppraiseType> subList = appraiseTypes.subList(start, end);

        setupFooter(player, profile, mode, menu, page, totalPages);

        // Sound
        player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0F, 1.0F);

        // Back
        menu.replaceExistingItem(GUIDE_BACK, ChestMenuUtils.getBackButton(
            player,
            "",
            ChatColor.GRAY + Slimefun.getLocalization().getMessage(player, "guide.back.guide")
        ));
        menu.addMenuClickHandler(GUIDE_BACK, (p, slot, itemStack, clickAction) -> {
            SlimefunGuide.openItemGroup(profile, BumpItemGroups.MAIN, mode, 1);
            return false;
        });

        for (int i = 0; i < PAGE_SIZE; i++) {
            final int slot = i + 9;

            if (i + 1 <= subList.size()) {
                final AppraiseType appraiseType = subList.get(i);
                menu.replaceExistingItem(slot, getInfoGroupItem(appraiseType));
                menu.addMenuClickHandler(slot, (p, slot1, itemStack, clickAction) -> {
                    displayAppraiseType(p, profile, mode, menu, page, appraiseType);
                    return false;
                });
            } else {
                menu.replaceExistingItem(slot, null);
                menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
            }
        }
    }

    @ParametersAreNonnullByDefault
    private void displayAppraiseType(Player player, PlayerProfile profile, SlimefunGuideMode mode, ChestMenu menu, int returnPage, AppraiseType appraiseType) {
        setupFooter(player, profile, mode, menu, 1, 1);

        clearDisplay(menu);

        // Sound
        player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0F, 1.0F);

        // Back
        menu.replaceExistingItem(GUIDE_BACK, ChestMenuUtils.getBackButton(
            player,
            "",
            ChatColor.GRAY + Slimefun.getLocalization().getMessage(player, "guide.back.guide")
        ));
        menu.addMenuClickHandler(GUIDE_BACK, (p, slot, itemStack, clickAction) -> {
            SlimefunGuide.openItemGroup(profile, this, mode, returnPage);
            return false;
        });

        menu.replaceExistingItem(INFO_SLOT, getAppraiseInfoItem(appraiseType));
        menu.addMenuClickHandler(INFO_SLOT, ChestMenuUtils.getEmptyClickHandler());

        menu.replaceExistingItem(EQUIPMENT_TYPE_SLOT, getEquipmentTypeItem(appraiseType));
        menu.addMenuClickHandler(EQUIPMENT_TYPE_SLOT, ChestMenuUtils.getEmptyClickHandler());

        menu.replaceExistingItem(MATERIAL_SLOT, getMaterialItem(appraiseType));
        menu.addMenuClickHandler(MATERIAL_SLOT, ChestMenuUtils.getEmptyClickHandler());

        menu.replaceExistingItem(EQUIPMENT_SLOT_SLOT, getEquipmentSlotItem(appraiseType));
        menu.addMenuClickHandler(EQUIPMENT_SLOT_SLOT, ChestMenuUtils.getEmptyClickHandler());

        displayAppraiseTypeAttributes(menu, appraiseType);
    }

    private void clearDisplay(@Nonnull ChestMenu menu) {
        for (int i = 0; i < PAGE_SIZE; i++) {
            final int slot = i + 9;
            menu.replaceExistingItem(slot, null);
            menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
        }
    }

    @Nonnull
    private List<String> getDescriptionLore(@Nonnull AppraiseType type) {
        List<String> lore = type.getDescription();
        return lore.stream()
            .map(line -> ChatUtil.color(ChatColor.GRAY + line))
            .collect(Collectors.toList());
    }

    @Nonnull
    private ItemStack getInfoGroupItem(@Nonnull AppraiseType type) {
        List<String> lore = getDescriptionLore(type);
        lore.add("");
        lore.add(Bump.getLocalization().getString("appraise_info.click"));

        return new CustomItemStack(
            Material.PAPER,
            Bump.getLocalization().getString("appraise_info.name", type.getName()),
            lore
        );
    }

    @Nonnull
    private ItemStack getAppraiseInfoItem(@Nonnull AppraiseType type) {
        return new CustomItemStack(
            Material.ANVIL,
            Bump.getLocalization().getString("appraise_info.name", type.getName()),
            getDescriptionLore(type)
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
                if (Bump.getRegistry().getLanguage().equalsIgnoreCase("zh-CN")) {
                    lore.add(ChatUtil.color(MaterialHelper.getName(material)));
                } else {
                    lore.add(ChatUtil.color(StringUtil.humanize(material.toString())));
                }
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

        for (EquipmentSlot slot : type.getValidEquipmentSlots()) {
            lore.add(ChatUtil.color(slot.toString()));
        }

        return new CustomItemStack(
            Material.IRON_CHESTPLATE,
            Bump.getLocalization().getString("appraise_info.equipment_slot.name"),
            lore
        );
    }

    @ParametersAreNonnullByDefault
    private void displayAppraiseTypeAttributes(ChestMenu menu, AppraiseType type) {
        int slot = ATTRIBUTE_LEFT_BORDER;
        for (AppraiseAttribute attribute : type.getAttributes()) {
            menu.replaceExistingItem(slot, getAppraiseAttributeItem(attribute));
            menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());

            // If the slot exceeds the right border, ignore remaining
            slot++;
            if (slot > ATTRIBUTE_RIGHT_BORDER) {
                break;
            }
        }
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

    @ParametersAreNonnullByDefault
    private void setupFooter(Player player, PlayerProfile profile, SlimefunGuideMode mode, ChestMenu menu, int page, int totalPages) {
        for (int slot : FOOTER) {
            menu.replaceExistingItem(slot, ChestMenuUtils.getBackground());
            menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
        }

        menu.replaceExistingItem(PAGE_PREVIOUS, ChestMenuUtils.getPreviousButton(player, page, totalPages));
        menu.addMenuClickHandler(PAGE_PREVIOUS, (p, slot, itemStack, clickAction) -> {
            final int previousPage = page - 1;
            if (previousPage >= 1) {
                displayCollection(p, profile, mode, menu, previousPage);
            }
            return false;
        });

        menu.replaceExistingItem(PAGE_NEXT, ChestMenuUtils.getNextButton(player, page, totalPages));
        menu.addMenuClickHandler(PAGE_NEXT, (p, slot, itemStack, clickAction) -> {
            final int nextPage = page + 1;
            if (nextPage <= totalPages) {
                displayCollection(p, profile, mode, menu, nextPage);
            }
            return false;
        });
    }
}
