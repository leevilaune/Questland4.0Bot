package org.leevilaune.questland.api.models.requests.guild;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GuildInfo {

    private String c;
    private int m;
    private String desc;
    private int priv;
    @JsonIgnore
    private Object academy;

    private int p;
    private int exp;
    @JsonProperty("activity_tier")
    private int activityTier;
    @JsonProperty("qe_rank_points")
    private int qeRankPoints;
    @JsonProperty("req_cnt")
    private int reqCnt;
    @JsonProperty("owner_active")
    private boolean ownerActive;
    private String sn;
    private int mmc;
    @JsonProperty("req_vip")
    private int reqVip;
    @JsonProperty("req_heropower")
    private int reqHeropower;
    private List<Integer> banner;
    @JsonProperty("invite_counter")
    private int inviteCounter;
    private List<Integer> members;
    @JsonProperty("collected_banners")
    private List<Integer> collectedBanners;
    private String n;
    private int lvl;
    @JsonProperty("req_level")
    private int reqLevel;

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPriv() {
        return priv;
    }

    public void setPriv(int priv) {
        this.priv = priv;
    }

    public Object getAcademy() {
        return academy;
    }

    public void setAcademy(Object academy) {
        this.academy = academy;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
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

    public int getQeRankPoints() {
        return qeRankPoints;
    }

    public void setQeRankPoints(int qeRankPoints) {
        this.qeRankPoints = qeRankPoints;
    }

    public int getReqCnt() {
        return reqCnt;
    }

    public void setReqCnt(int reqCnt) {
        this.reqCnt = reqCnt;
    }

    public boolean isOwnerActive() {
        return ownerActive;
    }

    public void setOwnerActive(boolean ownerActive) {
        this.ownerActive = ownerActive;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getMmc() {
        return mmc;
    }

    public void setMmc(int mmc) {
        this.mmc = mmc;
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

    public List<Integer> getBanner() {
        return banner;
    }

    public void setBanner(List<Integer> banner) {
        this.banner = banner;
    }

    public int getInviteCounter() {
        return inviteCounter;
    }

    public void setInviteCounter(int inviteCounter) {
        this.inviteCounter = inviteCounter;
    }

    public List<Integer> getMembers() {
        return members;
    }

    public void setMembers(List<Integer> members) {
        this.members = members;
    }

    public List<Integer> getCollectedBanners() {
        return collectedBanners;
    }

    public void setCollectedBanners(List<Integer> collectedBanners) {
        this.collectedBanners = collectedBanners;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getReqLevel() {
        return reqLevel;
    }

    public void setReqLevel(int reqLevel) {
        this.reqLevel = reqLevel;
    }

    @Override
    public String toString() {
        return "GuildInfo{" +
                "c='" + c + '\'' +
                ", m=" + m +
                ", desc='" + desc + '\'' +
                ", priv=" + priv +
                ", academy=" + academy +
                ", p=" + p +
                ", exp=" + exp +
                ", activityTier=" + activityTier +
                ", qeRankPoints=" + qeRankPoints +
                ", reqCnt=" + reqCnt +
                ", ownerActive=" + ownerActive +
                ", sn='" + sn + '\'' +
                ", mmc=" + mmc +
                ", reqVip=" + reqVip +
                ", reqHeropower=" + reqHeropower +
                ", banner=" + banner +
                ", inviteCounter=" + inviteCounter +
                ", members=" + members +
                ", collectedBanners=" + collectedBanners +
                ", n='" + n + '\'' +
                ", lvl=" + lvl +
                ", reqLevel=" + reqLevel +
                '}';
    }
}
