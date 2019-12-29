package com.blackace.fakeplayerfix;

import com.blackace.fakeplayerfix.command.CommandFakePlayer;
import com.blackace.fakeplayerfix.config.MyConfig;
import com.blackace.fakeplayerfix.config.MyConfigManager;
import com.blackace.fakeplayerfix.handlers.FakePlayerHandler;
import com.blackace.fakeplayerfix.listeners.FakePlayerListener;
import com.blackace.fakeplayerfix.reference.PathReference;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Created by Black Ace on 6/25/2015.
 */
public class FakePlayerFix extends JavaPlugin {

    public static FakePlayerFix instance;
    public static Logger logger;
    MyConfigManager manager;
    public static MyConfig config;
    public static FakePlayerHandler fakePlayerHandler;
    FakePlayerListener fakePlayerListener;

    @Override
    public void onEnable(){
        instance = this;
        logger = Bukkit.getLogger();

        manager = new MyConfigManager(this);
        config = manager.getNewConfig("config.yml");
        createConfig();

        fakePlayerHandler = new FakePlayerHandler();

        fakePlayerListener = new FakePlayerListener();

        this.getCommand("fakeplayer").setExecutor(new CommandFakePlayer());
    }



    private void createConfig(){
        config.create(PathReference.PlacePassthroughList, new String[]{"FakePlayer1","FakePlayer2","FakePlayer3"});
        config.create(PathReference.BreakPassthroughList, new String[]{"FakePlayer1","FakePlayer2","FakePlayer3"});
        config.create(PathReference.UsePassthroughList, new String[]{"FakePlayer1","FakePlayer2","FakePlayer3"});
        config.create(PathReference.DamagePassthroughList, new String[]{"FakePlayer1","FakePlayer2","FakePlayer3"});
        config.create(PathReference.Debug, false);
        config.create(PathReference.PvP, false);
        config.saveConfig();
    }

}
