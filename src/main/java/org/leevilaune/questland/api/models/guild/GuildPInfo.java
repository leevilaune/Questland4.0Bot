package org.leevilaune.questland.api.models.requests.guild;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GuildPInfo {

    private List<GuildPlayerID> guildPlayers;

    public List<GuildPlayerID> getGuildPlayers() {
        return guildPlayers;
    }

    public void setGuildPlayers(List<GuildPlayerID> guildPlayers) {
        this.guildPlayers = guildPlayers;
    }

    @Override
    public String toString() {
        return "GuildPInfo{" +
                "guildPlayers=" + guildPlayers +
                '}';
    }
}
