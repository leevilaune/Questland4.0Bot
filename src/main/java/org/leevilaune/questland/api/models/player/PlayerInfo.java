package org.leevilaune.questland.api.models.requests.player;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerInfo {

    @JsonProperty("pow_rank")
    private int powRank;
    private P p;

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

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "powRank=" + powRank +
                ", p=" + p +
                '}';
    }
}
