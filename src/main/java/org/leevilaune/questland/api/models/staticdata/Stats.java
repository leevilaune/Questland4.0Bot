package org.leevilaune.questland.api.models.staticdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Stats {

    @JsonProperty("dmg")
    private List<Integer> attack;
    @JsonProperty("def")
    private List<Integer> defence;
    @JsonProperty("hp")
    private List<Integer> health;
    @JsonProperty("magic")
    private List<Integer> magic;

    public List<Integer> getAttack() {
        return attack;
    }

    public void setAttack(List<Integer> attack) {
        this.attack = attack;
    }

    public List<Integer> getDefence() {
        return defence;
    }

    public void setDefence(List<Integer> defence) {
        this.defence = defence;
    }

    public List<Integer> getHealth() {
        return health;
    }

    public void setHealth(List<Integer> health) {
        this.health = health;
    }

    public List<Integer> getMagic() {
        return magic;
    }

    public void setMagic(List<Integer> magic) {
        this.magic = magic;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "attack=" + attack +
                ", defence=" + defence +
                ", health=" + health +
                ", magic=" + magic +
                '}';
    }
}
