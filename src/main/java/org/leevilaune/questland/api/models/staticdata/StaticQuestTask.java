package org.leevilaune.questland.api.models.staticdata;

import java.util.*;

public class StaticQuestTask {

    private int id;
    private List<Integer> task;
    private int item;
    private int limit;

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

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getTaskString(List<StaticClientString> clientStrings, String lang){
        StringBuilder taskString = new StringBuilder();
        for(int i : this.getTask()){
            if(lang.equalsIgnoreCase("en")){
                taskString.append(" ").append(clientStrings.stream().filter(c -> c.getId() == i).findFirst().get().getEn());
            }
            if(lang.equalsIgnoreCase("it")){
                if(!clientStrings.stream().filter(c->c.getId()==i).findFirst().get().getIt().isEmpty()){
                    taskString.append(" ").append(clientStrings.stream().filter(c -> c.getId() == i).findFirst().get().getIt());
                }else{
                    taskString.append(" ").append(clientStrings.stream().filter(c -> c.getId() == i).findFirst().get().getEn());
                }
            }
        }
        return taskString.toString();
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
