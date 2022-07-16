package me.Xaxis.replace.Listener;

import me.Xaxis.replace.GUI;
import me.Xaxis.replace.IIR;
import me.Xaxis.replace.Manager.BannedItemManager;
import me.Xaxis.replace.gui.ReplacementGui;
import me.Xaxis.replace.utility.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class onInventoryClick implements Listener {

    private final IIR instance;
    private final BannedItemManager itemManager;

    ReplacementGui gui;

    public onInventoryClick(IIR instance, BannedItemManager itemManager) {
        this.instance = instance;
        gui = new ReplacementGui(instance, itemManager);
        this.itemManager = itemManager;

    }

    @EventHandler
    public void onClick(@NotNull InventoryClickEvent e) {

        Player p = Bukkit.getPlayer(e.getWhoClicked().getUniqueId());

        if (e.getView().getTitle().equalsIgnoreCase(Utils.chat(GUI.EMPTY_GUI.getTitle(instance)))) {

            if(checks(e)) {

                ItemStack item = e.getCurrentItem();
                Inventory inventory = Objects.requireNonNull(e.getClickedInventory());

                assert item != null;
                if (item.getType() == Material.PAPER) {
                    System.out.println(GUI.EMPTY_GUI.getTitle(instance));
                    if (Objects.requireNonNull(item.getItemMeta()).getDisplayName().equalsIgnoreCase(Utils.chat(GUI.EMPTY_GUI.getTitle(instance)))) {

                        itemManager.addItems(inventory.getStorageContents());
                        itemManager.save();
                        for(ItemStack i : inventory.getStorageContents()){
                            gui.openReplacementGui(p, i);
                        }



                    }
                    gui.openReplacementGui(p, new ItemStack(Material.PAPER));
                }
            }
        }
        if(e.getView().getTitle().equalsIgnoreCase(Utils.chat(GUI.REPLACEMENT_GUI.getTitle(instance)))){

            if(checks(e)) {

                ItemStack item = e.getCurrentItem();
                Inventory inventory = Objects.requireNonNull(e.getClickedInventory());

                if(item.getType() == Material.PAPER){
                    if(Objects.requireNonNull(item.getItemMeta()).getDisplayName().equalsIgnoreCase(Utils.chat(GUI.REPLACEMENT_GUI.getTitle(instance)))) {

                    ItemStack toBeReplaced = inventory.getItem(0);
                    ItemStack replacer = inventory.getItem(1);

                        assert toBeReplaced != null;
                        itemManager.addItem(toBeReplaced, replacer);


                    }
                }

            }


        }

    }

    public boolean checks(InventoryClickEvent e){
        if (!e.isLeftClick()) return false;
        if (e.getCurrentItem() == null) return false;
        if (e.getCurrentItem().getItemMeta() == null) return false;

        return true;
    }

}
