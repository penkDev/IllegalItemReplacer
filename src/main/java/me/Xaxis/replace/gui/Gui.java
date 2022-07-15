package me.Xaxis.replace.gui;

import me.Xaxis.replace.IIR;
import me.Xaxis.replace.utility.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

    public void createItemMeta(ItemStack item, String pathName, String itemName){

        if(item.getItemMeta() == null) return;
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(Utils.chat(INSTANCE.getConfig().getString("GUI."+pathName+".ITEMS."+itemName+".NAME")));
        itemMeta.setLore(INSTANCE.getConfig().getStringList("GUI."+pathName+".ITEMS."+itemName+".LORE"));
        item.setItemMeta(itemMeta);

    }



}
