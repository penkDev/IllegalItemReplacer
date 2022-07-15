package me.Xaxis.replace.gui;

import me.Xaxis.replace.IIR;
import me.Xaxis.replace.utility.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EmptyGui {

    private final IIR INSTANCE;
    private String TITLE;
    private int SIZE;
    private final ItemStack NEXT_BUTTON = new ItemStack(Material.PAPER);


    public EmptyGui(IIR instance){
        this.INSTANCE = instance;
        this.TITLE = instance.getConfig().getString("GUI.PLACE_ITEMS_GUI.TITLE");
        this.SIZE = instance.getConfig().getInt("GUI.PLACE_ITEMS_GUI.SIZE");
    }

    public void openEmptyGui(Player player){

        Inventory inventory = Bukkit.createInventory(player, 54, Utils.chat(TITLE));
        player.openInventory(inventory);

    }

    public void createItems(){


        ItemMeta nextButtonMeta = NEXT_BUTTON.getItemMeta();
        nextButtonMeta.setDisplayName(Utils.chat("&aNext ->"));
        NEXT_BUTTON.setItemMeta(nextButtonMeta);




    }



}
