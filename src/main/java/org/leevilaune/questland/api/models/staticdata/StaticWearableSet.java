package org.leevilaune.questland.api.models.staticdata;

public class StaticWearableSet {

    private int id;
    private int name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StaticWearableSet{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
