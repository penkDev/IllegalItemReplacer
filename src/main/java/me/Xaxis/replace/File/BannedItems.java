package me.Xaxis.replace.File;

import lombok.SneakyThrows;
import me.Xaxis.replace.IIR;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Objects;

public class BannedItems {

    public BannedItems(){
    }

    private File file;
    private YamlConfiguration configuration;

    /**
     *
     * @param plugin The class of the owner of the file
     * @param fileName The file name (without file extension)
     */
    @SneakyThrows
    public void run(IIR plugin, String fileName) {

        if(!Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("IllegalItemReplacer")).getDataFolder().exists()){
            Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("IllegalItemReplacer")).getDataFolder().mkdir();
        }

        File folder = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("IllegalItemReplacer")).getDataFolder(), "Data");

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

    @SneakyThrows
    public void reload(){
        this.configuration.save(file);
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    @SneakyThrows
    public void save() {
        this.configuration.save(file);

    }

}
