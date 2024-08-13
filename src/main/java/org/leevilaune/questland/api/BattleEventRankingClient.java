package org.leevilaune.questland.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.leevilaune.questland.api.models.beranking.BattleEventRanking;
import org.leevilaune.questland.api.models.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BattleEventRankingClient extends WebSocketListener {

    private String returnedJson;
    private String searchRequest;
    private ObjectMapper mapper;
    private PlayerClient playerClient;

    public BattleEventRankingClient(PlayerClient playerClient){
        this.mapper = new ObjectMapper();
        this.playerClient = playerClient;
        //setMapperConfigurations();
    }

    public void getBattleEventRanking() throws Exception {
        searchRequest = "{\"req_id\":0,\"platform\":\"android\",\"type\":\"battle_event\",\"ge_kind\":\"\",\"version\":\"4.20.0.4085\",\"token\":\"9d5ccbae75c5d35c6a56f78d3855df9a\",\"lang\":\"en\",\"task\":\"logged/ranking/get\"}";
        run();
        Thread.sleep(2000);
        System.out.println(returnedJson);

        BattleEventRanking ranking = mapper.readerFor(BattleEventRanking.class).readValue(returnedJson);

        int i = 0;
        List<String> ranks = new ArrayList<>();
        for(List<Integer> rank : ranking.getBattleEvent().getBattleEvent().getLadder()){
            Player p = playerClient.getPlayer(rank.get(1));

            try{
                String s = i+","+rank.get(0) + ","
                        + p.getPinfo().getPlayerInfo().getBattleEvent().getMultiplier() + ","
                        + p.getPinfo().getPlayerInfo().getP().getName() + ","
                        + p.getPinfo().getPlayerInfo().getP().getId();
                ranks.add(s);
                System.out.println(s);
            }catch (Exception e){
                System.out.println(rank);
            }

            if(i>100){
                break;
            }
            i++;
        }
        for (String s : ranks){
            System.out.println(s);
        }
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
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        if(text.contains("version_requred")){
            System.err.println(text);
        }
        //System.out.println(text);
        returnedJson = text;
        webSocket.close(1000,null);
    }
}
