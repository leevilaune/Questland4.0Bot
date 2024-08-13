package org.leevilaune.questland.api.models.staticdata;

import java.util.List;

public class GearModel extends ItemModel{

    private int id;
    private String name;
    private String quality;
    private String slot;
    private String emblem;
    private int health;
    private int healthPotential;
    private int attack;
    private int attackPotential;
    private int defence;
    private int defencePotential;
    private int magic;
    private int magicPotential;
    private List<Integer> gearLinks;
    private String gearLinkType;
    private List<Integer> orbLinks;
    private String orbLinkType;
    private List<String> urls;

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

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getEmblem() {
        return emblem;
    }

    public void setEmblem(String emblem) {
        this.emblem = emblem;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealthPotential() {
        return healthPotential;
    }

    public void setHealthPotential(int healthPotential) {
        this.healthPotential = healthPotential;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getAttackPotential() {
        return attackPotential;
    }

    public void setAttackPotential(int attackPotential) {
        this.attackPotential = attackPotential;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getDefencePotential() {
        return defencePotential;
    }

    public void setDefencePotential(int defencePotential) {
        this.defencePotential = defencePotential;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getMagicPotential() {
        return magicPotential;
    }

    public void setMagicPotential(int magicPotential) {
        this.magicPotential = magicPotential;
    }

    public List<Integer> getGearLinks() {
        return gearLinks;
    }

    public void setGearLinks(List<Integer> gearLinks) {
        this.gearLinks = gearLinks;
    }

    public String getGearLinkType() {
        return gearLinkType;
    }

    public void setGearLinkType(String gearLinkType) {
        this.gearLinkType = gearLinkType;
    }

    public List<Integer> getOrbLinks() {
        return orbLinks;
    }

    public void setOrbLinks(List<Integer> orbLinks) {
        this.orbLinks = orbLinks;
    }

    public String getOrbLinkType() {
        return orbLinkType;
    }

    public void setOrbLinkType(String orbLinkType) {
        this.orbLinkType = orbLinkType;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public int getPotential(){
        return this.attackPotential+this.defencePotential+this.healthPotential+this.magicPotential;
    }

    public String csv() {
        String csv = "";
                if(this.emblem!=null){
                    csv += this.emblem.toUpperCase() + ",";
                }else{
                    csv += "NONE,";
                }
                csv += this.name.toUpperCase() + ",";
                if(this.gearLinkType != null){
                    csv += this.gearLinkType.toUpperCase() + ",";
                }else{
                    csv += ",";
                }
                csv += this.slot.toUpperCase() + "," +
                this.getPotential() + "," +
                this.health + "," +
                this.attack + "," +
                this.defence + "," +
                this.magic + ",";
                if (this.gearLinks!=null) {
                    if (this.gearLinks.size()>=2) {
                        csv += this.gearLinks.get(0) + "," +
                        this.gearLinks.get(1) + ",";
                    }else {
                        csv += ",,";
                    }
                    if(this.gearLinks.size() == 3){
                        csv += this.gearLinks.get(2) + ",";
                    }else{
                        csv += ",";
                    }
                }else{
                    csv += ",,,";
                }
                csv+=this.urls.get(0)+ "," +
                this.urls.get(1)+"," +
                this.id;


        return csv;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quality='" + quality + '\'' +
                ", slot='" + slot + '\'' +
                ", emblem='" + emblem + '\'' +
                ", health=" + health +
                ", healthPotential=" + healthPotential +
                ", attack=" + attack +
                ", attackPotential=" + attackPotential +
                ", defence=" + defence +
                ", defencePotential=" + defencePotential +
                ", magic=" + magic +
                ", magicPotential=" + magicPotential +
                ", gearLinks=" + gearLinks +
                ", gearLinkType='" + gearLinkType + '\'' +
                ", orbLinks=" + orbLinks +
                ", orbLinkType='" + orbLinkType + '\'' +
                ", urls=" + urls +
                '}';
    }
}
