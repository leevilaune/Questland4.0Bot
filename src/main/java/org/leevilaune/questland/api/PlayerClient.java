package org.leevilaune.questland.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.leevilaune.questland.api.models.Deserialization;
import org.leevilaune.questland.api.models.guild.Guild;
import org.leevilaune.questland.api.models.player.Player;
import org.leevilaune.questland.api.requests.player.GetProfileRequest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.List;

public class PlayerClient extends WebSocketListener {

    private String returnedJson;
    private String playerRequest;
    private ObjectMapper mapper;
    private Deserialization deserialization;
    private GuildSearchClient guildSearchClient;
    private String token,version;
    private WebSocket ws;
    private volatile boolean isReady;
    private Player player;
    private int id;

    private WebSocketClient webSocketClient;
    public PlayerClient(GuildSearchClient guildSearchClient,String token, String version, WebSocketClient webSocketClient) {
        super();
        this.deserialization = new Deserialization();
        this.mapper = new ObjectMapper();
        this.guildSearchClient = guildSearchClient;
        this.token = token;
        this.version = version;
        this.isReady = false;
        this.player = null;

        this.webSocketClient = webSocketClient;
        setMapperConfigurations();
    }

    public Player getPlayer(String name, String guildName) throws Exception{

        Guild g = guildSearchClient.getGuild(guildName);
        if(g == null){
            return null;
        }
        Thread.sleep(2000);
        if(g.getpInfo().getGuildPlayers().stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().get() == null){
            return new Player();
        }
        int id = g.getpInfo().getGuildPlayers().stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().get().getId();
        playerRequest = "{\"req_id\":0,\"platform\":\"android\",\"player_id\":"+id+",\"version\":\""+version+"\",\"token\":\""+token+"\",\"lang\":\"en\",\"task\":\"logged/player/getprofile\"}";
        run();
        Thread.sleep(2000);
        if(returnedJson == null){
            return null;
        }
        Player player = mapper.readerFor(Player.class).readValue(deserialization.reformat(id,"player_info",returnedJson));
        deserialization.deserializeOrbs(returnedJson,id);
        return player;

    }
    /*public Player getPlayer(int id) throws Exception{
        this.isReady = false;
        this.id = id;
        run();
        this.ws.send(mapper.writeValueAsString(new GetProfileRequest(0,token,version,id)));
        long t = Instant.now().getEpochSecond();
        while(this.isReady==false){
        }
        System.out.println(t-Instant.now().getEpochSecond());
        return this.player;
    }*/
    public Player getPlayer(String id) throws JsonProcessingException {
        String  node = this.webSocketClient.sendRequest(new GetProfileRequest(0,token,version,id));
        this.id = Integer.parseInt(id);
        return mapper.readerFor(Player.class).readValue(deserialization.reformat(this.id,"player_info",node.toString()));
    }
    public List<Player> getMultiple(List<Integer> ids) throws Exception {
        List<Player> players = new ArrayList<>();
        int i = 0;
        for(int id : ids){
            players.add(getPlayer(String.valueOf(id)));
            Thread.sleep(100);
            i++;
            if(i>100){
                break;
            }
        }
        return players;
    }
    public Player getPlayer(String name, Guild guild) throws Exception{

        int id = guild.getpInfo().getGuildPlayers().stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().get().getId();
        playerRequest = "{\"req_id\":0,\"platform\":\"android\",\"player_id\":"+id+",\"version\":\""+version+"\",\"token\":\""+token+"\",\"lang\":\"en\",\"task\":\"logged/player/getprofile\"}";
        run();
        Thread.sleep(2000);
        if(returnedJson == null){
            return new Player();
        }
        Player player = mapper.readerFor(Player.class).readValue(deserialization.reformat(id,"player_info",returnedJson));
        return player;

    }

    public void handle(String json){
        try {
            this.player = mapper.readerFor(Player.class).readValue(deserialization.reformat(this.id,"player_info",json));
            this.isReady = true;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(player);
        this.isReady = true;
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
        this.ws = client.newWebSocket(request, this);

        // Trigger shutdown of the dispatcher's executor so this process can exit cleanly.
        client.dispatcher().executorService().shutdown();
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        super.onOpen(webSocket, response);
        try{
            //webSocket.send(playerRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        System.out.println(reason);
        super.onClosed(webSocket, code, reason);
    }

    @Override
    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        System.out.println(reason);
        super.onClosing(webSocket, code, reason);
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
        System.out.println(response);
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
        handle(text);
        returnedJson = text;
        System.out.println(text);
        webSocket.close(1000,null);
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
        super.onMessage(webSocket, bytes);
    }
}
