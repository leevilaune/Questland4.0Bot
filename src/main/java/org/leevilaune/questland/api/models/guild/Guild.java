package org.leevilaune.questland.api.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Guild {

    private int staticdataTs;
    private int ts;

    private GuildPInfo pInfo;

    private GuildsList guildsList;

    private Object guildAcademyTimers;

    public int getStaticdataTs() {
        return staticdataTs;
    }
    @JsonProperty("staticdata_ts")
    public void setStaticdataTs(int staticdataTs) {
        this.staticdataTs = staticdataTs;
    }

    public int getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    public GuildPInfo getpInfo() {
        return pInfo;
    }
    @JsonProperty("pinfo")
    public void setpInfo(GuildPInfo pInfo) {
        this.pInfo = pInfo;
    }
    public GuildsList getGuildsList() {
        return guildsList;
    }
    @JsonProperty("guilds_list")
    public void setGuildsList(GuildsList guildsList) {
        this.guildsList = guildsList;
    }

    public Object getGuildAcademyTimers() {
        return guildAcademyTimers;
    }
    @JsonIgnore
    @JsonProperty("player_guild_academy_timers")
    public void setGuildAcademyTimers(Object guildAcademyTimers) {
        this.guildAcademyTimers = guildAcademyTimers;
    }

    public String print(){
        GuildInfo guild = this.guildsList.getGuildInfo();
        return this.guildsList.getGuildInfo().getN() +
                "\n    GQE  :   " + guild.getQeRankPoints() +
                "\n    Level:   " + guild.getLvl() +
                "\n    Members: " + guild.getM();
    }

    @Override
    public String toString() {
        return "Guild{" +
                "staticdataTs=" + staticdataTs +
                ",\n ts=" + ts +
                ",\n pInfo=" + pInfo +
                ",\n guildsList=" + guildsList +
                "\n}";
    }
}
