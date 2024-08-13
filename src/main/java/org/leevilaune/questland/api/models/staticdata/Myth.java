package org.leevilaune.questland.api.models.staticdata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Myth {

    @JsonProperty("attr")
    private String attribute;
    @JsonProperty("minp")
    private int minPotential;
    @JsonProperty("maxp")
    private int maxPotential;

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public int getMinPotential() {
        return minPotential;
    }

    public void setMinPotential(int minPotential) {
        this.minPotential = minPotential;
    }

    public int getMaxPotential() {
        return maxPotential;
    }

    public void setMaxPotential(int maxPotential) {
        this.maxPotential = maxPotential;
    }

    @Override
    public String toString() {
        return "Myth{" +
                "attribute='" + attribute + '\'' +
                ", minPotential=" + minPotential +
                ", maxPotential=" + maxPotential +
                '}';
    }
}
