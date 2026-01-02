package com.mike.chats;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (player.hasPermission(Chats.getCF().getString("permesso_admin"))) return;

        TextComponent mess = new TextComponent("§b§lChats.jar§7 - plugin gratuito creato dal canale Telegram §b@Mike_Workshop");
        mess.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://t.me/mike_workshop"));
        mess.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new BaseComponent[]{ new TextComponent("Scopri il canale Mike Workshop su Telegram") }
        ));

        player.spigot().sendMessage(mess);
    }

    @EventHandler
    public void onChatsMessage(ChatsMessageEvent e){
        if (e.isCancelled()) return;

        String prefix = FileManager.getPrefisso(e.getChatName());
        String perm = FileManager.getPermesso(e.getChatName());

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission(perm)) {
                player.sendMessage(
                        ChatColor.translateAlternateColorCodes('&', prefix)
                                + " "
                                + ChatColor.GRAY + e.getPlayer().getName() + " » " + e.getChatMessage());
            }
        }
    }
}
