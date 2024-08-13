package org.leevilaune.questland.api.models.beranking;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class RankingBattleEvent {

    @JsonProperty("battle_event")
    private BattleEvent battleEvent;

    public RankingBattleEvent(){

    }

    public BattleEvent getBattleEvent() {
        return battleEvent;
    }

    public void setBattleEvent(BattleEvent battleEvent) {
        this.battleEvent = battleEvent;
    }
}
