package org.leevilaune.questland.api.models.guildranking;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GlobalGuild {

    @JsonProperty("offset")
    private int offSet;
    private int league;
    private List<Integer[]> ladder;

    public int getOffSet() {
        return offSet;
    }

    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }

    public int getLeague() {
        return league;
    }

    public void setLeague(int league) {
        this.league = league;
    }

    public List<Integer[]> getLadder() {
        return ladder;
    }

    public void setLadder(List<Integer[]> ladder) {
        this.ladder = ladder;
    }
}
