package me.Xaxis.replace.gui;

import me.Xaxis.replace.GUI;
import me.Xaxis.replace.IIR;
import me.Xaxis.replace.utility.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class ReplacementGui {

    public final IIR INSTANCE;
    private final ItemStack SAVE_BUTTON = new ItemStack(Material.PAPER);
    GUI emptyGUI = GUI.REPLACEMENT_GUI;
    Gui gui;


    public ReplacementGui(IIR instance){
        this.INSTANCE = instance;
    }

    public void openReplacementGui(Player player, ItemStack itemStack){

        gui = new Gui(INSTANCE, Utils.chat(emptyGUI.getTitle(INSTANCE)), InventoryType.ANVIL, player);
        player.openInventory(gui.getInventory());
        gui.createItemMeta(SAVE_BUTTON, emptyGUI.getPathName(), Material.PAPER.name());
        gui.getInventory().setItem(0, itemStack);
        gui.getInventory().setItem(2,SAVE_BUTTON);

    }

}
