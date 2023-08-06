package org.leevilaune.questland.api.models.requests.guild;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GuildPlayer {

    private List<Integer> fce;
    private int bre;
    private List<Integer> g_banner;
    private int id;
    private String name;
    private int frame;
    private int suffix;
    private String sex;
    @JsonProperty("g_sname")
    private String gSname;
    @JsonProperty("g_flvl")
    private String fameLevel;
    @JsonProperty("lvl")
    private int level;
    private int power;
    @JsonProperty("g_id")
    private int guildID;
    @JsonProperty("g_rank")
    private String guildRank;
    private int del;
    private int lts;
    @JsonProperty("g_name")
    private String guildName;

    public List<Integer> getFce() {
        return fce;
    }

    public void setFce(List<Integer> fce) {
        this.fce = fce;
    }

    public int getBre() {
        return bre;
    }

    public void setBre(int bre) {
        this.bre = bre;
    }

    public List<Integer> getG_banner() {
        return g_banner;
    }

    public void setG_banner(List<Integer> g_banner) {
        this.g_banner = g_banner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public int getSuffix() {
        return suffix;
    }

    public void setSuffix(int suffix) {
        this.suffix = suffix;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getgSname() {
        return gSname;
    }

    public void setgSname(String gSname) {
        this.gSname = gSname;
    }

    public String getFameLevel() {
        return fameLevel;
    }

    public void setFameLevel(String fameLevel) {
        this.fameLevel = fameLevel;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getGuildID() {
        return guildID;
    }

    public void setGuildID(int guildID) {
        this.guildID = guildID;
    }

    public String getGuildRank() {
        return guildRank;
    }

    public void setGuildRank(String guildRank) {
        this.guildRank = guildRank;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public int getLts() {
        return lts;
    }

    public void setLts(int lts) {
        this.lts = lts;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    @Override
    public String toString() {
        return "GuildPlayer{" +
                "fce=" + fce +
                ", bre=" + bre +
                ", g_banner=" + g_banner +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", frame=" + frame +
                ", suffix=" + suffix +
                ", sex='" + sex + '\'' +
                ", gSname='" + gSname + '\'' +
                ", fameLevel='" + fameLevel + '\'' +
                ", level=" + level +
                ", power=" + power +
                ", guildID=" + guildID +
                ", guildRank='" + guildRank + '\'' +
                ", del=" + del +
                ", lts=" + lts +
                ", guildName='" + guildName + '\'' +
                '}';
    }
}
