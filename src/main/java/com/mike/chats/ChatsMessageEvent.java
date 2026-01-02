package com.mike.chats;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ChatsMessageEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();

    private boolean cancelled;
    private final Player player;
    private final String chatName;
    private final String message;

    public ChatsMessageEvent(Player player, String chatName, String message) {
        this.player = player;
        this.chatName = chatName;
        this.message = message;
        this.cancelled = false;
    }

    public Player getPlayer() {
        return player;
    }

    public String getChatName() {
        return chatName;
    }

    public String getChatMessage() {
        return message;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }
}
