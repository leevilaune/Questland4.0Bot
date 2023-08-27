package org.leevilaune.questland.api.models.guild;

import java.util.List;

public class GuildPInfo {

    private List<GuildPlayer> guildPlayers;

    public List<GuildPlayer> getGuildPlayers() {
        return guildPlayers;
    }

    public void setGuildPlayers(List<GuildPlayer> guildPlayers) {
        this.guildPlayers = guildPlayers;
    }

    @Override
    public String toString() {
        return "GuildPInfo{" +
                "guildPlayers=" + guildPlayers +
                '}';
    }
}
