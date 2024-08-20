package org.leevilaune.questland.api.models.staticdata;

import java.util.List;


public class StaticMilestone {

    private int id;
    private List<StaticReward> rewards;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<StaticReward> getRewards() {
        return rewards;
    }

    public void setRewards(List<StaticReward> rewards) {
        this.rewards = rewards;
    }

    @Override
    public String toString() {
        return "StaticMilestone{" +
                "id=" + id +
                ", rewards=" + rewards +
                '}';
    }
}
