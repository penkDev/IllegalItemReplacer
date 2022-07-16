package me.Xaxis.replace.commands;

import me.Xaxis.replace.File.BannedItems;
import me.Xaxis.replace.IIR;
import me.Xaxis.replace.Lang;
import me.Xaxis.replace.Manager.BannedItemManager;
import me.Xaxis.replace.Permission;
import me.Xaxis.replace.gui.EmptyGui;
import me.Xaxis.replace.utility.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReplaceCommand implements CommandExecutor {

    private final IIR instance;
    private final BannedItems file;

    EmptyGui emptyGui;

    public ReplaceCommand(IIR instance, BannedItems file){
        this.instance = instance;
        emptyGui = new EmptyGui(instance);
        this.file = file;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if(!(sender instanceof Player p)){
            sender.sendMessage(Utils.chat(Lang.PREFIX.getMessage(instance)+Lang.SENDER_NOT_PLAYER.getMessage(instance)));
            return true;
        }

        if(!p.hasPermission(Permission.ADMIN.permission()) || !p.hasPermission(Permission.REPLACE_ITEMS.permission())){
            sender.sendMessage(Utils.chat(Lang.PREFIX.getMessage(instance)+Lang.NO_PERMISSION.getMessage(instance)));
            return true;
        }

        emptyGui.openEmptyGui(p);

        return true;
    }
}
