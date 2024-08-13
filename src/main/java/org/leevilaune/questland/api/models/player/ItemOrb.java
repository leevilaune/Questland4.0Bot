package org.leevilaune.questland.api.models.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
public class ItemOrb {

    private String itemID;
    private int orbID;
    private String slot;
    private int potential;
    private int level;
    private int enhance;
    private double bonus;


    public ItemOrb(String itemID, int orbID, int amount, List<String> orb) {
        this.itemID = itemID;
        this.orbID = orbID;

    }

    public ItemOrb(){

    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public int getOrbID() {
        return orbID;
    }

    public void setOrbID(int orbID) {
        this.orbID = orbID;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public int getPotential() {
        return potential;
    }

    public void setPotential(int potential) {
        this.potential = potential;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getEnhance() {
        return enhance;
    }

    public void setEnhance(int enhance) {
        this.enhance = enhance;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "ItemOrb{" +
                "itemID='" + itemID + '\'' +
                ", orbID=" + orbID +
                ", slot='" + slot + '\'' +
                ", potential=" + potential +
                ", level=" + level +
                ", enhance=" + enhance +
                ", bonus=" + bonus +
                '}';
    }
}
