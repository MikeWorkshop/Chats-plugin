package com.mike.chats.AdminCmd;

import com.mike.chats.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ACompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1)
            return Arrays.asList("crea", "elimina", "setprefix");

        if (args.length == 2 && (args[0].equalsIgnoreCase("elimina") || args[0].equalsIgnoreCase("setprefix")))
            return FileManager.getAllChats();

        return Arrays.asList();
    }
}
