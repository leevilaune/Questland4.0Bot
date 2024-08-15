package org.leevilaune.questland.api.requests.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.leevilaune.questland.api.requests.Request;

public class GetProfileRequest extends Request {

    @JsonProperty("player_id")
    private int playerID;

    public GetProfileRequest(int reqID,String token,String version, int playerID){
        super(token,reqID,"android",version,"logged/player/getprofile","us");
        this.playerID = playerID;
    }
}