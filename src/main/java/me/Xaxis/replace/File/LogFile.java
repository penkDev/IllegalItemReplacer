package me.Xaxis.replace.File;

import lombok.SneakyThrows;
import me.Xaxis.replace.IIR;
import me.Xaxis.replace.utility.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

public class LogFile {

    private final IIR instance;
    private FileWriter fileWriter;

    public LogFile(IIR instance){
        this.instance = instance;
    }

    @SneakyThrows
    public void create(){

        if(!instance.getDataFolder().exists()){
            instance.getDataFolder().mkdirs();
        }

        File f = new File(instance.getDataFolder()+File.pathSeparator+"Data");

        if(!f.exists()){
            try{
                f.createNewFile();
            }catch (IOException e){
                instance.getServer().getLogger().log(Level.SEVERE, "FAILED TO CREATE LOG FILE! PLEASE CONTACT DEVELOPER ABOUT THIS ERROR!");
                instance.getPluginLoader().disablePlugin(instance);
            }
        }

        fileWriter = new FileWriter(f);

    }

    public FileWriter getFileWriter(){
        return fileWriter;
    }

}
