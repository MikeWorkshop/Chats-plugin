package com.mike.chats.PlayerCmd;

import com.mike.chats.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player)sender;
        if (args.length == 1) return StringUtil.copyPartialMatches(args[0], FileManager.getChatsConPerm(player), new ArrayList<>()) ;
        return Arrays.asList();
    }
}
