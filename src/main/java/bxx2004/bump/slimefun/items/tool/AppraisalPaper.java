package bxx2004.bump.slimefun.items.tool;

import bxx2004.bump.Bump;
import bxx2004.bump.slimefun.BumpItemGroups;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import net.guizhanss.guizhanlib.common.Scheduler;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;

public class AppraisalPaper extends SimpleSlimefunItem<ItemUseHandler> implements Listener {

    private final int[] BORDER = {5, 6, 7, 14, 16, 23, 24, 25};
    private final int POWER_SLOT = 11;
    private final int CHARGE_SLOT = 15;
    private final int INV_SIZE = 27;

    public AppraisalPaper(SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(BumpItemGroups.TOOL, item, recipeType, recipe);

        Bukkit.getPluginManager().registerEvents(this, Bump.getInstance());
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();

            // Get variables
//            final Player p = e.getPlayer();
//            final ItemStack chargerItem = e.getItem();
//            final Rechargeable charger = (Rechargeable) SlimefunItem.getByItem(chargerItem);
//
//            // Create GUI Items
//            Inventory inventory = Bukkit.createInventory(null, INV_SIZE, ChatColor.GOLD + "便携式充电器");
//
//            ItemStack backgroundItem = Utils.buildNonInteractable(Material.GRAY_STAINED_GLASS_PANE, null);
//            ItemStack borderItem = Utils.buildNonInteractable(Material.YELLOW_STAINED_GLASS_PANE, null);
//            ItemStack powerItem = Utils.buildNonInteractable(Material.GLOWSTONE, "&4电量");
//
//            // Build and open GUI
//            for (int i = 0; i < INV_SIZE; i++)
//                inventory.setItem(i, backgroundItem);
//
//            for (int slot : BORDER)
//                inventory.setItem(slot, borderItem);
//
//            inventory.setItem(POWER_SLOT, powerItem);
//            updateSlot(inventory, POWER_SLOT, "&6&l剩余电量",
//                "&e" + charger.getItemCharge(chargerItem) + "J");
//            inventory.clear(CHARGE_SLOT);
//            p.openInventory(inventory);
//
//            // Task that triggers every second
//            Scheduler.repeat(20, new BukkitRunnable() {
//                @Override
//                public void run() {
//
//                    ItemStack deviceItem = inventory.getItem(CHARGE_SLOT);
//                    SlimefunItem sfItem = SlimefunItem.getByItem(deviceItem);
//
//                    if (sfItem instanceof PortableCharger) {
//                        p.closeInventory();
//                        Utils.send(p, "&c你不能为便携式充电器充电");
//                    }
//
//                    if (sfItem instanceof Rechargeable) {
//
//                        Rechargeable device = (Rechargeable) sfItem;
//                        float neededCharge = device.getMaxItemCharge(deviceItem)
//                            - device.getItemCharge(deviceItem);
//                        float availableCharge = charger.getItemCharge(chargerItem);
//
//                        // Three different scenarios
//                        if (p.getGameMode() == GameMode.CREATIVE && neededCharge > 0) {
//                            device.setItemCharge(deviceItem, device.getMaxItemCharge(deviceItem));
//
//                        } else if (neededCharge > 0 && availableCharge > 0) {
//
//                            if (neededCharge >= CHARGE_SPEED && availableCharge >= CHARGE_SPEED) {
//                                transferCharge(charger, chargerItem, device, deviceItem, CHARGE_SPEED);
//
//                            } else {
//                                transferCharge(charger, chargerItem, device, deviceItem, Math.min(neededCharge,
//                                    availableCharge));
//                            }
//
//                        } else if (neededCharge == 0) {
//                            Utils.send(p, "&c物品电量已满!");
//
//                        } else {
//                            Utils.send(p, "&c充电器电量不足!");
//                        }
//
//                        // The name of the powerItem NEEDS to be "Portable Charger" to cancel event
//                        updateSlot(inventory, POWER_SLOT, "&6&l剩余电量",
//                            "&e" + charger.getItemCharge(chargerItem) + "J");
//                    }
//
//                    // Check if GUI is no longer open
//                    if (!inventory.getViewers().contains(p)) {
//                        cancel();
//
//                        ItemStack forgottenItem = inventory.getItem(CHARGE_SLOT);
//
//                        // Check if player left an item inside
//                        if (forgottenItem != null) {
//                            Utils.send(p, "&c嘿! 你好像忘了把东西拿出充电器了! 现在还给你...");
//                            p.getWorld().dropItemNaturally(p.getLocation(), forgottenItem);
//                        }
//                    }
//                }
//            });
        };
    }

//    @EventHandler
//    public void onChargerItemClick(InventoryClickEvent e) {
//        SlimefunItem sfItem1 = SlimefunItem.getByItem(e.getCurrentItem());
//        SlimefunItem sfItem2 = SlimefunItem.getByItem(e.getCursor());
//        if ((sfItem1 instanceof PortableCharger || sfItem2 instanceof PortableCharger)
//            && e.getWhoClicked().getOpenInventory().getTitle().contains("便携式充电器")) {
//            e.setCancelled(true);
//        }
//    }
//
//    public void updateSlot(Inventory inventory, int slot, String name, String... lore) {
//        ItemStack item = inventory.getItem(slot);
//        ItemMeta slotMeta = item.getItemMeta();
//        if (name != null) {
//            slotMeta.setDisplayName(ChatColors.color(name));
//        } else {
//            slotMeta.setDisplayName(" ");
//        }
//
//        if (lore.length > 0) {
//            List<String> lines = new ArrayList<>();
//
//            for (String line : lore) {
//                lines.add(ChatColor.translateAlternateColorCodes('&', line));
//            }
//            slotMeta.setLore(lines);
//        }
//        item.setItemMeta(slotMeta);
//        inventory.setItem(slot, item);
//    }
//
//    public void transferCharge(Rechargeable charger, ItemStack chargerItem, Rechargeable device, ItemStack deviceItem
//        , float charge) {
//        charger.removeItemCharge(chargerItem, charge);
//        device.addItemCharge(deviceItem, charge);
//    }
//
//    @Override
//    public float getMaxItemCharge(ItemStack itemStack) {
//        return CHARGE_CAPACITY;
//    }
//
//    @Override
//    public boolean isDisenchantable() {
//        return false;
//    }
//
//    @Getter
//    @AllArgsConstructor(access = AccessLevel.PRIVATE)
//    public enum Type {
//
//        SMALL(128, 8),
//        MEDIUM(512, 32),
//        BIG(1024, 64),
//        LARGE(8192, 512),
//        CARBONADO(65526, 4096);
//
//        public int chargeCapacity;
//        public int chargeSpeed;
//
//    }
}
