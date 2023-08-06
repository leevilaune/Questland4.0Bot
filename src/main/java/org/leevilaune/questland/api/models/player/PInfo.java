package org.leevilaune.questland.api.models.player;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PInfo {

    @JsonProperty("player_info")
    private PlayerInfo playerInfo;

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    @Override
    public String toString() {
        return "PInfo{" +
                "playerInfo=" + playerInfo +
                '}';
    }
}
