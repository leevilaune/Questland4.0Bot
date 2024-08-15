package org.leevilaune.questland.api.requests.guild;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.leevilaune.questland.api.requests.Request;

public class SearchGuildRequest extends Request {

    @JsonProperty("name")
    private String name;
    @JsonProperty("country")
    private String country;
    @JsonProperty("level")
    private int level;
    @JsonProperty("req_level")
    private int redLevel;
    @JsonProperty("req_heropower")
    private int reqHeroPower;
    @JsonProperty("req_vip")
    private int reqVip;
    @JsonProperty("not_full")
    private int notFull;
    @JsonProperty("recommended")
    private int recommended;


    public SearchGuildRequest(int reqID, String token,String version,String name){
        super(token,reqID,"android",version,"logged/guild/searchguild","us");
        this.name = name;
    }
}
