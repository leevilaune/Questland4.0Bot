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
        String csv = "";
        csv+=this.id+",";
        csv+=this.name+",";
        csv+=this.slot+",";
        csv+=this.quality+",";
        if(this.urls.size()>0){
            for(String s : this.urls){
                csv+=s+",";
            }
        }
        return csv;
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
