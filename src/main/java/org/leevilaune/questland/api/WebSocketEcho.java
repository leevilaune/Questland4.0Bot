package org.leevilaune.questland;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public final class WebSocketEcho extends WebSocketListener {
    private void run() {
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
                "   \"guild_id\":200155,\n" +
                "   \"version\":\"4.11.2.3896\",\n" +
                "   \"token\":\"9d5ccbae75c5d35c6a56f78d3855df9a\",\n" +
                "   \"lang\":\"en\",\n" +
                "   \"task\":\"logged/guild/getguild\"\n" +
                "}";
        //String json = "{\"req_id\":0,\"platform\":\"android\",\"type\":\"battle_event\",\"ge_kind\":\"\",\"version\":\"4.11.2.3896\",\"token\":\"93da0c04a1e3e43e3c5bc512416a39cc\",\"lang\":\"en\",\"task\":\"logged/ranking/get\"}";
        //String json = "{\"req_id\":6,\"platform\":\"android\",\"device_id\":\"623300eac3c7d72c85fdd22ade212e81\",\"phone_model\":\"OnePlus ONEPLUS A5000\",\"ntoken\":\"\",\"version\":\"4.11.2.3896\",\"token\":\"9d5ccbae75c5d35c6a56f78d3855df9a\",\"lang\":\"en\",\"task\":\"logged/player/init\"}";
        //getprofile
        String profile = "{\"req_id\":22,\"platform\":\"android\",\"player_id\":10726993,\"version\":\"4.11.3.3899\",\"token\":\"9d5ccbae75c5d35c6a56f78d3855df9a\",\"lang\":\"en\",\"task\":\"logged/player/getprofile\"}";
        webSocket.send(guild);
        webSocket.close(1000,null);
    }

    @Override public void onMessage(WebSocket webSocket, String text) {
        System.out.println("MESSAGE: " + text);
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