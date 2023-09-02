package org.leevilaune.questland.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.leevilaune.questland.api.models.Deserialization;
import org.leevilaune.questland.api.models.guild.Guild;
import org.leevilaune.questland.api.models.player.P;
import org.leevilaune.questland.api.models.player.Player;

import java.util.concurrent.TimeUnit;

public class PlayerClient extends WebSocketListener {

    private String returnedJson;
    private String playerRequest;
    private ObjectMapper mapper;
    private Deserialization deserialization;
    private GuildSearchClient guildSearchClient;
    public PlayerClient(GuildSearchClient guildSearchClient) {
        super();
        this.deserialization = new Deserialization();
        this.mapper = new ObjectMapper();
        this.guildSearchClient = guildSearchClient;
        setMapperConfigurations();
    }

    public Player getPlayer(String name, String guildName) throws Exception{

        Guild g = guildSearchClient.getGuild(guildName);
        if(g == null){
            return null;
        }
        Thread.sleep(2000);
        if(g.getpInfo().getGuildPlayers().stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().get() == null){
            return null;
        }
        int id = g.getpInfo().getGuildPlayers().stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().get().getId();
        playerRequest = "{\"req_id\":0,\"platform\":\"android\",\"player_id\":"+id+",\"version\":\"4.11.5.3912\",\"token\":\"9d5ccbae75c5d35c6a56f78d3855df9a\",\"lang\":\"en\",\"task\":\"logged/player/getprofile\"}";
        run();
        Thread.sleep(2000);
        if(returnedJson == null){
            return null;
        }
        Player player = mapper.readerFor(Player.class).readValue(deserialization.reformat(id,"player_info",returnedJson));
        return player;

    }
    public Player getPlayer(String name, Guild guild) throws Exception{

        int id = guild.getpInfo().getGuildPlayers().stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().get().getId();
        playerRequest = "{\"req_id\":0,\"platform\":\"android\",\"player_id\":"+id+",\"version\":\"4.11.5.3912\",\"token\":\"9d5ccbae75c5d35c6a56f78d3855df9a\",\"lang\":\"en\",\"task\":\"logged/player/getprofile\"}";
        run();
        Thread.sleep(2000);
        if(returnedJson == null){
            return new Player();
        }
        Player player = mapper.readerFor(Player.class).readValue(deserialization.reformat(id,"player_info",returnedJson));
        return player;

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
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        super.onOpen(webSocket, response);
        try{
            webSocket.send(playerRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
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
}
