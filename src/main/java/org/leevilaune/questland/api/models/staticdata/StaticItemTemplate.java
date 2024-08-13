package org.leevilaune.questland.api.models.staticdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class StaticItemTemplate {

    private int id;
    @JsonProperty("stats")
    private Stats stats;
    @JsonProperty("s")
    private String slot;
    @JsonProperty("q")
    private String quality;
    @JsonProperty("set")
    private int set;
    @JsonProperty("n")
    private int name;
    @JsonProperty("links")
    private List<StaticLinks> links;
    private int itemGraphic;
    private int itemGraphicSd;
    private int itemGraphicHd;
    private int itemGraphicR;
    private int itemGraphicPreview;

    public StaticItemTemplate(){
        this.id = 0;
        this.slot = "";
        this.quality = "";
        this.set = 0;
        this.name = 0;
        this.links = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public List<StaticLinks> getLinks() {
        return links;
    }

    public void setLinks(List<StaticLinks> links) {
        this.links = links;
    }

    public int getItemGraphic() {
        return itemGraphic;
    }

    public void setItemGraphic(int itemGraphic) {
        this.itemGraphic = itemGraphic;
    }

    public int getItemGraphicSd() {
        return itemGraphicSd;
    }

    public void setItemGraphicSd(int itemGraphicSd) {
        this.itemGraphicSd = itemGraphicSd;
    }

    public int getItemGraphicHd() {
        return itemGraphicHd;
    }

    public void setItemGraphicHd(int itemGraphicHd) {
        this.itemGraphicHd = itemGraphicHd;
    }

    public int getItemGraphicR() {
        return itemGraphicR;
    }

    public void setItemGraphicR(int itemGraphicR) {
        this.itemGraphicR = itemGraphicR;
    }

    public int getItemGraphicPreview() {
        return itemGraphicPreview;
    }

    public void setItemGraphicPreview(int itemGraphicPreview) {
        this.itemGraphicPreview = itemGraphicPreview;
    }

    public String csv(){
        return this.getId()+","+
                this.getStats().getAttack().get(0)+","+
                this.getStats().getDefence().get(0)+","+
                this.getStats().getHealth().get(0)+","+
                this.getStats().getMagic().get(0)+","+
                this.getQuality()+","+
                this.getSlot()+","+
                this.getSet();
    }

    @Override
    public String toString() {
        return "StaticItemTemplate{" +
                "id=" + id +
                ", stats=" + stats +
                ", slot='" + slot + '\'' +
                ", quality='" + quality + '\'' +
                ", set=" + set +
                ", name=" + name +
                ", links=" + links +
                ", itemGraphic=" + itemGraphic +
                ", itemGraphicSd=" + itemGraphicSd +
                ", itemGraphicHd=" + itemGraphicHd +
                ", itemGraphicR=" + itemGraphicR +
                ", itemGraphicPreview=" + itemGraphicPreview +
                '}';
    }
}
