package me.Xaxis.replace.Listener;

import me.Xaxis.replace.IIR;
import me.Xaxis.replace.Manager.BannedItemManager;
import me.Xaxis.replace.Permission;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class onInventoryOpen implements Listener {

    private final IIR instance;
    private final BannedItemManager itemManager;

    public onInventoryOpen(IIR instance, BannedItemManager itemManager){
        this.instance = instance;
        this.itemManager = itemManager;
    }

    @EventHandler
    public void onInvOpen(InventoryOpenEvent e){

        Player player = (Player) e.getPlayer();

        if(player.hasPermission(Permission.BYPASS.permission())) return;

        ItemStack[] items = player.getInventory().getContents();

        Inventory inventory = player.getInventory();

        for(ItemStack item : items){

            inventory.all(item).entrySet().forEach(entry ->{

                for(ItemStack i : itemManager.getItems()){

                    if(item.isSimilar(i)){

                        int amt = i.getAmount();

                        ItemStack v = itemManager.getItemMap().get(i).clone();
                        v.setAmount(amt);

                        inventory.setItem(entry.getKey(), v);
                    }

                }


            });

        }

    }


}
