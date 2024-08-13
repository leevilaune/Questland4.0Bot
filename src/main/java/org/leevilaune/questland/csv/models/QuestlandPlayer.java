package org.leevilaune.questland.csv.models;

public class QuestlandPlayer {

    private int id;
    private String name;
    private String guildRank;
    private String cc;
    private String guildName;
    private int fame;
    private int attack;
    private int health;
    private int defence;
    private int magic;
    private int power;
    private double multi;
    private int score;
    private int bloodlust;
    private int chillingCold;
    private int bmr;

    public QuestlandPlayer(int id, String name, String guildRank,String cc, String guildName, int fame, int attack, int health, int defence, int magic, int power,double multi, int score, int bloodlust, int chillingCold, int bmr) {
        this.id = id;
        this.name = name;
        this.guildRank = guildRank;
        this.cc = cc;
        this.guildName = guildName;
        this.fame = fame;
        this.attack = attack;
        this.health = health;
        this.defence = defence;
        this.magic = magic;
        this.power = power;
        this.multi = multi;
        this.score = score;
        this.bloodlust = bloodlust;
        this.chillingCold = chillingCold;
        this.bmr = bmr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuildRank() {
        return guildRank;
    }

    public void setGuildRank(String guildRank) {
        this.guildRank = guildRank;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public int getFame() {
        return fame;
    }

    public void setFame(int fame) {
        this.fame = fame;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public double getMulti() {
        return multi;
    }

    public void setMulti(double multi) {
        this.multi = multi;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getBloodlust() {
        return bloodlust;
    }

    public void setBloodlust(int bloodlust) {
        this.bloodlust = bloodlust;
    }

    public int getChillingCold() {
        return chillingCold;
    }

    public void setChillingCold(int chillingCold) {
        this.chillingCold = chillingCold;
    }

    public int getBmr() {
        return bmr;
    }

    public void setBmr(int bmr) {
        this.bmr = bmr;
    }

    @Override
    public String toString() {
        return "QuestlandPlayer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", attack=" + attack +
                ", health=" + health +
                ", defence=" + defence +
                ", magic=" + magic +
                ", multi=" + multi +
                ", score=" + score +
                '}';
    }
}
