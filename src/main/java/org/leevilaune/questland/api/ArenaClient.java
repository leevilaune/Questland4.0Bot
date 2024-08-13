package org.leevilaune.questland.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import okio.ByteString;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.leevilaune.questland.api.models.arena.ArenaRanking;

import java.util.concurrent.TimeUnit;

public class ArenaClient extends WebSocketListener {
    private ObjectMapper mapper;
    private String request;
    private String returnedJson;
    private String token;
    private String version;
    public ArenaClient(String token, String version) {
        super();
        mapper = new ObjectMapper();
        this.token = token;
        this.version = version;
    }

    public ArenaRanking getRanking() throws Exception{
        request = "{\"req_id\":0,\"platform\":\"android\",\"type\":\"pvp\",\"ge_kind\":\"\",\"version\":\""+version+"\",\"token\":\""+token+"\",\"lang\":\"en\",\"task\":\"logged/ranking/get\"}";
        run();
        Thread.sleep(2000);
        if(returnedJson == null){
            return null;
        }
        System.out.println(returnedJson);
        return new ArenaRanking();
    }
    private void setMapperConfigurations(){
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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

    @Override
    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        super.onClosed(webSocket, code, reason);
    }

    @Override
    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        super.onClosing(webSocket, code, reason);
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
        super.onFailure(webSocket, t, response);
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        if(text.contains("version_requred")){
            System.err.println(text);
        }
        if(text.contains("messages")){
            return;
        }
        returnedJson = text;
        webSocket.close(1000,null);
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
        super.onMessage(webSocket, bytes);
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        super.onOpen(webSocket, response);
        try{
            webSocket.send(request);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
