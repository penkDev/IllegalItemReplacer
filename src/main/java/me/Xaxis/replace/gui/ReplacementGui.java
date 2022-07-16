package me.Xaxis.replace.gui;

import me.Xaxis.replace.GUI;
import me.Xaxis.replace.IIR;
import me.Xaxis.replace.Manager.BannedItemManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class ReplacementGui {

    public final IIR INSTANCE;
    private final ItemStack SAVE_BUTTON = new ItemStack(Material.PAPER);
    GUI emptyGUI=GUI.EMPTY_GUI;
    Gui gui;
    private final BannedItemManager itemManager;


    public ReplacementGui(IIR instance, BannedItemManager itemManager){
        this.INSTANCE = instance;
        this.itemManager = itemManager;
    }

    public void openReplacementGui(Player player, ItemStack itemStack){

        gui = new Gui(INSTANCE, emptyGUI.getTitle(INSTANCE), InventoryType.ANVIL, player);
        player.openInventory(gui.getInventory());
        gui.createItemMeta(SAVE_BUTTON, emptyGUI.getPathName(), Material.PAPER.toString());
        gui.getInventory().setItem(0, itemStack);
        gui.getInventory().setItem(2,SAVE_BUTTON);

    }

}
