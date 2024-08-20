package org.leevilaune.questland.api.models.staticdata;

public class StaticReward {

    private int requiredPoints;
    private int item;
    private int amount;

    public int getRequiredPoints() {
        return requiredPoints;
    }

    public void setRequiredPoints(int requiredPoints) {
        this.requiredPoints = requiredPoints;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
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
        return "StaticReward{" +
                "requiredPoints=" + requiredPoints +
                ", item=" + item +
                ", amount=" + amount +
                '}';
    }
}
