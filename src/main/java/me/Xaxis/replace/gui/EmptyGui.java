package me.Xaxis.replace.gui;

import me.Xaxis.replace.IIR;
import me.Xaxis.replace.utility.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EmptyGui {

    public final IIR INSTANCE;
    private String TITLE;
    private int SIZE;
    private final ItemStack NEXT_BUTTON = new ItemStack(Material.PAPER);
    Gui gui;


    public EmptyGui(IIR instance){
        this.INSTANCE = instance;
        this.TITLE = instance.getConfig().getString("GUI.PLACE_ITEMS_GUI.TITLE");
        this.SIZE = instance.getConfig().getInt("GUI.PLACE_ITEMS_GUI.SIZE");
    }

    public void openEmptyGui(Player player){

        gui = new Gui(INSTANCE, TITLE, SIZE, player);
        player.openInventory(gui.getInventory());

    }

    public void createItems(){


        ItemMeta nextButtonMeta = NEXT_BUTTON.getItemMeta();
        nextButtonMeta.setDisplayName(Utils.chat("&aNext ->"));
        NEXT_BUTTON.setItemMeta(nextButtonMeta);




    }



}
