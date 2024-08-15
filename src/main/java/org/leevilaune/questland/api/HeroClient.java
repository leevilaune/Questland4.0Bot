package org.leevilaune.questland.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.leevilaune.questland.api.models.Deserialization;
import org.leevilaune.questland.api.models.player.Player;
import org.leevilaune.questland.api.requests.player.GetProfileRequest;

import java.util.ArrayList;
import java.util.List;

public class HeroClient {

    private String token;
    private String version;
    private WebSocketClient webSocketClient;
    private int id;
    private ObjectMapper mapper;
    private Deserialization deserialization;

    public HeroClient(String token,String version, WebSocketClient webSocketClient){
        this.token = token;
        this.version = version;
        this.webSocketClient = webSocketClient;
        this.mapper = new ObjectMapper();
        this.deserialization = new Deserialization();

        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Player getPlayer(int id) throws JsonProcessingException {
        String node = this.webSocketClient.sendRequest(new GetProfileRequest(0,token,version,id));
        this.id = id;
        return mapper.readerFor(Player.class).readValue(deserialization.reformat(id,"player_info",node));
    }
    public List<Player> getMultiple(List<Integer> ids) throws Exception {
        List<Player> players = new ArrayList<>();
        int i = 0;
        for (int id : ids) {
            players.add(getPlayer(id));
            Thread.sleep(100);
            i++;
            if (i > 100) {
                break;
            }
        }
        return players;
    }
}
