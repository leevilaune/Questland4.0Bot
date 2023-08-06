package org.leevilaune.questland.api.models.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class P {

    @JsonProperty("g_rank")
    private String guildRank;
    private String cc;
    @JsonProperty("g_don_ts")
    private int gDonTs;
    private int vip;
    private String name;
    private int del;
    private String sex;
    @JsonProperty("g_name")
    private String guildName;
    private int flags;
    private PlayerAttributes attr;
    private int power;
    @JsonProperty("g_flvl")
    private int fame;

    @JsonIgnore
    private Object col1;
    @JsonIgnore
    private Object col2;

    public String getGuildRank() {
        return guildRank;
    }

    public void setGuildRank(String guildRank) {
        this.guildRank = guildRank;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public int getgDonTs() {
        return gDonTs;
    }

    public void setgDonTs(int gDonTs) {
        this.gDonTs = gDonTs;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public PlayerAttributes getAttr() {
        return attr;
    }

    public void setAttr(PlayerAttributes attr) {
        this.attr = attr;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getFame() {
        return fame;
    }

    public void setFame(int fame) {
        this.fame = fame;
    }

    public Object getCol1() {
        return col1;
    }

    public void setCol1(Object col1) {
        this.col1 = col1;
    }

    public Object getCol2() {
        return col2;
    }

    public void setCol2(Object col2) {
        this.col2 = col2;
    }

    @Override
    public String toString() {
        return "P{" +
                "guildRank='" + guildRank + '\'' +
                ", cc='" + cc + '\'' +
                ", gDonTs=" + gDonTs +
                ", vip=" + vip +
                ", name='" + name + '\'' +
                ", del=" + del +
                ", sex='" + sex + '\'' +
                ", guildName='" + guildName + '\'' +
                ", flags=" + flags +
                ", attr=" + attr +
                ", power=" + power +
                ", fame=" + fame +
                ", col1=" + col1 +
                ", col2=" + col2 +
                '}';
    }
}
