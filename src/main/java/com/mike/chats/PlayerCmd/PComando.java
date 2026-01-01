package com.mike.chats.PlayerCmd;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mike.chats.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PComando implements CommandExecutor {

    private static final int COOLDOWN_SECONDS = 2;
    private Cache<UUID, Long> cooldown = CacheBuilder.newBuilder().expireAfterWrite(COOLDOWN_SECONDS, TimeUnit.SECONDS).build();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length >= 2) {
                //cooldown
                if (cooldown.asMap().containsKey(p.getUniqueId())) {
                    long tempoRimanente = cooldown.asMap().get(p.getUniqueId()) - System.currentTimeMillis();
                    p.sendMessage(ChatColor.RED + "Aspetta ancora " +
                            (TimeUnit.MILLISECONDS.toSeconds(tempoRimanente) + 1) +
                            " secondi prima di eseguire di nuovo il comando");
                    return true;
                }

                String nomeChat = args[0];
                //ricerca della chat nel chats.yml
                if (FileManager.contieneChat(nomeChat)) {
                    //controllo del permesso
                    String perm = FileManager.getPermesso(nomeChat);
                    if (p.hasPermission(perm)) {
                        StringBuilder mess = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            mess.append(args[i] + " ");
                        }

                        String prefix = FileManager.getPrefisso(nomeChat);
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player.hasPermission(perm)) {
                                player.sendMessage(
                                        ChatColor.translateAlternateColorCodes('&', prefix)
                                                + " "
                                                + ChatColor.GRAY + p.getName() + " » " + mess);
                            }
                        }
                        cooldown.put(p.getUniqueId(), System.currentTimeMillis() + (COOLDOWN_SECONDS * 1000));
                    } else{
                        p.sendMessage(ChatColor.RED + "Non hai il permesso per scrivere in questa chat");
                    }
                }else{
                    p.sendMessage(ChatColor.RED + "Questa chat non esiste");
                }
            }
        }
        return false;
    }
}
