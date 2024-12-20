package org.leevilaune.questland.api.requests.staticdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.leevilaune.questland.api.requests.Request;

import java.time.Instant;

public class StaticDataRequest extends Request {

    //@JsonProperty("ts")
    //private long ts;

    public StaticDataRequest(int reqID,String token,String version){
        super(token,reqID,"android",version,"unlogged/getstaticdata","us");
        //this.ts = Instant.now().getEpochSecond();
    }
}
