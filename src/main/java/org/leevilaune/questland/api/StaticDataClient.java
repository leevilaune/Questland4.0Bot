package org.leevilaune.questland.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.leevilaune.questland.Logger;
import org.leevilaune.questland.api.requests.staticdata.StaticDataRequest;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class StaticDataClient extends WebSocketListener {

    private String returnedJson;
    private ObjectMapper mapper;
    private String token,version;
    private Logger logger;
    private WebSocket ws;

    public StaticDataClient(String token,String version) {
        super();
        this.mapper = new ObjectMapper();
        this.token = token;
        this.version = version;
        this.logger = new Logger();
    }

    public void run() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(0,  TimeUnit.MILLISECONDS)
                .build();

        Request request = new Request.Builder()
                .url("wss://prod.ql-api-gamesture.com/ws")
                .build();
        this.ws = client.newWebSocket(request, this);

        // Trigger shutdown of the dispatcher's executor so this process can exit cleanly.
        client.dispatcher().executorService().shutdown();
    }

    public void getStaticData() throws JsonProcessingException {
        run();
        ws.send(mapper.writeValueAsString(new StaticDataRequest(0,this.token,this.version)));
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
        super.onMessage(webSocket, text);
        System.out.println(text);
        returnedJson = text;
        logger.log(text);
        try {
            JsonNode node = mapper.readValue(text, JsonNode.class);
            System.out.println("Static Data TS: "+Instant.ofEpochSecond(node.get("staticdata_ts").asLong()));
            System.out.println("Time Stamp    : "+Instant.ofEpochSecond(node.get("ts").asLong()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        webSocket.close(2000,null);
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        super.onOpen(webSocket, response);
    }
}
