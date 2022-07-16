package me.Xaxis.replace.gui;

import me.Xaxis.replace.GUI;
import me.Xaxis.replace.IIR;
import me.Xaxis.replace.Manager.BannedItemManager;
import me.Xaxis.replace.utility.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EmptyGui {

    public final IIR INSTANCE;
    private final ItemStack NEXT_BUTTON = new ItemStack(Material.PAPER);
    GUI emptyGUI=GUI.EMPTY_GUI;
    Gui gui;


    public EmptyGui(IIR instance){
        this.INSTANCE = instance;
    }

    public void openEmptyGui(Player player){

        gui = new Gui(INSTANCE, Utils.chat(emptyGUI.getTitle(INSTANCE)), emptyGUI.getSize(INSTANCE), player);
        player.openInventory(gui.getInventory());
        gui.createItemMeta(NEXT_BUTTON, emptyGUI.getPathName(), Material.PAPER.toString());
        gui.getInventory().setItem(53, NEXT_BUTTON);

    }





}
