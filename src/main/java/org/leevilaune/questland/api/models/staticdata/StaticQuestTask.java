package org.leevilaune.questland.api.models.staticdata;

import java.util.*;

public class StaticQuestTask {

    private int id;
    private List<Integer> task;
    private int item;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getTask() {
        return task;
    }

    public void setTask(List<Integer> task) {
        this.task = task;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public String getTaskString(List<StaticClientString> clientStrings){
        String taskString = "";
        for(int i : this.getTask()){
            taskString+=" "+clientStrings.stream().filter(c->c.getId()==i).findFirst().get().getEn();
        }
        return taskString;
    }

    @Override
    public String toString() {
        return "StaticQuestTask{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", item=" + item +
                '}';
    }
}
