package org.leevilaune.questland.api.models.player;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerInfo {

    @JsonProperty("pow_rank")
    private int powRank;
    private P p;

    @JsonProperty("battle_event")
    private BattleEvent battleEvent;
    private Talents talents;

    public int getPowRank() {
        return powRank;
    }

    public void setPowRank(int powRank) {
        this.powRank = powRank;
    }

    public P getP() {
        return p;
    }

    public void setP(P p) {
        this.p = p;
    }

    public BattleEvent getBattleEvent() {
        return battleEvent;
    }

    public void setBattleEvent(BattleEvent battleEvent) {
        this.battleEvent = battleEvent;
    }

    public Talents getTalents() {
        return talents;
    }

    public void setTalents(Talents talents) {
        this.talents = talents;
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "powRank=" + powRank +
                ", p=" + p +
                '}';
    }
}
