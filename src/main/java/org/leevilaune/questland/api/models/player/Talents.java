package org.leevilaune.questland.api.models.player;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Talents {

    @JsonProperty("1")
    private List<List<Integer>> slot1;
    @JsonProperty("2")
    private List<List<Integer>> slot2;
    @JsonProperty("3")
    private List<List<Integer>> slot3;
    @JsonProperty("4")
    private List<List<Integer>> slot4;

    public List<List<Integer>> getSlot1() {
        return slot1;
    }

    public void setSlot1(List<List<Integer>> slot1) {
        this.slot1 = slot1;
    }

    public List<List<Integer>> getSlot2() {
        return slot2;
    }

    public void setSlot2(List<List<Integer>> slot2) {
        this.slot2 = slot2;
    }

    public List<List<Integer>> getSlot3() {
        return slot3;
    }

    public void setSlot3(List<List<Integer>> slot3) {
        this.slot3 = slot3;
    }

    public List<List<Integer>> getSlot4() {
        return slot4;
    }

    public void setSlot4(List<List<Integer>> slot4) {
        this.slot4 = slot4;
    }
}
