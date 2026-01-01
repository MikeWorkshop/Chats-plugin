package com.mike.chats;

import com.mike.chats.AdminCmd.AComando;
import com.mike.chats.AdminCmd.ACompleter;
import com.mike.chats.PlayerCmd.PComando;
import com.mike.chats.PlayerCmd.PCompleter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Chats extends JavaPlugin {

    private static FileConfiguration config;
    public static FileConfiguration getCF(){return config;}

    @Override
    public void onEnable() {
        if (this.getConfig().getString("permesso_admin") == null){
            getLogger().info("permesso nella config non inserito. il plugin non può avviarsi");
            return;
        }

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("chat").setExecutor(new PComando());
        getCommand("chat").setTabCompleter(new PCompleter());
        getCommand("achat").setExecutor(new AComando());
        getCommand("achat").setTabCompleter(new ACompleter());
        Bukkit.getPluginManager().registerEvents(new Events(), this);

        config = this.getConfig();

        FileManager.init();
        getLogger().info(" ");
        getLogger().info("Chats.jar avviato correttamente");
        getLogger().info(" ");
    }
}
