package org.leevilaune.questland.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.leevilaune.questland.api.requests.boss.ChallengeGetInfoRequest;

public class BossClient {

    private String token,version;
    private WebSocketClient webSocketClient;
    private ObjectMapper mapper;

    public BossClient(String token,String version, WebSocketClient webSocketClient){
        this.token = token;
        this.version = version;
        this.webSocketClient = webSocketClient;
        this.mapper = new ObjectMapper();

        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void getBoss() throws JsonProcessingException {
        System.out.println(this.webSocketClient.sendRequest(new ChallengeGetInfoRequest(0,token,version)));
    }
}
