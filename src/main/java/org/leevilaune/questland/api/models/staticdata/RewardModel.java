package org.leevilaune.questland.api.models.staticdata;

public class RewardModel {

    private int requiredPoints;
    private String item;
    private int amount;

    public int getRequiredPoints() {
        return requiredPoints;
    }

    public void setRequiredPoints(int requiredPoints) {
        this.requiredPoints = requiredPoints;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "RewardModel{" +
                "requiredPoints=" + requiredPoints +
                ", item='" + item + '\'' +
                ", amount=" + amount +
                '}';
    }
}
