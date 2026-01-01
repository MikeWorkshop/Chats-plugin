package com.mike.chats;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
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
}
