package org.leevilaune.questland.api.models.staticdata;

import java.util.List;

public class MilestoneModel {

    private int id;
    private List<RewardModel> rewards;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<RewardModel> getRewards() {
        return rewards;
    }

    public void setRewards(List<RewardModel> rewards) {
        this.rewards = rewards;
    }

    @Override
    public String toString() {
        return "MilestoneModel{" +
                "id=" + id +
                ", rewards=" + rewards +
                '}';
    }
}
