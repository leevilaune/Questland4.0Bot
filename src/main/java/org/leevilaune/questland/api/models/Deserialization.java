package org.leevilaune.questland.api.models.requests;

public class Deserialization {

    public String reformat(int id,String replacement, String json){

        String newJson = json.replace("\""+id+"\":{","\""+replacement+"\":{");

        return newJson;
    }
}
