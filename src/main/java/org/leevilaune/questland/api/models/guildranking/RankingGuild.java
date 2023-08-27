package org.leevilaune.questland.api.models.guildranking;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class RankingGuild {

    private int priv;
    @JsonProperty("req_vip")
    private int reqVip;
    @JsonProperty("req_heropower")
    private int reqHeropower;
    private int[] banner;
    private String sn;
    private String c;
    private int p;
    @JsonProperty("mmc")
    private int maxMembers;
    private String desc;
    private int exp;
    @JsonProperty("activity_tier")
    private int activityTier;
    @JsonProperty("invite_counter")
    private int inviteCounter;
    @JsonProperty("n")
    private String name;
    @JsonProperty("m")
    private int membersCount;
    private int lvl;
    @JsonProperty("qe_rank_points")
    private int qeRankPoints;

    public int getPriv() {
        return priv;
    }

    public void setPriv(int priv) {
        this.priv = priv;
    }

    public int getReqVip() {
        return reqVip;
    }

    public void setReqVip(int reqVip) {
        this.reqVip = reqVip;
    }

    public int getReqHeropower() {
        return reqHeropower;
    }

    public void setReqHeropower(int reqHeropower) {
        this.reqHeropower = reqHeropower;
    }

    public int[] getBanner() {
        return banner;
    }

    public void setBanner(int[] banner) {
        this.banner = banner;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(int maxMembers) {
        this.maxMembers = maxMembers;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getActivityTier() {
        return activityTier;
    }

    public void setActivityTier(int activityTier) {
        this.activityTier = activityTier;
    }

    public int getInviteCounter() {
        return inviteCounter;
    }

    public void setInviteCounter(int inviteCounter) {
        this.inviteCounter = inviteCounter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(int membersCount) {
        this.membersCount = membersCount;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getQeRankPoints() {
        return qeRankPoints;
    }

    public void setQeRankPoints(int qeRankPoints) {
        this.qeRankPoints = qeRankPoints;
    }

    @Override
    public String toString() {
        return "RankingGuild{" +
                "\npriv=" + priv +
                "\n, reqVip=" + reqVip +
                "\n, reqHeropower=" + reqHeropower +
                "\n, banner=" + Arrays.toString(banner) +
                "\n, sn='" + sn + '\'' +
                "\n, c='" + c + '\'' +
                "\n, p=" + p +
                "\n, maxMembers=" + maxMembers +
                "\n, desc='" + desc + '\'' +
                "\n, exp=" + exp +
                "\n, activityTier=" + activityTier +
                "\n, inviteCounter=" + inviteCounter +
                "\n, name='" + name + '\'' +
                "\n, membersCount=" + membersCount +
                "\n, lvl=" + lvl +
                "\n, qeRankPoints=" + qeRankPoints +
                '}';
    }
}
