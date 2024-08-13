package org.leevilaune.questland.api.models.staticdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StaticLinks {

    @JsonProperty("e")
    private String bonusStat;
    @JsonProperty("p")
    private int percentage;
    @JsonProperty("i")
    private List<List<Integer>> links;

    public String getBonusStat() {
        return bonusStat;
    }

    public void setBonusStat(String bonusStat) {
        this.bonusStat = bonusStat;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public List<List<Integer>> getLinks() {
        return links;
    }

    public void setLinks(List<List<Integer>> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "StaticLinks{" +
                "bonusStat='" + bonusStat + '\'' +
                ", percentage=" + percentage +
                ", links=" + links +
                '}';
    }
}
