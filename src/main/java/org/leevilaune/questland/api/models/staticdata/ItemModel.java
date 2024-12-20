package org.leevilaune.questland.api.models.staticdata;

import java.util.List;

public class ItemModel {

    private int id;
    private String name;
    private String quality;
    private String slot;
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

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public String csv(){
        StringBuilder csv = new StringBuilder();
        csv.append(this.id).append(",");
        csv.append(this.name).append(",");
        csv.append(this.slot).append(",");
        csv.append(this.quality).append(",");
        if(!this.urls.isEmpty()){
            for(String s : this.urls){
                csv.append(s).append(",");
            }
        }
        return csv.toString();
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quality='" + quality + '\'' +
                ", slot='" + slot + '\'' +
                ", urls=" + urls +
                '}';
    }
}
