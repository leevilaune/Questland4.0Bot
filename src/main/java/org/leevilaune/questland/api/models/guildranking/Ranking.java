package org.leevilaune.questland.api.models.guildranking;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ranking {

    @JsonProperty("global_guild")
    private GlobalGuild globalGuild;

    public GlobalGuild getGlobalGuild() {
        return globalGuild;
    }

    public void setGlobalGuild(GlobalGuild globalGuild) {
        this.globalGuild = globalGuild;
    }
}
