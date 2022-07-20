package me.Xaxis.replace.commands;

import me.Xaxis.replace.IIR;
import me.Xaxis.replace.Lang;
import me.Xaxis.replace.Permission;
import me.Xaxis.replace.utility.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetPanicChestCommand implements CommandExecutor {

    private final IIR instance;

    public SetPanicChestCommand(IIR instance){
        this.instance = instance;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Utils.chat(Lang.PREFIX.getMessage(instance)+Lang.SENDER_NOT_PLAYER.getMessage(instance)));
            return true;
        }

        Player p = (Player) sender;

        if(!p.hasPermission(Permission.ADMIN.permission()) || !p.hasPermission(Permission.PANIC_CHEST_LOCATION.permission())){
            sender.sendMessage(Utils.chat(Lang.PREFIX.getMessage(instance)+Lang.NO_PERMISSION.getMessage(instance)));
            return true;
        }

        ConfigurationSection section = instance.getConfig().getConfigurationSection("EMERGENCY_STASH_LOCATION");
        if(section == null) return true;

        if(p.getLocation().getBlock().getType() != Material.CHEST) {
            p.sendMessage(Utils.chat("&4You must be standing on a chest!"));
            return true;
        }

        section.set("Location", p.getLocation());
        instance.saveConfig();

        p.sendMessage(Utils.chat("&aSuccessfully set location!"));


        return true;
    }
}
