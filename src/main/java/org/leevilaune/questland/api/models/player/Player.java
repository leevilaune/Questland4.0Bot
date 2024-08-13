package org.leevilaune.questland.api.models.player;

public class Player {


    private int staticdataTs;
    private int ts;
    private PInfo pinfo;

    public int getStaticdataTs() {
        return staticdataTs;
    }

    public void setStaticdataTs(int staticdataTs) {
        this.staticdataTs = staticdataTs;
    }

    public int getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    public PInfo getPinfo() {
        return pinfo;
    }

    public void setPinfo(PInfo pinfo) {
        this.pinfo = pinfo;
    }

    public String print(){
        P player = this.getPinfo().getPlayerInfo().getP();
        BattleEvent battleEvent = this.getPinfo().getPlayerInfo().getBattleEvent();
        return this.getPinfo().getPlayerInfo().getP().getName() +
                "\n   ID:    " + player.getId() +
                "\n   VIP:   " + player.getVip() +
                "\n   Power: " + player.getPower() +
                "\n   Multi: " + battleEvent.getMultiplier() +
                "\n   Score: " + battleEvent.getScore();
    }

    @Override
    public String toString() {
        return "Player{" +
                "staticdataTs=" + staticdataTs +
                ", ts=" + ts +
                ", pinfo=" + pinfo +
                '}';
    }
}
