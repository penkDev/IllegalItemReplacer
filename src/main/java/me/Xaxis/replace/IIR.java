package me.Xaxis.replace;

import me.Xaxis.replace.commands.ReplaceCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class IIR extends JavaPlugin {

    private static IIR IIR;

    public static IIR getInstance() {
        return IIR;
    }

    @Override
    public void onEnable() {

        getCommand("replaceItem").setExecutor(new ReplaceCommand(this));
        saveDefaultConfig();

    }
}
