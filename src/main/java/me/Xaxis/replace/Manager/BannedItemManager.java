package me.Xaxis.replace.Manager;

import me.Xaxis.replace.File.BannedItems;
import me.Xaxis.replace.IIR;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
        file.save();

        if(!itemsSection.getKeys(false).isEmpty()) {

            for (String name : itemsSection.getKeys(false)) {

                ConfigurationSection itemsConfigSection = itemsSection.getConfigurationSection(name);

                if (itemsConfigSection == null) return;

                ItemStack replace = null;
                ItemStack item = null;

                for (String sectionName : itemsConfigSection.getKeys(false)) {

                    if (sectionName.equalsIgnoreCase("replacementItem")) {

                        replace = itemsConfigSection.getItemStack("replacementItem");

                    } else {

                        item = itemsConfigSection.getItemStack(name);

                    }

                }

                if (item == null) return;
                if (replace == null) return;

                items.add(item);
                itemMap.put(item, replace);

                if (item.getItemMeta() != null) {
                    System.out.println("Loaded Item: " + item.getItemMeta().getDisplayName());
                } else {
                    System.out.println("Loaded Item: " + item.getType().name());
                }


            }
        }

    }
    public void addItem(@NotNull ItemStack i,@NotNull ItemStack replacement) {

        ConfigurationSection is = itemsSection.createSection(i.getType().name());

        is.set(i.toString(), i);

        is.set("replacementItem", replacement);

        itemMap.put(i, replacement);

        file.save();

    }
    public void addItems(ItemStack @NotNull [] item){

        items.addAll(Arrays.asList(item));

    }

    public void save(){
        file.save();
    }

    public ArrayList<ItemStack> getItems(){
        return items;
    }

    public HashMap<ItemStack, ItemStack> getItemMap(){
        return itemMap;
    }

}
