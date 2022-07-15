package me.Xaxis.replace.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class onInventoryOpen implements Listener {

    @EventHandler
    public void onInvOpen(InventoryOpenEvent e){

        Player player = (Player) e.getPlayer();




    }


}
