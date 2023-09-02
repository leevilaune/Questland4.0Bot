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

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class GuildClient extends WebSocketListener {

    private Guild returnedGuild;
    private String returnedJson;
    private String guildRequest;
    private ObjectMapper mapper;
    private Deserialization deserialization;
    public GuildClient() {
        super();
        this.deserialization = new Deserialization();
        this.returnedGuild = new Guild();
        this.mapper = new ObjectMapper();
        setMapperConfigurations();
    }

    public Guild getGuild(int id) throws Exception{
        guildRequest = "{\n" +
                "   \"req_id\":0,\n" +
                "   \"platform\":\"android\",\n" +
                "   \"guild_id\":"+id+",\n" +
                "   \"version\":\"4.11.5.3912\",\n" +
                "   \"token\":\"9d5ccbae75c5d35c6a56f78d3855df9a\",\n" +
                "   \"lang\":\"en\",\n" +
                "   \"task\":\"logged/guild/getguild\"\n" +
                "}";
        run();
        Thread.sleep(1000);
        if(returnedJson == null){
            return new Guild();
        }
        Guild guild = mapper.readerFor(Guild.class).readValue(deserialization.reformat(id,"guild_info", returnedJson));
        guild.getpInfo().setGuildPlayers(deserialization.deserializeGuildPlayers(returnedJson));
        return guild;

    }

    //"{"req_id":17,"platform":"android","type":"global_guild","ge_kind":"","version":"4.11.5.3912","token":"9d5ccbae75c5d35c6a56f78d3855df9a","lang":"en","task":"logged/ranking/get"}";
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
            webSocket.send(guildRequest);
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
        if(!text.contains("pinfo") || text.contains("messages")){
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