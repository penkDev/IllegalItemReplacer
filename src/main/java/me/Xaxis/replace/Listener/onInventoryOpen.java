package me.Xaxis.replace.Listener;

import lombok.SneakyThrows;
import me.Xaxis.replace.GUI;
import me.Xaxis.replace.IIR;
import me.Xaxis.replace.Manager.BannedItemManager;
import me.Xaxis.replace.Permission;
import me.Xaxis.replace.utility.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class onInventoryOpen implements Listener {

    private final BannedItemManager itemManager;
    private final boolean REPLACE_ITEMS_ENABLED ;
    private final IIR instance;
    private final FileWriter fileWriter;

    public onInventoryOpen(@NotNull IIR instance, BannedItemManager itemManager, FileWriter fileWriter){
        this.itemManager = itemManager;
        REPLACE_ITEMS_ENABLED = instance.getConfig().getBoolean("ENABLED");
        this.instance = instance;
        this.fileWriter = fileWriter;

    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent e){

        if(!REPLACE_ITEMS_ENABLED) return;

        Player player = (Player) e.getPlayer();

        if(player.hasPermission(Permission.BYPASS.permission())) return;

        InventoryView inventory = player.getOpenInventory();

        List<ItemStack> items = new ArrayList<>();

        addItems(inventory, items);

        for(ItemStack item : items){

            replaceItems(inventory.getBottomInventory(), item, player);

            replaceItems(inventory.getTopInventory(), item, player);

        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(!REPLACE_ITEMS_ENABLED) return;

        Player player = (Player) e.getWhoClicked();

        if(player.hasPermission(Permission.BYPASS.permission())) return;

        InventoryView inventory = player.getOpenInventory();

        if(inventory.getTitle().equalsIgnoreCase(Utils.chat(GUI.REPLACEMENT_GUI.getTitle(instance)))
                || inventory.getTitle().equalsIgnoreCase(Utils.chat(GUI.EMPTY_GUI.getTitle(instance)))) return;

        List<ItemStack> items = new ArrayList<>();

        addItems(inventory, items);

        for(ItemStack item : items){

            replaceItems(inventory.getBottomInventory(), item, player);

            replaceItems(inventory.getTopInventory(), item, player);

        }
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

            replaceItems(inventory.getBottomInventory(), item, player);

            replaceItems(inventory.getTopInventory(), item, player);

        }

    }

    @SneakyThrows
    public void replaceItems(@NotNull Inventory inventory, ItemStack item, Player p){

        ConfigurationSection section = instance.getConfig().getConfigurationSection("EMERGENCY_STASH_LOCATION");
        if(section == null) return;
        Location PANIC_CHEST_LOCATION = section.getLocation("Location");
        Chest chest;
        if(PANIC_CHEST_LOCATION == null) return;
        if(PANIC_CHEST_LOCATION.getBlock().getType() == Material.CHEST){
            chest = (Chest) PANIC_CHEST_LOCATION.getBlock().getState();
        }else{
            Bukkit.getServer().getLogger().log(Level.SEVERE, Utils.chat("IllegalItemReplacer found no chest! Make sure you did not break it!"));
            return;
        }

        inventory.all(item).entrySet().forEach(entry ->{

            for(ItemStack i : itemManager.getItems()){

                if(item.isSimilar(i)){

                    int amt = item.getAmount();

                    chest.getBlockInventory().addItem(item);

                    ItemStack v = itemManager.getItemMap().get(i).clone();

                    v.setAmount(amt);

                    inventory.setItem(entry.getKey(), v);

                    try {
                        fileWriter.write("\nIllegalItemReplacer Log " +
                                "| Username: " + p.getDisplayName()+" " +
                                "| UUID: "+ p.getUniqueId().toString() +" " +
                                "| Item replaced: "+item.getType().toString()+" "+
                                "| Amount given: "+amt+" "+
                                "| Item Type given: "+v.getType().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }

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
