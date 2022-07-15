package me.Xaxis.replace.gui;

import me.Xaxis.replace.GUI;
import me.Xaxis.replace.IIR;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ReplacementGui {

    public final IIR INSTANCE;
    private final ItemStack NEXT_BUTTON = new ItemStack(Material.PAPER);
    GUI emptyGUI=GUI.EMPTY_GUI;
    Gui gui;


    public ReplacementGui(IIR instance){
        this.INSTANCE = instance;
    }

    public void openReplacementGui(Player player){

        gui = new Gui(INSTANCE, emptyGUI.getTitle(INSTANCE), emptyGUI.getSize(INSTANCE), player);
        player.openInventory(gui.getInventory());
        gui.createItemMeta(NEXT_BUTTON, emptyGUI.getPathName(), Material.PAPER.toString());
        gui.getInventory().setItem(53, NEXT_BUTTON);

    }

}
