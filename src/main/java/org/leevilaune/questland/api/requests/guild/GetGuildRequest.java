package org.leevilaune.questland.api.requests.guild;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.leevilaune.questland.api.requests.Request;

public class GetGuildRequest extends Request {

    @JsonProperty("guild_id")
    private int guildID;

    public GetGuildRequest(int reqID, String token, String version, int guildID){
        super(token,reqID,"android",version,"logged/guild/getguild","us");
        this.guildID = guildID;

    }
}
