package io.github.slimefunguguproject.bump.implementation.groups;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.bakedlibs.dough.items.CustomItemStack;
import io.github.slimefunguguproject.bump.api.appraise.AppraiseType;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;

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

    private static final int EQUIPMENT_TYPE_SLOT = 28;

    private final String name;

    @ParametersAreNonnullByDefault
    public AppraiseInfoGroup(String name, NamespacedKey key, ItemStack item) {
        super(key, item);

        this.name = name;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isVisible(Player player, PlayerProfile playerProfile, SlimefunGuideMode guideMode) {
        return true;
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

    @Nonnull
    private ItemStack getInfoGroupItem(@Nonnull AppraiseType appraiseType) {
        List<String> lore = new ArrayList<>(appraiseType.getDescription());
        lore.add("");
        lore.add(Bump.getLocalization().getString("appraise_info.click"));

        return new CustomItemStack(
            Material.PAPER,
            Bump.getLocalization().getString("appraise_info.name", appraiseType.getName()),
            lore
        );
    }

    @ParametersAreNonnullByDefault
    private void displayAppraiseType(Player player, PlayerProfile profile, SlimefunGuideMode mode, ChestMenu menu, int returnPage, AppraiseType appraiseType) {
        setupFooter(player, profile, mode, menu, 1, 1);

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


        menu.replaceExistingItem(EQUIPMENT_TYPE_SLOT, getEquipmentTypeItem(appraiseType));
        menu.addMenuClickHandler(EQUIPMENT_TYPE_SLOT, ChestMenuUtils.getEmptyClickHandler());
    }

    @Nonnull
    private ItemStack getAppraiseInfoItem(@Nonnull AppraiseType appraiseType) {
        return new CustomItemStack(
            Material.ANVIL,
            Bump.getLocalization().getString("appraise_info.name", appraiseType.getName()),
            appraiseType.getDescription()
        );
    }

    @Nonnull
    private ItemStack getEquipmentTypeItem(@Nonnull AppraiseType appraiseType) {
        String equipmentType = appraiseType.getEquipmentType().toString();
        return new CustomItemStack(
            Material.DIAMOND_SWORD,
            Bump.getLocalization().getString("appraise_info.equipment_type.name", equipmentType),
            Bump.getLocalization().getString("appraise_info.equipment_type." + equipmentType.toLowerCase(Locale.ROOT))
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
