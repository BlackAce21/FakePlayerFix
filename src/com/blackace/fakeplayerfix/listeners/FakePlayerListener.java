package com.blackace.fakeplayerfix.listeners;

import com.blackace.fakeplayerfix.FakePlayerFix;
import com.blackace.fakeplayerfix.handlers.FakePlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.List;

/**
 * Created by Black Ace on 6/25/2015.
 */
public class FakePlayerListener implements Listener {

    public FakePlayerListener(){
        Bukkit.getServer().getPluginManager().registerEvents(this, FakePlayerFix.instance);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreakEvent(BlockBreakEvent e){
        String name = e.getPlayer().getName();
        String displayName = e.getPlayer().getDisplayName();
        String customName = e.getPlayer().getCustomName();

        if(FakePlayerFix.fakePlayerHandler.DebugMode){
            FakePlayerFix.logger.info("Fake player named " + name + " threw a break event for a " + e.getBlock().getType());
        }

        if(doesListContainPlayer(FakePlayerFix.fakePlayerHandler.BreakList, name, displayName, customName)){
            if(e.isCancelled()) {
                e.setCancelled(false);

                if (FakePlayerFix.fakePlayerHandler.DebugMode) {
                    FakePlayerFix.logger.info("Event was scheduled to be cancelled, allowing the event to pass.");
                }
            } else {
                if(FakePlayerFix.fakePlayerHandler.DebugMode){
                    FakePlayerFix.logger.info("Event was scheduled to be passed, no action taken");
                }
            }

        } else {
            if(FakePlayerFix.fakePlayerHandler.DebugMode){
                FakePlayerFix.logger.info("Fake Player was not on the list.");
            }
        }
        if(FakePlayerFix.fakePlayerHandler.DebugMode) {
            FakePlayerFix.logger.info("=------------------------------------------------=");
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPlaceEvent(BlockPlaceEvent e){
        String name = e.getPlayer().getName();
        String displayName = e.getPlayer().getDisplayName();
        String customName = e.getPlayer().getCustomName();

        if(FakePlayerFix.fakePlayerHandler.DebugMode){
            FakePlayerFix.logger.info("=------------------------------------------------=");
            FakePlayerFix.logger.info("Fake player named " + name + " threw a place event for a " + e.getBlockPlaced().getType());
        }

        if(doesListContainPlayer(FakePlayerFix.fakePlayerHandler.PlaceList, name, displayName, customName)){
            if(e.isCancelled()) {
                e.setCancelled(false);
                e.setBuild(true);
                if (FakePlayerFix.fakePlayerHandler.DebugMode) {
                    FakePlayerFix.logger.info("Event was scheduled to be cancelled, allowing the event to pass.");
                }
            } else {
                if(FakePlayerFix.fakePlayerHandler.DebugMode){
                    FakePlayerFix.logger.info("Event was scheduled to be passed, no action taken");
                }
            }
        } else {
            if(FakePlayerFix.fakePlayerHandler.DebugMode){
                FakePlayerFix.logger.info("Fake Player was not on the list.");
            }
        }
        if(FakePlayerFix.fakePlayerHandler.DebugMode) {
            FakePlayerFix.logger.info("=------------------------------------------------=");
        }
    }


    private boolean doesListContainPlayer(List<String> list, String name, String displayName, String customName){
        for(String s : list){
            if(s.equalsIgnoreCase(name) || s.equalsIgnoreCase(displayName) || s.equalsIgnoreCase(customName)){
                return true;
            }
        }
        return false;
    }

}
