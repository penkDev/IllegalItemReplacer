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

            System.out.println("1");

            for (String name : itemsSection.getKeys(false)) {

                System.out.println("2");
                ConfigurationSection itemsConfigSection = itemsSection.getConfigurationSection(name);

                if (itemsConfigSection == null) return;

                ItemStack replace = null;
                ItemStack item = null;

                System.out.println("3");

                for (String sectionName : itemsConfigSection.getKeys(false)) {

                    if (sectionName.equalsIgnoreCase("replacementItem")) {
                        System.out.println("4");

                        replace = itemsConfigSection.getItemStack("replacementItem");

                    } else if(sectionName.equalsIgnoreCase("toBeReplaced")){
                        System.out.println("5");

                        item = itemsConfigSection.getItemStack("toBeReplaced");

                    }

                }

                if (item == null) return;
                if (replace == null) return;
                System.out.println("6");

                items.add(item);
                itemMap.put(item, replace);

                if (item.getItemMeta() != null && item.hasItemMeta()) {
                    System.out.println("Loaded Item: " + item.getItemMeta().getDisplayName());
                } else {
                    System.out.println("Loaded Item: " + item.getType().name());
                }


            }
        }

    }
    public void addItem(@NotNull ItemStack i,@NotNull ItemStack replacement) {

        ConfigurationSection is = itemsSection.createSection(i.getType().name());

        is.set("toBeReplaced", i);

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
