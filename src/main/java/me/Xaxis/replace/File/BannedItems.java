package me.Xaxis.replace.File;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import me.Xaxis.replace.IIR;

public class BannedItems {

    IIR instance;

    public BannedItems(IIR instance){
        this.instance = instance;
    }

    private File file;
    private YamlConfiguration configuration;

    /**
     *
     * @param plugin The class of the owner of the file
     * @param fileName The file name (without file extension)
     */
    public void run(IIR plugin, String fileName) throws IOException, InvalidConfigurationException {

        if(!instance.getDataFolder().exists()){
            instance.getDataFolder().mkdir();
        }

        File folder = new File(instance.getDataFolder(), "Data");

        if(!folder.exists()) folder.mkdirs();

        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        file = new File(folder, fileName + ".yml");
        configuration = new YamlConfiguration();

        if(!file.exists()) {
            file.createNewFile();
        }

        this.configuration.load(file);
    }

    public YamlConfiguration get() {
        return configuration;
    }

    public void reload() throws IOException {
        this.configuration.save(file);
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public void save() throws IOException {
        this.configuration.save(file);
    }

}
