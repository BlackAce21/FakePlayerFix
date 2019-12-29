package com.blackace.fakeplayerfix.command;

import com.blackace.fakeplayerfix.FakePlayerFix;
import com.blackace.fakeplayerfix.handlers.FakePlayerHandler;
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

        if(args.length == 0){
            sendHelpMessage(sender);
            return true;
        }

        if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            FakePlayerFix.fakePlayerHandler.reload();
            sender.sendMessage(ChatColor.GREEN + "Fake player config reloaded!");
            return true;
        }

        if(args.length == 1 && args[0].equalsIgnoreCase("pvp")){
            if(FakePlayerFix.fakePlayerHandler.PvPMode) {
                FakePlayerFix.fakePlayerHandler.PvPMode = false;
                FakePlayerFix.config.set(PathReference.PvP, false);
                FakePlayerFix.config.saveConfig();
                sender.sendMessage(ChatColor.RED + "PvP is not allowed!");
            } else {
                FakePlayerFix.fakePlayerHandler.PvPMode = true;
                FakePlayerFix.config.set(PathReference.PvP, true);
                FakePlayerFix.config.saveConfig();
                sender.sendMessage(ChatColor.GREEN + "PvP is allowed!");
            }
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
            sender.sendMessage(ChatColor.GOLD + "+===Use/Interact list===+");
            for(String name : FakePlayerFix.fakePlayerHandler.UseList){
                sender.sendMessage(ChatColor.AQUA + name);
            }
            sender.sendMessage(ChatColor.GOLD + "+===Damage list===+");
            for(String name : FakePlayerFix.fakePlayerHandler.DamageList){
                sender.sendMessage(ChatColor.AQUA + name);
            }
            sender.sendMessage(ChatColor.DARK_GRAY + "+-----------------------------------------------+");

        }

        if(args.length == 3 && args[0].equalsIgnoreCase("add")){
            if(args[1].equalsIgnoreCase("break")){
                FakePlayerFix.fakePlayerHandler.addBreak(args[2]);
                sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.GREEN + args[2] + ChatColor.GOLD + " added to break list.");
            }
            if(args[1].equalsIgnoreCase("place")){
                FakePlayerFix.fakePlayerHandler.addPlace(args[2]);
                sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.GREEN + args[2] + ChatColor.GOLD + " added to place list.");
            }
            if(args[1].equalsIgnoreCase("use")){
                FakePlayerFix.fakePlayerHandler.addUse(args[2]);
                sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.GREEN + args[2] + ChatColor.GOLD + " added to use/interact list.");
            }
            if(args[1].equalsIgnoreCase("damage")){
                FakePlayerFix.fakePlayerHandler.addDamage(args[2]);
                sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.GREEN + args[2] + ChatColor.GOLD + " added to damage list.");
            }
        }

        if(args.length == 3 && args[0].equalsIgnoreCase("remove")){
            if(args[1].equalsIgnoreCase("break")){
                if(FakePlayerFix.fakePlayerHandler.removeBreak(args[2])) {
                    sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + args[2] + ChatColor.GOLD + " removed from break list.");
                } else {
                    sender.sendMessage(ChatColor.RED + "Player not found on this list. Use /fakeplayer list to check.");
                }
            }
            if(args[1].equalsIgnoreCase("place")){
                if(FakePlayerFix.fakePlayerHandler.removePlace(args[2])) {
                    sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + args[2] + ChatColor.GOLD + " removed from place list.");
                } else {
                    sender.sendMessage(ChatColor.RED + "Player not found on this list. Use /fakeplayer list to check.");
                }
            }
            if(args[1].equalsIgnoreCase("use")){
                if(FakePlayerFix.fakePlayerHandler.removeUse(args[2])) {
                    sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + args[2] + ChatColor.GOLD + " removed from use list.");
                } else {
                    sender.sendMessage(ChatColor.RED + "Player not found on this list. Use /fakeplayer list to check.");
                }
            }
            if(args[1].equalsIgnoreCase("damage")){
                if(FakePlayerFix.fakePlayerHandler.removeDamage(args[2])) {
                    sender.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + args[2] + ChatColor.GOLD + " removed from damage list.");
                } else {
                    sender.sendMessage(ChatColor.RED + "Player not found on this list. Use /fakeplayer list to check.");
                }
            }
        }



        return false;
    }

    private void sendHelpMessage(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "Use /fakeplayer reload to reload the config.");
        sender.sendMessage(ChatColor.GOLD + "Use /fakeplayer list to display all fake players.");
        sender.sendMessage(ChatColor.GOLD + "Use /fakeplayer debug to turn on console event logging.");
        sender.sendMessage(ChatColor.GOLD + "Use /fakeplayer [add|remove] [break|place|use|damage] <name> to add or remove a player from that specific list. (Case Sensitive)");
    }


}
