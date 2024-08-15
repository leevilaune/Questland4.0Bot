package org.leevilaune.questland.api.requests.staticdata;

import org.leevilaune.questland.api.requests.Request;

import java.time.Instant;

public class StaticDataRequest extends Request {

    private long ts;

    public StaticDataRequest(int reqID,String token,String version){
        super(token,reqID,"android",version,"unlogged/getstaticdata","us");
        this.ts = Instant.now().getEpochSecond();
    }
}
