package org.leevilaune.questland.api.models.guild;

import java.util.List;

public class Academy {

    private List<Integer> damage;
    private List<Integer> defence;
    private List<Integer> hp;
    private List<Integer> magic;

    public List<Integer> getDamage() {
        return damage;
    }

    public void setDamage(List<Integer> damage) {
        this.damage = damage;
    }

    public List<Integer> getDefence() {
        return defence;
    }

    public void setDefence(List<Integer> defence) {
        this.defence = defence;
    }

    public List<Integer> getHp() {
        return hp;
    }

    public void setHp(List<Integer> hp) {
        this.hp = hp;
    }

    public List<Integer> getMagic() {
        return magic;
    }

    public void setMagic(List<Integer> magic) {
        this.magic = magic;
    }
}
