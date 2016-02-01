package com.blackace.fakeplayerfix.command;

import com.blackace.fakeplayerfix.FakePlayerFix;
import com.blackace.fakeplayerfix.reference.PathReference;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Black Ace on 6/25/2015.
 */
public class CommandFakePlayer implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmdLabel, String[] args) {

        if(!sender.isOp()) return false;

        if(args.length == 0) return false;

        if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            FakePlayerFix.fakePlayerHandler.reload();
            sender.sendMessage(ChatColor.GREEN + "Fake player config reloaded!");
            return true;
        }

        if(args.length == 1 && args[0].equalsIgnoreCase("debug")){
            if(FakePlayerFix.fakePlayerHandler.DebugMode) {
                FakePlayerFix.fakePlayerHandler.DebugMode = false;
                FakePlayerFix.config.set(PathReference.Debug, false);
                FakePlayerFix.config.saveConfig();
                sender.sendMessage(ChatColor.RED + "Console debug mode disabled!");
            } else {
                FakePlayerFix.fakePlayerHandler.DebugMode = true;
                FakePlayerFix.config.set(PathReference.Debug, true);
                FakePlayerFix.config.saveConfig();
                sender.sendMessage(ChatColor.GREEN + "Console debug mode enabled!");
            }
        }

        if(args.length == 1 && args[0].equalsIgnoreCase("list")){
            sender.sendMessage(ChatColor.DARK_GRAY + "+-----------------------------------------------+");
            sender.sendMessage(ChatColor.GOLD + "+===Block break list===+");
            for(String name : FakePlayerFix.fakePlayerHandler.BreakList){
                sender.sendMessage(ChatColor.AQUA + name);
            }
            sender.sendMessage(ChatColor.GOLD + "+===Block place list===+");
            for(String name : FakePlayerFix.fakePlayerHandler.PlaceList){
                sender.sendMessage(ChatColor.AQUA + name);
            }
            sender.sendMessage(ChatColor.DARK_GRAY + "+-----------------------------------------------+");

        }

        return false;
    }


}
