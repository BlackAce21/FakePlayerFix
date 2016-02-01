package com.blackace.fakeplayerfix.handlers;

import com.blackace.fakeplayerfix.FakePlayerFix;
import com.blackace.fakeplayerfix.reference.PathReference;

import java.util.List;

/**
 * Created by Black Ace on 6/25/2015.
 */
public class FakePlayerHandler {

    public List<String> BreakList;
    public List<String> PlaceList;
    public boolean DebugMode;

    public FakePlayerHandler(){
        this.BreakList = getBreakList();
        this.PlaceList = getPlaceList();
        this.DebugMode = getDebugMode();
    }


    private List<String> getBreakList(){
        return FakePlayerFix.config.getStringList(PathReference.BreakPassthroughList);
    }

    private List<String> getPlaceList(){
        return FakePlayerFix.config.getStringList(PathReference.PlacePassthroughList);
    }

    private boolean getDebugMode(){
        return FakePlayerFix.config.getBoolean(PathReference.Debug);
    }

    public void reload(){
        FakePlayerFix.config.reloadConfig();
        this.BreakList = getBreakList();
        this.PlaceList = getPlaceList();
        this.DebugMode = getDebugMode();
    }
}
