package com.mike.chats.AdminCmd;

import com.mike.chats.Chats;
import com.mike.chats.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AComando implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (player.hasPermission(Chats.getCF().getString("permesso_admin"))) {
                if (args.length == 2) {
                    String nomeChat = args[1];

                    if (args[0].equalsIgnoreCase("crea")) {
                        if (!FileManager.contieneChat(nomeChat)) {
                            FileManager.aggiungiChat(args[1], "chat." + args[1]);
                            player.sendMessage(ChatColor.GREEN + "Chat " + nomeChat + " creata con successo!");
                        } else {
                            player.sendMessage(ChatColor.RED + "Questa chat esiste già");
                        }

                    } else if (args[0].equalsIgnoreCase("elimina")) {
                        if (FileManager.contieneChat(nomeChat)) {
                            FileManager.eliminaChat(nomeChat);
                            player.sendMessage(ChatColor.GREEN + "Chat " + nomeChat + " eliminata con successo!");
                        } else {
                            player.sendMessage(ChatColor.RED + "Chat non trovata");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Utilizzo errato. Prova /achat crea | elimina <chat>");
                    }
                    return true;
                }

                if (args.length >= 1 && args[0].equalsIgnoreCase("setprefix")) {
                    if (args.length >= 3) {
                        String nomeChat = args[1];
                        if (!FileManager.contieneChat(nomeChat)) {
                            player.sendMessage(ChatColor.RED + "Questa chat non esiste.");
                            return true;
                        }

                        StringBuilder prefix = new StringBuilder();
                        for (int i = 2; i < args.length; i++)
                            prefix.append(args[i]).append(" ");

                        FileManager.setPrefix(nomeChat, ChatColor.translateAlternateColorCodes('&', prefix.toString()));
                        player.sendMessage(ChatColor.GREEN + "Prefisso aggiornato!");
                    } else {
                        player.sendMessage(ChatColor.RED + "Uso: /achat setprefix <chat> <nuovo prefisso>");
                    }
                    return true;
                }
                player.sendMessage(ChatColor.RED + "Utilizzo errato. Prova /achat crea | elimina <chat>");
                return true;
            } else{
                player.sendMessage(ChatColor.RED + "Permesso negato");
            }
        }

        return false;
    }
}
