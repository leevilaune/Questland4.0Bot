package org.leevilaune.questland.api.models.guild;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GuildsList {

    @JsonProperty("guild_info")
    private GuildInfo guildInfo;

    public GuildInfo getGuildInfo() {
        return guildInfo;
    }

    public void setGuildInfo(GuildInfo guildInfo) {
        this.guildInfo = guildInfo;
    }

    @Override
    public String toString() {
        return "GuildsList{" +
                "guildInfo=" + guildInfo +
                '}';
    }
}
