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
    public List<String> UseList;
    public List<String> DamageList;
    public boolean DebugMode;
    public boolean PvPMode;

    public FakePlayerHandler(){
        this.BreakList = getBreakList();
        this.PlaceList = getPlaceList();
        this.UseList = getUseList();
        this.DamageList = getDamageList();
        this.DebugMode = getDebugMode();
        this.PvPMode = getPvPMode();
    }


    private List<String> getBreakList(){
        return FakePlayerFix.config.getStringList(PathReference.BreakPassthroughList);
    }

    private List<String> getPlaceList(){
        return FakePlayerFix.config.getStringList(PathReference.PlacePassthroughList);
    }

    private List<String> getUseList(){
        return FakePlayerFix.config.getStringList(PathReference.UsePassthroughList);
    }

    private List<String> getDamageList(){
        return FakePlayerFix.config.getStringList(PathReference.DamagePassthroughList);
    }

    public void addBreak(String s){
        BreakList.add(s);
        FakePlayerFix.config.set(PathReference.BreakPassthroughList, BreakList);
        FakePlayerFix.config.saveConfig();
    }

    public void addPlace(String s){
        PlaceList.add(s);
        FakePlayerFix.config.set(PathReference.PlacePassthroughList, PlaceList);
        FakePlayerFix.config.saveConfig();
    }

    public void addUse(String s){
        UseList.add(s);
        FakePlayerFix.config.set(PathReference.UsePassthroughList, UseList);
        FakePlayerFix.config.saveConfig();
    }

    public void addDamage(String s){
        DamageList.add(s);
        FakePlayerFix.config.set(PathReference.DamagePassthroughList, DamageList);
        FakePlayerFix.config.saveConfig();
    }

    public boolean removeBreak(String s){
        if(BreakList.contains(s)) {
            BreakList.remove(s);
            FakePlayerFix.config.set(PathReference.BreakPassthroughList, BreakList);
            FakePlayerFix.config.saveConfig();
            return true;
        } else {
            return false;
        }
    }

    public boolean removePlace(String s){
        if(PlaceList.contains(s)) {
            PlaceList.remove(s);
            FakePlayerFix.config.set(PathReference.PlacePassthroughList, PlaceList);
            FakePlayerFix.config.saveConfig();
            return true;
        } else {
            return false;
        }
    }

    public boolean removeUse(String s){
        if(UseList.contains(s)){
            UseList.remove(s);
            FakePlayerFix.config.set(PathReference.UsePassthroughList, UseList);
            FakePlayerFix.config.saveConfig();
            return true;
        } else {
            return false;
        }
    }

    public boolean removeDamage(String s){
        if(DamageList.contains(s)){
            DamageList.remove(s);
            FakePlayerFix.config.set(PathReference.DamagePassthroughList, DamageList);
            FakePlayerFix.config.saveConfig();
            return true;
        } else {
            return false;
        }
    }

    private boolean getDebugMode(){
        return FakePlayerFix.config.getBoolean(PathReference.Debug);
    }

    private boolean getPvPMode() { return FakePlayerFix.config.getBoolean(PathReference.PvP); }

    public void reload(){
        FakePlayerFix.config.reloadConfig();
        this.BreakList = getBreakList();
        this.PlaceList = getPlaceList();
        this.UseList = getUseList();
        this.DamageList = getDamageList();
        this.DebugMode = getDebugMode();
        this.PvPMode = getPvPMode();
    }
}
