package org.leevilaune.questland.api.models.guildranking;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GuildRanking {

    @JsonIgnore
    @JsonProperty("guilds_list")
    private List<RankingGuild> guildsList;
    private Ranking ranking;
    private int ts;
    @JsonProperty("staticdata_ts")
    private int staticDataTS;

    public List<RankingGuild> getGuildsList() {
        return guildsList;
    }

    public void setGuildsList(List<RankingGuild> guildsList) {
        this.guildsList = guildsList;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    public int getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    public int getStaticDataTS() {
        return staticDataTS;
    }

    public void setStaticDataTS(int staticDataTS) {
        this.staticDataTS = staticDataTS;
    }

    @Override
    public String toString() {
        return "GuildRanking{" +
                "guildsList=" + guildsList +
                ", ranking=" + ranking +
                ", ts=" + ts +
                ", staticDataTS=" + staticDataTS +
                '}';
    }
}
