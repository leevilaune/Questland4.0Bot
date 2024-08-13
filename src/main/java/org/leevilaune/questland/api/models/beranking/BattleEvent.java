package org.leevilaune.questland.api.models.beranking;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class BattleEvent {

    private List<List<Integer>> ladder;
    private int league;
    @JsonProperty("myscore")
    private int myScore;
    private int offset;
    @JsonIgnore
    @JsonProperty("tiers")
    private Objects tiers;

    public BattleEvent(){

    }

    public List<List<Integer>> getLadder() {
        return ladder;
    }

    public void setLadder(List<List<Integer>> ladder) {
        this.ladder = ladder;
    }

    public int getLeague() {
        return league;
    }

    public void setLeague(int league) {
        this.league = league;
    }

    public int getMyScore() {
        return myScore;
    }

    public void setMyScore(int myScore) {
        this.myScore = myScore;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "BattleEvent{" +
                "ladder=" + ladder +
                ", league=" + league +
                ", myScore=" + myScore +
                ", offset=" + offset +
                '}';
    }
}
