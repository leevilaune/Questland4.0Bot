package org.leevilaune.questland;

import okhttp3.*;

import java.io.IOException;

public class HTTPClient {

    private OkHttpClient client;
    private String apiToken;
    private String baseUrl;

    public HTTPClient(String apiToken){
        this.client = new OkHttpClient();
        this.apiToken = apiToken;
        this.baseUrl = "wss://prod.ql-api-gamesture.com/ws";
    }
    protected Request.Builder getBaseRequest(){
        return new Request.Builder().url(baseUrl);
    }
    protected Response get(String url) throws IOException {
        return client.newCall(getBaseRequest().build()).execute();
    }
    protected Response post(String request) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),request);

        return client.newCall(getBaseRequest().post(body).build()).execute();
    }
}