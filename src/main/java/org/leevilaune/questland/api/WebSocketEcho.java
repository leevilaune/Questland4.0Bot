package org.leevilaune.questland.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.leevilaune.questland.api.models.Deserialization;
import org.leevilaune.questland.api.models.player.Player;
import org.leevilaune.questland.api.models.requests.PlayerRequest;

public final class WebSocketEcho extends WebSocketListener {

    private PlayerRequest request;
    private List<String> completed;
    private List<PlayerRequest> requests;
    private String guildResponse;

    private int playerID;

    public WebSocketEcho() {
        this.completed = new ArrayList<>();
        this.requests = new ArrayList<>();
    }

    public void setRequest(PlayerRequest request) {
        this.request = request;
    }

    public List<String> getCompleted() {
        return completed;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public List<PlayerRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<PlayerRequest> requests) {
        this.requests = requests;
    }

    public void run() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(0,  TimeUnit.MILLISECONDS)
                .build();

        Request request = new Request.Builder()
                .url("wss://prod.ql-api-gamesture.com/ws")
                .build();
        client.newWebSocket(request, this);

        // Trigger shutdown of the dispatcher's executor so this process can exit cleanly.
        client.dispatcher().executorService().shutdown();
    }

    @Override public void onOpen(WebSocket webSocket, Response response) {
        int req_id = 0;
        String version = "4.11.2.3896";
        String guild = "{\n" +
                "   \"req_id\":0,\n" +
                "   \"platform\":\"android\",\n" +
                "   \"guild_id\":202597,\n" +
                "   \"version\":\"4.11.4.3907\",\n" +
                "   \"token\":\"9d5ccbae75c5d35c6a56f78d3855df9a\",\n" +
                "   \"lang\":\"en\",\n" +
                "   \"task\":\"logged/guild/getguild\"\n" +
                "}";
        //String json = "{\"req_id\":0,\"platform\":\"android\",\"type\":\"battle_event\",\"ge_kind\":\"\",\"version\":\"4.11.2.3896\",\"token\":\"93da0c04a1e3e43e3c5bc512416a39cc\",\"lang\":\"en\",\"task\":\"logged/ranking/get\"}";
        //String json = "{\"req_id\":6,\"platform\":\"android\",\"device_id\":\"623300eac3c7d72c85fdd22ade212e81\",\"phone_model\":\"OnePlus ONEPLUS A5000\",\"ntoken\":\"\",\"version\":\"4.11.2.3896\",\"token\":\"9d5ccbae75c5d35c6a56f78d3855df9a\",\"lang\":\"en\",\"task\":\"logged/player/init\"}"
        //String profile = "{\"req_id\":0,\"platform\":\"android\",\"player_id\":3915660,\"version\":\"4.11.3.3899\",\"token\":\"9d5ccbae75c5d35c6a56f78d3855df9a\",\"lang\":\"en\",\"task\":\"logged/player/getprofile\"}";
        String ranking = "{\"req_id\":0,\"platform\":\"android\",\"type\":\"heropower\",\"ge_kind\":\"\",\"version\":\"4.11.3.3899\",\"token\":\"9d5ccbae75c5d35c6a56f78d3855df9a\",\"lang\":\"en\",\"task\":\"logged/ranking/get\"}";
        //getprofile
        String profile = null;
        //webSocket.send("{\"req_id\":15,\"platform\":\"android\",\"version\":\"4.11.3.3899\",\"token\":\"9d5ccbae75c5d35c6a56f78d3855df9a\",\"lang\":\"en\",\"task\":\"logged/player/idleclaim\"}");
        //be ranking
        //webSocket.send("{\"req_id\":0,\"platform\":\"android\",\"type\":\"battle_event\",\"ge_kind\":\"\",\"version\":\"4.11.3.3899\",\"token\":\"9d5ccbae75c5d35c6a56f78d3855df9a\",\"lang\":\"en\",\"task\":\"logged/ranking/get\"}");
        //webSocket.send(guild);
        try {
            webSocket.send(new ObjectMapper().writeValueAsString(request));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override public void onMessage(WebSocket webSocket, String text) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if(!text.contains("pinfo") || text.contains("messages")){
            return;
        }
        System.out.println("MESSAGE: " + text);
        completed.add(text);
    }

    @Override public void onMessage(WebSocket webSocket, ByteString bytes) {
        System.out.println("MESSAGE: " + bytes.hex());
    }

    @Override public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(1000, null);
        System.out.println("CLOSE: " + code + " " + reason);
    }

    @Override public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        t.printStackTrace();
    }
}