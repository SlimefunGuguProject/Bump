package io.github.slimefunguguproject.bump.implementation.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.base.Preconditions;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.slimefunguguproject.bump.api.appraise.AppraiseType;
import io.github.slimefunguguproject.bump.implementation.Bump;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * This class will open a menu with all {@link AppraiseType}.
 *
 * @author ybw0014
 */
@SuppressWarnings("ConstantConditions")
@RequiredArgsConstructor
public abstract class AppraiseTypesMenu {
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

    /**
     * The display name of menu.
     */
    @NonNull
    private final String name;

    /**
     * The callback is called after selecting an {@link AppraiseType}.
     */
    @NonNull
    private final Consumer<AppraiseType> successCallback;

    /**
     * The callback is called after clicking on back button.
     */
    @NonNull
    private final Runnable backCallback;

    /**
     * Open the menu to {@link Player}.
     *
     * @param p The {@link Player} to open the menu to.
     */
    public void open(@Nonnull Player p) {
        Preconditions.checkArgument(p != null, "Player cannot be null");

        final ChestMenu chestMenu = new ChestMenu(name);

        for (int slot : HEADER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }
        for (int slot : FOOTER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }

        chestMenu.setEmptySlotsClickable(false);
        displayCollection(p, chestMenu, 1);
        chestMenu.open(p);
    }

    @ParametersAreNonnullByDefault
    private void displayCollection(Player player, ChestMenu menu, int page) {
        final List<AppraiseType> appraiseTypes = new ArrayList<>(Bump.getRegistry().getAppraiseTypes());
        final int total = appraiseTypes.size();
        final int totalPages = (int) Math.ceil(total / (double) PAGE_SIZE);
        final int start = (page - 1) * PAGE_SIZE;
        final int end = Math.min(start + PAGE_SIZE, total);

        final List<AppraiseType> subList = appraiseTypes.subList(start, end);

        setupFooter(player, menu, page, totalPages);

        // Sound
        player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0F, 1.0F);

        // Back
        menu.replaceExistingItem(GUIDE_BACK, ChestMenuUtils.getBackButton(
            player,
            "",
            ChatColor.GRAY + Slimefun.getLocalization().getMessage(player, "guide.back.guide")
        ));
        menu.addMenuClickHandler(GUIDE_BACK, (p, slot, itemStack, clickAction) -> {
            backCallback.run();
            return false;
        });

        for (int i = 0; i < PAGE_SIZE; i++) {
            final int slot = i + 9;

            if (i + 1 <= subList.size()) {
                final AppraiseType appraiseType = subList.get(i);
                menu.replaceExistingItem(slot, getDisplayItem(appraiseType));
                menu.addMenuClickHandler(slot, (p, slot1, itemStack, clickAction) -> {
                    successCallback.accept(appraiseType);
                    return false;
                });
            } else {
                menu.replaceExistingItem(slot, null);
                menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
            }
        }
    }

    @Nonnull
    public abstract ItemStack getDisplayItem(@Nonnull AppraiseType type);

    @ParametersAreNonnullByDefault
    private void setupFooter(Player player, ChestMenu menu, int page, int totalPages) {
        for (int slot : FOOTER) {
            menu.replaceExistingItem(slot, ChestMenuUtils.getBackground());
            menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
        }

        menu.replaceExistingItem(PAGE_PREVIOUS, ChestMenuUtils.getPreviousButton(player, page, totalPages));
        menu.addMenuClickHandler(PAGE_PREVIOUS, (p, slot, itemStack, clickAction) -> {
            final int previousPage = page - 1;
            if (previousPage >= 1) {
                displayCollection(p, menu, previousPage);
            }
            return false;
        });

        menu.replaceExistingItem(PAGE_NEXT, ChestMenuUtils.getNextButton(player, page, totalPages));
        menu.addMenuClickHandler(PAGE_NEXT, (p, slot, itemStack, clickAction) -> {
            final int nextPage = page + 1;
            if (nextPage <= totalPages) {
                displayCollection(p, menu, nextPage);
            }
            return false;
        });
    }
}
