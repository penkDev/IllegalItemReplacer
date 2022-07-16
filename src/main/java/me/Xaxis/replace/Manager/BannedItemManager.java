package me.Xaxis.replace.Manager;

import me.Xaxis.replace.File.BannedItems;
import me.Xaxis.replace.IIR;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class BannedItemManager {

    private final IIR instance;
    private ArrayList<ItemStack> items;
    private HashMap<ItemStack, ItemStack> itemMap;
    ConfigurationSection itemsSection;
    BannedItems file;

    public BannedItemManager(BannedItems file, IIR instance){
        this.instance = instance;
        this.file = file;
    }

    public void loadItems(){

        if(!file.get().getKeys(false).contains("Items")) {
            itemsSection = file.get().createSection("Items");
        }else{
            itemsSection = file.get().getConfigurationSection("Items");
        }
        file.save();

        if(!itemsSection.getKeys(false).isEmpty()) {
            for (String name : itemsSection.getKeys(false)) {

                ItemStack item = itemsSection.getItemStack(name);
                ConfigurationSection itemStackSection = itemsSection.getConfigurationSection(name);
                assert itemStackSection != null;
                ItemStack replace = itemStackSection.getItemStack("replacementItem");
                items.add(item);
                itemMap.put(item, replace);
                assert item != null;
                if(item.hasItemMeta()){
                    System.out.println("Loaded Item: " + Objects.requireNonNull(item.getItemMeta()).getDisplayName());
                }else{
                    System.out.println("Loaded Item: " + item.getType().name());
                }

            }
        }

    }
    public void addItem(@NotNull ItemStack i, ItemStack replacement){

        if(i.hasItemMeta()){
            itemsSection.set(Objects.requireNonNull(i.getItemMeta()).getDisplayName(), i);
            ConfigurationSection itemStackSection = itemsSection.getConfigurationSection(i.getItemMeta().getDisplayName());
            assert itemStackSection != null;
            itemStackSection.set("replacementItem", replacement);
            itemMap.put(i, replacement);
        }else{
            itemsSection.set(i.getType().name(), i);
            ConfigurationSection itemStackSection = itemsSection.getConfigurationSection(i.getType().name());
            assert itemStackSection != null;
            itemStackSection.set("replacementItem", replacement);
            itemMap.put(i, replacement);
        }

    }
    public void addItems(ItemStack @NotNull [] item, ItemStack replacement){

        for(ItemStack i : item){

            if(i.hasItemMeta()){
                itemsSection.set(Objects.requireNonNull(i.getItemMeta()).getDisplayName(), i);
                ConfigurationSection itemStackSection = itemsSection.getConfigurationSection(i.getItemMeta().getDisplayName());
                assert itemStackSection != null;
                itemStackSection.set("replacementItem", replacement);
                itemMap.put(i, replacement);
            }else{
                itemsSection.set(i.getType().name(), i);
                ConfigurationSection itemStackSection = itemsSection.getConfigurationSection(i.getType().name());
                assert itemStackSection != null;
                itemStackSection.set("replacementItem", replacement);
                itemMap.put(i, replacement);
            }

        }

    }
    public void addItems(ItemStack @NotNull [] item){

        for(ItemStack i : item){

            if(i.hasItemMeta()){
                items.add(i);
            }else{
                items.add(i);
            }

        }

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
