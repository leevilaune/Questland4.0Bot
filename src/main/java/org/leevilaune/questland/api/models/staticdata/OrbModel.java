package org.leevilaune.questland.api.models.staticdata;

import java.util.List;

public class OrbModel extends ItemModel{

    private int id;
    private String name;
    private String quality;
    private String slot;
    private Stats stats;
    private Myth myth;
    private List<Integer> links;
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

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Myth getMyth() {
        return myth;
    }

    public void setMyth(Myth myth) {
        this.myth = myth;
    }

    public List<Integer> getLinks() {
        return links;
    }

    public void setLinks(List<Integer> links) {
        this.links = links;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "OrbModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quality='" + quality + '\'' +
                ", slot='" + slot + '\'' +
                ", stats=" + stats +
                ", myth=" + myth +
                ", links=" + links +
                ", urls=" + urls +
                '}';
    }
}
