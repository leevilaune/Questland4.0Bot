package org.leevilaune.questland.api.models.beranking;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.leevilaune.questland.api.models.player.PInfo;

import java.util.List;

public class BattleEventRanking {

    @JsonIgnore
    @JsonProperty("ts")
    private Object ts;

    @JsonIgnore
    @JsonProperty("pinfo")
    private Object pinfo;

    @JsonProperty("staticdata_ts")
    private Object staticdataTS;
    @JsonProperty("ranking")
    private RankingBattleEvent battleEvent;

    public BattleEventRanking(){

    }

    public RankingBattleEvent getBattleEvent() {
        return battleEvent;
    }

    public void setBattleEvent(RankingBattleEvent battleEvent) {
        this.battleEvent = battleEvent;
    }

    @Override
    public String toString() {
        return "BattleEventRanking{" +
                "battleEvent=" + battleEvent +
                '}';
    }
}
