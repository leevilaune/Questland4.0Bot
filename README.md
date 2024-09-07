# Questland API

## Setting up

### Java

Using `okhttp3.WebSocketListener` you can send requests to the server. Lets create a `WebSocketClient` class that inherits `okhttp3.WebSocketListener`.
```java
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.TimeUnit;

public class WebSocketClient extends WebSocketListener {

    private WebSocket ws;
    private ObjectMapper mapper;
    private volatile boolean isReady;
    private String returnedJson;

    public WebSocketClient(){
        this.mapper = new ObjectMapper();
        this.isReady = false;
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

    public void close(){
        this.ws.close(1000,null);
    }

    private void handle(String json){
        this.returnedJson = json;
        this.isReady = true;
    }
    public String sendRequest(org.leevilaune.questland.api.requests.Request r) throws JsonProcessingException {
        this.ws.send(mapper.writeValueAsString(r));
        while(!isReady){
        }
        this.isReady = false;
        return returnedJson;
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        handle(text);
        super.onMessage(webSocket, text);
    }
}

```

Creating  `Request` class.

```java

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {

    @JsonProperty("token")
    protected String token;
    @JsonProperty("red_id")
    protected int reqId;
    @JsonProperty("platform")
    protected String platform;
    @JsonProperty("version")
    protected String version;
    @JsonProperty("task")
    protected String task;
    @JsonProperty("lang")
    protected String lang;

    public Request(String token, int reqId, String platform, String version, String task, String lang) {
        this.token = token;
        this.reqId = reqId;
        this.platform = platform;
        this.version = version;
        this.task = task;
        this.lang = lang;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
```
Now you have a base class for your request. Rest of the request classes will inherit `Request`. For example `GetProfileRequest` to get player data.
```java
import com.fasterxml.jackson.annotation.JsonProperty;
import project.path.Request;

public class GetProfileRequest extends Request {

    @JsonProperty("player_id")
    private int playerID;

    public GetProfileRequest(int reqID,String token,String version, int playerID){
        super(token,reqID,"android",version,"logged/player/getprofile","us");
        this.playerID = playerID;
    }
}
```

