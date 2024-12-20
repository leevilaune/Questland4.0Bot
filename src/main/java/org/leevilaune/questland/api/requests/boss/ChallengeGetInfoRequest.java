package org.leevilaune.questland.api.requests.boss;

import org.leevilaune.questland.api.requests.Request;

public class ChallengeGetInfoRequest extends Request {

    public ChallengeGetInfoRequest(int reqID, String token, String version){
        super(token,reqID,"android",version,"logged/boss/challengegetinfo","us");
    }
}
