package me.Xaxis.replace.Listener;

import me.Xaxis.replace.GUI;
import me.Xaxis.replace.IIR;
import me.Xaxis.replace.gui.ReplacementGui;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class onInventoryClick implements Listener {

    private final IIR instance;

    ReplacementGui gui;

    public onInventoryClick(IIR instance) {
        this.instance = instance;
        gui = new ReplacementGui(instance);

    }

    @EventHandler
    public void onClick(@NotNull InventoryClickEvent e) {

        Player p = Bukkit.getPlayer(e.getWhoClicked().getUniqueId());

        if (viewChecks(e, GUI.EMPTY_GUI)) {
            gui.openReplacementGui(p);
        }

        if(viewChecks(e,GUI.REPLACEMENT_GUI)){

        }






    }

    public boolean viewChecks(InventoryClickEvent e, GUI guiType) {
        if (e.getView().getTitle().equalsIgnoreCase(guiType.getTitle(instance))) {

            if (!e.getClick().isLeftClick()) return false;

            if (e.getCurrentItem() == null) return false;

            if (e.getCurrentItem().getItemMeta() == null) return false;

            return e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(guiType.getPath() + "ITEMS.PAPER.NAME");
        }
        return false;
    }
}
