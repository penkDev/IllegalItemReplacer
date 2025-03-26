package me.Xaxis.replace.Manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import me.Xaxis.replace.File.BannedItems;

public class BannedItemManager {

    private ArrayList<ItemStack> items;
    private HashMap<ItemStack, ItemStack> itemMap;
    ConfigurationSection itemsSection;
    BannedItems file;

    public BannedItemManager(BannedItems file){
        this.file = file;
    }
    public void loadItems(){

        items = new ArrayList<>();
        itemMap = new HashMap<>();

        if(!file.get().getKeys(false).contains("Items")) {
            itemsSection = file.get().createSection("Items");
        }else{
            itemsSection = file.get().getConfigurationSection("Items");
        }
        
        try {
            file.save();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!itemsSection.getKeys(false).isEmpty()) {


            for (String name : itemsSection.getKeys(false)) {


                ConfigurationSection itemsConfigSection = itemsSection.getConfigurationSection(name);

                if (itemsConfigSection == null) return;

                ItemStack replace = null;
                ItemStack item = null;


                for (String sectionName : itemsConfigSection.getKeys(false)) {

                    if (sectionName.equalsIgnoreCase("replacementItem")) {


                        replace = itemsConfigSection.getItemStack("replacementItem");

                    } else if(sectionName.equalsIgnoreCase("toBeReplaced")){


                        item = itemsConfigSection.getItemStack("toBeReplaced");

                    }

                }

                if (item == null) return;
                if (replace == null) return;


                items.add(item);
                itemMap.put(item, replace);

                System.out.println("Loaded Item: " + name);

            }
        }

    }
    public void addItem(@NotNull ItemStack i,@NotNull ItemStack replacement) {

        ConfigurationSection is = itemsSection.createSection(i.getType().toString());

        is.set("toBeReplaced", i);

        is.set("replacementItem", replacement);

        itemMap.put(i, replacement);

        try {
            file.save();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void addItems(ItemStack @NotNull [] item){

        items.addAll(Arrays.asList(item));

    }

    public void save() {
        try {
            file.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ItemStack> getItems(){
        return items;
    }

    public HashMap<ItemStack, ItemStack> getItemMap(){
        return itemMap;
    }

}
