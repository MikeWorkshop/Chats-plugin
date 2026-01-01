package com.mike.chats;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager {

    private static File chatsFile;
    private static FileConfiguration chatsConfig;

    public static void init() {
        chatsFile = new File(Bukkit.getPluginManager().getPlugin("Chats").getDataFolder(), "lista_chats.yml");
        if (!chatsFile.exists()) {
            try {
                chatsFile.createNewFile();
            } catch (IOException ignored) {}
        }
        chatsConfig = YamlConfiguration.loadConfiguration(chatsFile);
    }

    private static void save() {
        try { chatsConfig.save(chatsFile); } catch(Exception e) { e.printStackTrace(); }
    }

    public static void aggiungiChat(String nome, String perm) {
        chatsConfig.set(nome + ".perm", perm);
        chatsConfig.set(nome + ".prefix", "&7[" + nome.toUpperCase() + "&7]");
        save();
    }

    public static void eliminaChat(String nome) {
        chatsConfig.set(nome, null);
        save();
    }

    public static boolean contieneChat(String nome) {
        return chatsConfig.getKeys(false).stream()
                .anyMatch(key -> key.equalsIgnoreCase(nome));
    }

    public static String getPermesso(String nome) {
        return chatsConfig.getString(nome + ".perm");
    }

    public static String getPrefisso(String nome) {
        return chatsConfig.getString(nome + ".prefix");
    }

    public static List<String> getAllChats() {
        return new ArrayList<>(chatsConfig.getKeys(false));
    }

    public static List<String> getChatsConPerm(Player p) {
        return chatsConfig.getKeys(false).stream()
                .filter(chat -> p.hasPermission(getPermesso(chat)))
                .collect(Collectors.toList());
    }

    public static void setPrefix(String nome, String prefix) {
        chatsConfig.set(nome + ".prefix", prefix.trim());
        save();
    }
}
