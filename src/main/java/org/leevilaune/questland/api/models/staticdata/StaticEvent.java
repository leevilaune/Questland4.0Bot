package org.leevilaune.questland.api.models.staticdata;

import java.util.*;

public class StaticEvent {

    private int id;
    private int name;
    private long startTS;
    private long endTS;
    private int milestones;
    private List<StaticEventTab> tabs;

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

    public long getStartTS() {
        return startTS;
    }

    public void setStartTS(long startTS) {
        this.startTS = startTS;
    }

    public long getEndTS() {
        return endTS;
    }

    public void setEndTS(long endTS) {
        this.endTS = endTS;
    }

    public int getMilestones() {
        return milestones;
    }

    public void setMilestones(int milestones) {
        this.milestones = milestones;
    }

    public List<StaticEventTab> getTabs() {
        return tabs;
    }

    public void setTabs(List<StaticEventTab> tabs) {
        this.tabs = tabs;
    }

    @Override
    public String toString() {
        return "StaticEvent{" +
                "id=" + id +
                ", name=" + name +
                ", tabs=" + tabs +
                '}';
    }
}
