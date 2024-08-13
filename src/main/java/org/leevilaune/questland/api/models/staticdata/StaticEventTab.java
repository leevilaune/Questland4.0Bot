package org.leevilaune.questland.api.models.staticdata;

import java.util.HashMap;

public class StaticEventTab {

    private String name;
    //key = taskID, value = questPoints
    private long startTS;
    private long endTS;
    private HashMap<Integer,Integer> tasks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public HashMap<Integer, Integer> getTasks() {
        return tasks;
    }

    public void setTasks(HashMap<Integer, Integer> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "StaticEventTab{" +
                "name='" + name + '\'' +
                ", startTS=" + startTS +
                ", endTS=" + endTS +
                ", tasks=" + tasks +
                '}';
    }
}
