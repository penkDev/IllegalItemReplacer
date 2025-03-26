package me.Xaxis.replace;

import java.io.IOException;

import org.bstats.bukkit.Metrics;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import me.Xaxis.replace.File.BannedItems;
import me.Xaxis.replace.File.LogFile;
import me.Xaxis.replace.Listener.onInventoryClick;
import me.Xaxis.replace.Listener.onInventoryOpen;
import me.Xaxis.replace.Manager.BannedItemManager;
import me.Xaxis.replace.commands.ReplaceCommand;
import me.Xaxis.replace.commands.SetPanicChestCommand;

public class IIR extends JavaPlugin {

    BannedItems itemsFile;
    LogFile logFile;
    BannedItemManager bannedItems;

    @Override
    public void onEnable() {

        logFile = new LogFile(this);
        itemsFile = new BannedItems(this);
        bannedItems = new BannedItemManager(itemsFile);
        try {
            itemsFile.run(this, "ItemData");
            bannedItems.loadItems();
            logFile.create();
        } catch (IOException | InvalidConfigurationException e) {
            getLogger().severe("Failed to load data files: " + e.getMessage());
            e.printStackTrace();
        }

        getCommand("replaceItem").setExecutor(new ReplaceCommand(this, itemsFile));
        getCommand("setPanicChestLocation").setExecutor(new SetPanicChestCommand(this));
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new onInventoryClick(this, bannedItems), this);
        getServer().getPluginManager().registerEvents(new onInventoryOpen(this, bannedItems, logFile), this);

        registerBStats();

    }

    public void registerBStats(){

        int superSecretCode = 15845;

        Metrics metrics = new Metrics(this, superSecretCode);

    }

}
