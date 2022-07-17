package me.Xaxis.replace.Listener;

import me.Xaxis.replace.IIR;
import me.Xaxis.replace.Manager.BannedItemManager;
import me.Xaxis.replace.Permission;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class onInventoryOpen implements Listener {

    private final BannedItemManager itemManager;
    private final boolean REPLACE_ITEMS_ENABLED ;
    private final Location PANIC_CHEST_LOCATION;

    public onInventoryOpen(@NotNull IIR instance, BannedItemManager itemManager){
        this.itemManager = itemManager;
        REPLACE_ITEMS_ENABLED = instance.getConfig().getBoolean("ENABLED");
        ConfigurationSection section = instance.getConfig().getConfigurationSection("EMERGENCY_STASH_LOCATION");
        assert section != null;
        PANIC_CHEST_LOCATION = section.getLocation("Location");
    }

    @EventHandler
    public void onInvOpen(InventoryOpenEvent e){

        if(!REPLACE_ITEMS_ENABLED) return;

        Player player = (Player) e.getPlayer();

        if(player.hasPermission(Permission.BYPASS.permission())) return;

        InventoryView inventory = player.getOpenInventory();

        List<ItemStack> items = new ArrayList<>();

        addItems(inventory, items);

        for(ItemStack item : items){

            replaceItems(inventory.getBottomInventory(), item);

            replaceItems(inventory.getTopInventory(), item);

        }

    }

    public void replaceItems(@NotNull Inventory inventory, ItemStack item){

        Chest chest = (Chest) PANIC_CHEST_LOCATION.getBlock();

        inventory.all(item).entrySet().forEach(entry ->{

            for(ItemStack i : itemManager.getItems()){

                if(item.isSimilar(i)){

                    int amt = item.getAmount();

                    chest.getBlockInventory().addItem(item);

                    ItemStack v = itemManager.getItemMap().get(i).clone();
                    v.setAmount(amt);

                    inventory.setItem(entry.getKey(), v);
                }

            }


        });
    }

    public void addItems(@NotNull InventoryView inventoryView, List<ItemStack> items){

        Inventory topInv = inventoryView.getTopInventory();
        for(ItemStack item : topInv){
            items.add(item);
        }

        Inventory botInv = inventoryView.getBottomInventory();
        for(ItemStack item : botInv){
            items.add(item);
        }


    }


}
