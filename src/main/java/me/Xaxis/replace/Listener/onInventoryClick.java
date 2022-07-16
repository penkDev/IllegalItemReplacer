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
        gui = new ReplacementGui(instance);
        this.itemManager = itemManager;

    }

    @EventHandler
    public void onClick(@NotNull InventoryClickEvent e) {

        Player p = Bukkit.getPlayer(e.getWhoClicked().getUniqueId());
        if(p == null) return;

        if (e.getView().getTitle().equalsIgnoreCase(Utils.chat(GUI.EMPTY_GUI.getTitle(instance)))) {

            if(checks(e)) {

                ItemStack item = e.getCurrentItem();
                Inventory inventory = e.getClickedInventory();

                if(item == null) return;
                if(item.getItemMeta() == null) return;

                if (item.getType() == Material.PAPER) {
                    System.out.println(GUI.EMPTY_GUI.getTitle(instance));
                    System.out.println(instance.getConfig().getString(Utils.chat(GUI.EMPTY_GUI.getPath()+".ITEMS.PAPER.NAME")));

                    if (item.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat(instance.getConfig().getString(GUI.EMPTY_GUI.getPath()+".ITEMS.PAPER.NAME")))) {

                        if(inventory == null) return;
                        itemManager.addItems(inventory.getContents());
                        itemManager.save();
                        for(ItemStack i : inventory.getContents()){
                            if(i == null) return;
                            if(i.getType() == Material.PAPER) return;
                            gui.openReplacementGui(p, i);
                        }

                    }
                }
            }
        }

        if(e.getView().getTitle().equalsIgnoreCase(Utils.chat(GUI.REPLACEMENT_GUI.getTitle(instance)))){
            System.out.println("-1");

            if(checks(e)) {

                ItemStack item = e.getCurrentItem();
                Inventory inventory = e.getClickedInventory();
                System.out.println("0");

                if(item == null) return;
                if(inventory == null) return;
                if(item.getItemMeta() == null) return;

                if(item.getType() == Material.PAPER){
                    System.out.println("1");
                    if(item.getItemMeta().getDisplayName().equalsIgnoreCase(Utils.chat(instance.getConfig().getString(GUI.REPLACEMENT_GUI.getPath()+".ITEMS.PAPER.NAME")))) {
                        System.out.println("2");
                        ItemStack toBeReplaced = inventory.getItem(0);
                        ItemStack replacer = inventory.getItem(1);
                        if(replacer == null ) return;
                        if(toBeReplaced == null) return;

                        System.out.println("3");

                        itemManager.addItem(toBeReplaced, replacer);
                        p.sendMessage(Utils.chat("&cSaved data!"));
                        p.closeInventory();


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
