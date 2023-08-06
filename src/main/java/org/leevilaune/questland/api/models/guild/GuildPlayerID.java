package org.leevilaune.questland.api.models.guild;

public class GuildPlayerID {

    private GuildPlayer p;

    public GuildPlayer getP() {
        return p;
    }

    public void setP(GuildPlayer p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return "GuildPlayerID{" +
                "p=" + p +
                '}';
    }
}
