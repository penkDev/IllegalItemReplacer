package me.Xaxis.replace.gui;

import me.Xaxis.replace.IIR;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Gui {

    private final IIR INSTANCE;
    private final String TITLE;
    private final int SIZE;
    private final Inventory inventory;

    public Gui(IIR instance, String Spath, int Ipath, Player player){
        this.INSTANCE = instance;
        this.TITLE = Spath;
        this.SIZE = Ipath;
        inventory = Bukkit.createInventory(player, SIZE, TITLE);
    }

    public Inventory getInventory(){
        return inventory;
    }



}
