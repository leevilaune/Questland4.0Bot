package org.leevilaune.questland.api.models.player;

public class BattleEvent {

    private int score;
    private int multiplier;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public String toString() {
        return "BattleEvent{" +
                "score=" + score +
                ", multiplier=" + multiplier +
                '}';
    }
}
