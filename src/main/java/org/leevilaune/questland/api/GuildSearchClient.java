package org.leevilaune.questland.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.leevilaune.questland.api.models.Deserialization;
import org.leevilaune.questland.api.models.guild.Guild;
import org.leevilaune.questland.api.models.guild.GuildInfo;
import org.leevilaune.questland.api.models.guild.GuildPInfo;
import org.leevilaune.questland.api.models.guild.GuildsList;

import java.util.concurrent.TimeUnit;

public class GuildSearchClient extends WebSocketListener {

    //{"req_id":15,"platform":"android","name":"enjoying military","country":"","level":0,"req_level":0,"req_heropower":0,"req_vip":0,"not_full":1,"recommended":0,"version":"4.11.5.3912","token":"9d5ccbae75c5d35c6a56f78d3855df9a","lang":"en","task":"logged/guild/searchguild"}
    private Guild returnedGuild;
    private String returnedJson;
    private String searchRequest;
    private ObjectMapper mapper;
    private Deserialization deserialization;
    private GuildClient guildClient;
    public GuildSearchClient(GuildClient guildClient) {
        super();
        this.deserialization = new Deserialization();
        this.returnedGuild = new Guild();
        this.guildClient = guildClient;
        this.mapper = new ObjectMapper();
        setMapperConfigurations();
    }

    public Guild getGuild(String name) throws Exception{

        searchRequest = " {\"req_id\":0,\"platform\":\"android\",\"name\":\""+name+"\",\"country\":\"\",\"level\":0,\"req_level\":0,\"req_heropower\":0,\"req_vip\":0,\"not_full\":0,\"recommended\":0,\"version\":\"4.11.5.3912\",\"token\":\"9d5ccbae75c5d35c6a56f78d3855df9a\",\"lang\":\"en\",\"task\":\"logged/guild/searchguild\"}";
        System.out.println(searchRequest);
        run();
        Thread.sleep(2000);
        if(returnedJson == null){
            return new Guild();
        }
        System.out.println(returnedJson);
        JsonNode node = mapper.readerFor(JsonNode.class).readValue(returnedJson);
        if(node.get("guilds_search_indices").isEmpty()){
            return null;
        }
        Guild guild = guildClient.getGuild(Integer.parseInt(node.get("guilds_list").fieldNames().next()));
        return guild;

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
            webSocket.send(searchRequest);
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
