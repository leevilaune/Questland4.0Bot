package org.leevilaune.questland.api.models.player;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerAttributes {

    @JsonProperty("def")
    private int defence;
    @JsonProperty("hp")
    private int health;
    @JsonProperty("mag")
    private int magic;
    private double crit;
    private int critch;
    private int dod;
    @JsonProperty("dmg")
    private int attack;

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public double getCrit() {
        return crit;
    }

    public void setCrit(double crit) {
        this.crit = crit;
    }

    public int getCritch() {
        return critch;
    }

    public void setCritch(int critch) {
        this.critch = critch;
    }

    public int getDod() {
        return dod;
    }

    public void setDod(int dod) {
        this.dod = dod;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public String toString() {
        return "PlayerAttributes{" +
                "defence=" + defence +
                ", health=" + health +
                ", magic=" + magic +
                ", crit=" + crit +
                ", critch=" + critch +
                ", dod=" + dod +
                ", attack=" + attack +
                '}';
    }
}
