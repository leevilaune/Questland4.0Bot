package org.leevilaune.questland.api.models.guild;

public class AcademyCostItem {

    private int level;
    private int resources;
    private int writs;

    public AcademyCostItem(int level, int resources, int writs) {
        this.level = level;
        this.resources = resources;
        this.writs = writs;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }

    public int getWrits() {
        return writs;
    }

    public void setWrits(int writs) {
        this.writs = writs;
    }
}
