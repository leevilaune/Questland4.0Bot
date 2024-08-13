package org.leevilaune.questland.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.leevilaune.questland.api.models.guildranking.GuildRanking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlayerSearchClient extends WebSocketListener {

    private WebSocket webSocket;
    private String token;
    private String version;
    private GuildRanking guildRanking;
    private ObjectMapper mapper;
    private List<Integer> ladder;

    public PlayerSearchClient(String token,String version){
        this.token = token;
        this.version = version;
        this.mapper = new ObjectMapper();
        this.ladder = new ArrayList<>();
    }

    public void getGuilds(){
        webSocket.send("{\"req_id\":0,\"platform\":\"android\",\"type\":\"global_guild\",\"ge_kind\":\"\",\"version\":\""+this.version+"\",\"token\":\""+this.token+"\",\"lang\":\"us\",\"task\":\"logged/ranking/get\"}");
    }

    private void getGuildMembers(){

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
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        this.webSocket = webSocket;
        super.onOpen(webSocket, response);
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        try {
            if(text.contains("version_requred")){
                System.err.println(text);
            }
            if(text.contains("messages")){
                return;
            }
            Thread.sleep(1000);
            System.out.println(text);
            this.guildRanking = mapper.readValue(text, GuildRanking.class);
            for(Integer[] ladder : this.guildRanking.getRanking().getGlobalGuild().getLadder()){
                this.ladder.add(ladder[1]);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        super.onMessage(webSocket, text);
    }
}
