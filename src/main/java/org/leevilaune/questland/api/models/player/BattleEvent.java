package org.leevilaune.questland.api.models.player;

public class BattleEvent {

    private int score;
    private double multiplier;

    private String firstBonus;
    private String thirdBonus;
    private String fourthBonus;
    private int orbBonus;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public String getFirstBonus() {
        return firstBonus;
    }

    public void setFirstBonus(String firstBonus) {
        this.firstBonus = firstBonus;
    }

    public String getThirdBonus() {
        return thirdBonus;
    }

    public void setThirdBonus(String thirdBonus) {
        this.thirdBonus = thirdBonus;
    }

    public String getFourthBonus() {
        return fourthBonus;
    }

    public void setFourthBonus(String fourthBonus) {
        this.fourthBonus = fourthBonus;
    }

    public int getOrbBonus() {
        return orbBonus;
    }

    public void setOrbBonus(int orbBonus) {
        this.orbBonus = orbBonus;
    }

    @Override
    public String toString() {
        return "BattleEvent{" +
                "score=" + score +
                ", multiplier=" + multiplier +
                ", firstBonus='" + firstBonus + '\'' +
                ", thirdBonus='" + thirdBonus + '\'' +
                ", fourthBonus='" + fourthBonus + '\'' +
                ", orbBonus=" + orbBonus +
                '}';
    }
}
