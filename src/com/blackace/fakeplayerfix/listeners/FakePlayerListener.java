package com.blackace.fakeplayerfix.listeners;

import com.blackace.fakeplayerfix.FakePlayerFix;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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
        if (e.getPlayer() == null) return;
        String name = e.getPlayer().getName();
        String displayName = e.getPlayer().getDisplayName();
        String customName = e.getPlayer().getCustomName();
        String listName = e.getPlayer().getPlayerListName();

        if(FakePlayerFix.fakePlayerHandler.DebugMode){
            FakePlayerFix.logger.info("=------------------------------------------------=");
            if(e.getBlock() == null){
                FakePlayerFix.logger.info("Player named " + name + " threw a break event for a null block");
            } else {
                FakePlayerFix.logger.info("Player named " + name + " threw a break event for a " + e.getBlock().getType());
            }
        }

        if(doesListContainPlayer(FakePlayerFix.fakePlayerHandler.BreakList, name, displayName, customName, listName)){
            if(e.isCancelled()) {
                e.setCancelled(false);
                if (FakePlayerFix.fakePlayerHandler.DebugMode) {
                    FakePlayerFix.logger.info("Event was scheduled to be cancelled, allowing the event to pass.");
                }
            } else {
                if(FakePlayerFix.fakePlayerHandler.DebugMode){
                    FakePlayerFix.logger.info("Event was not cancelled, no action taken");
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
        if (e.getPlayer() == null) return;
        String name = e.getPlayer().getName();
        String displayName = e.getPlayer().getDisplayName();
        String customName = e.getPlayer().getCustomName();
        String listName = e.getPlayer().getPlayerListName();

        if(FakePlayerFix.fakePlayerHandler.DebugMode){
            FakePlayerFix.logger.info("=------------------------------------------------=");
            if(e.getBlock() == null){
                FakePlayerFix.logger.info("Player named " + name + " threw a place event for a null block");
            } else {
                FakePlayerFix.logger.info("Player named " + name + " threw a place event for a " + e.getBlock().getType());
            }
        }

        if(doesListContainPlayer(FakePlayerFix.fakePlayerHandler.PlaceList, name, displayName, customName, listName)){
            if(e.isCancelled()) {
                e.setCancelled(false);
                e.setBuild(true);
                if (FakePlayerFix.fakePlayerHandler.DebugMode) {
                    FakePlayerFix.logger.info("Event was scheduled to be cancelled, allowing the event to pass.");
                }
            } else {
                if(FakePlayerFix.fakePlayerHandler.DebugMode){
                    FakePlayerFix.logger.info("Event was not cancelled, no action taken");
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
    public void onBlockClickEvent(PlayerInteractEvent e){
        if (e.getPlayer() == null) return;
        Player p = e.getPlayer();
        String name = p.getName();
        String displayName = p.getDisplayName();
        String customName = p.getCustomName();
        String listName = p.getPlayerListName();

        if(FakePlayerFix.fakePlayerHandler.DebugMode){
            FakePlayerFix.logger.info("=------------------------------------------------=");
            if(e.getClickedBlock() == null){
                FakePlayerFix.logger.info("Player named " + name + " threw an interact event for a null block");
            } else {
                FakePlayerFix.logger.info("Player named " + name + " threw an interact event for a " + e.getClickedBlock().getType());
            }
        }

        if(doesListContainPlayer(FakePlayerFix.fakePlayerHandler.UseList, name, displayName, customName, listName)){
            if(e.isCancelled()) {
                e.setCancelled(false);
                if (FakePlayerFix.fakePlayerHandler.DebugMode) {
                    FakePlayerFix.logger.info("Event was scheduled to be cancelled, allowing the event to pass.");
                }
            } else {
                if(FakePlayerFix.fakePlayerHandler.DebugMode){
                    FakePlayerFix.logger.info("Event was not cancelled, no action taken");
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
    public void onDamageEvent(EntityDamageByEntityEvent e){
        if (e.getDamager() == null) return;
        if(!(e.getDamager() instanceof Player)) return;
        Player p = (Player) e.getDamager();
        String name = p.getName();
        String displayName = p.getDisplayName();
        String customName = p.getCustomName();
        String listName = p.getPlayerListName();

        if(FakePlayerFix.fakePlayerHandler.DebugMode){
            FakePlayerFix.logger.info("=------------------------------------------------=");
            if(e.getEntity() == null){
                FakePlayerFix.logger.info("Player named " + name + " threw a damage event against a null entity");
            } else {
                FakePlayerFix.logger.info("Player named " + name + " threw a damage event against a " + e.getEntity().getType());
            }
        }

        if(doesListContainPlayer(FakePlayerFix.fakePlayerHandler.DamageList, name, displayName, customName, listName)){
            if(e.getEntity() instanceof Player){
                if(FakePlayerFix.fakePlayerHandler.PvPMode){
                    e.setCancelled(false);
                    if (FakePlayerFix.fakePlayerHandler.DebugMode) {
                        FakePlayerFix.logger.info("PvP on, event passed through.");
                    }
                    return;
                } else {
                    e.setCancelled(true);
                    if (FakePlayerFix.fakePlayerHandler.DebugMode) {
                        FakePlayerFix.logger.info("PvP off, event cancelled.");
                    }
                    return;
                }
            }
            if(e.isCancelled()){
                e.setCancelled(false);
                if (FakePlayerFix.fakePlayerHandler.DebugMode) {
                    FakePlayerFix.logger.info("Event was scheduled to be cancelled, allowing the event to pass.");
                }
            } else {
                if(FakePlayerFix.fakePlayerHandler.DebugMode){
                    FakePlayerFix.logger.info("Event was not cancelled, no action taken");
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

    private boolean doesListContainPlayer(List<String> list, String name, String displayName, String customName, String listName){
        for(String s : list){
            if(s.equalsIgnoreCase(name) || s.equalsIgnoreCase(displayName) || s.equalsIgnoreCase(customName) || s.equalsIgnoreCase(listName)){
                return true;
            }
        }
        return false;
    }

}
