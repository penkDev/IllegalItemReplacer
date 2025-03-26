package me.Xaxis.replace.Listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import me.Xaxis.replace.GUI;
import me.Xaxis.replace.IIR;
import me.Xaxis.replace.Manager.BannedItemManager;
import me.Xaxis.replace.gui.ReplacementGui;
import me.Xaxis.replace.utility.Utils;

public class onInventoryClick implements Listener {

    private final IIR instance;
    private final BannedItemManager itemManager;

    ReplacementGui gui;

    public onInventoryClick(IIR instance, BannedItemManager itemManager) {
        this.instance = instance;
        gui = new ReplacementGui(instance);
        this.itemManager = itemManager;
    }

    @EventHandler
    public void onClick(@NotNull InventoryClickEvent e) {

        Player p = Bukkit.getPlayer(e.getWhoClicked().getUniqueId());
        if(p == null) return;

        if (e.getView().getTitle().equalsIgnoreCase(Utils.chat(GUI.EMPTY_GUI.getTitle(instance)))) {
            // If a player is placing an item, let them do so without interference
            if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            
            if(checks(e)) {
                ItemStack item = e.getCurrentItem();
                Inventory inventory = e.getView().getTopInventory(); // Use top inventory instead

                if(item == null) return;
                if(item.getItemMeta() == null) return;

                if (item.getType() == Material.PAPER && e.getSlot() == 8) { // Check if it's the paper button at slot 8
                    // Get the correct display name string from config
                    String expectedName = Utils.chat(instance.getConfig().getString(GUI.EMPTY_GUI.getPath()+".ITEMS.PAPER.NAME"));
                    
                    if (item.getItemMeta().getDisplayName().equalsIgnoreCase(expectedName)) {
                        // Debug message to show we're checking for items
                        p.sendMessage(Utils.chat("&6Checking for items in the GUI..."));
                        
                        // Collect valid items to process - now examine all slots except 8 (where button is)
                        List<ItemStack> validItems = new ArrayList<>();
                        for(int i = 0; i < inventory.getSize(); i++) {
                            if(i == 8) continue; // Skip the button slot
                            
                            ItemStack slotItem = inventory.getItem(i);
                            if(slotItem != null && slotItem.getType() != Material.AIR) {
                                validItems.add(slotItem);
                                // Debug: Show what items are found
                                p.sendMessage(Utils.chat("&7- Found: &f" + slotItem.getType().toString() + " &7in slot &f" + i));
                            }
                        }
                        
                        // Only save if we found items
                        if(!validItems.isEmpty()) {
                            itemManager.addItems(validItems.toArray(new ItemStack[0]));
                            itemManager.save();
                            
                            // Open replacement GUI for the first valid item found
                            gui.openReplacementGui(p, validItems.get(0));
                        } else {
                            p.sendMessage(Utils.chat("&cPlease place at least one item in the GUI before proceeding."));
                        }
                    }
                }
            }
        }

        if(e.getView().getTitle().equalsIgnoreCase(Utils.chat(GUI.REPLACEMENT_GUI.getTitle(instance)))) {
            if(checks(e)) {
                ItemStack item = e.getCurrentItem();
                Inventory inventory = e.getClickedInventory();

                if(item == null) return;
                if(inventory == null) return;
                if(item.getItemMeta() == null) return;

                if(item.getType() == Material.PAPER) {
                    // Get the correct display name string from config
                    String expectedName = Utils.chat(instance.getConfig().getString(GUI.REPLACEMENT_GUI.getPath()+".ITEMS.PAPER.NAME"));
                    
                    if(item.getItemMeta().getDisplayName().equalsIgnoreCase(expectedName)) {
                        ItemStack toBeReplaced = inventory.getItem(0);
                        ItemStack replacer = inventory.getItem(1);
                        if(replacer == null ) {
                            p.sendMessage(Utils.chat("&cPlease add a replacement item in the middle slot!"));
                            return;
                        }
                        if(toBeReplaced == null) {
                            p.sendMessage(Utils.chat("&cError: The item to be replaced is missing!"));
                            return;
                        }

                        itemManager.addItem(toBeReplaced, replacer);
                        p.sendMessage(Utils.chat("&aSuccessfully saved the item replacement!"));
                        p.closeInventory();
                    }
                }
            }
        }
    }

    public boolean checks(InventoryClickEvent e){
        if (e.getCurrentItem() == null) return false;
        if (e.getCurrentItem().getItemMeta() == null) return false;
        return true; // Removed isLeftClick check to make interaction easier
    }
}
