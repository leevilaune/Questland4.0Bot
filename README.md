# Questland API

## 01 Setting up on Java

### 1.1 Creating WebSocketClient

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

### 1.2 Creating Request models

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
### 1.3 Sending Requests

So far we have created a way to make requests to the server and modeled the request into class. Next up is actually sending the request. 
Since we convert the request to json in `WebSocketClient.sendRequest()` we dont have to worry about that here. How ever we do need 
player token and game version, lets create `apiValues.csv`

```csv
token,version
```
To use our `GetProfileRequest` we need 4 parameters, rest are handled in constructor
```
reqID     = request id, index of request during this connection
token     = player token, account authentication token, we'll go
            into how to obtain it later
version   = game version, can be found in game settings and the api
            will return the required version if you try to use old
            version
playerID  = can be found in ingame settings for yourself, and there are
            ways to find it with the api, which we'll go into later
```
Now finally lets create our `Main` class and send a request to the server. 
```java
import project.path.WebSocketClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws Exception {

        String apiValues = Files.readString(Path.of("src/main/resources/apiValues.csv"));
        String[] values = apiValues.split(",");
        String token = values[0];
        String version = values[1];

        ObjectMapper mapper = new ObjectMapper();

        WebSocketClient client = new WebSocketClient();
        System.out.println(client.sendRequest(new GetProfileRequest(0,token,version,11987825)));
    }
}
```

If we did everything correctly, we should get monstrosity of a json return.

## 02 Most common data request

### 2.1 Guild by Id
To get guild by id we need to send `getguild`-request.
```json
{
    "req_id" : int,
    "platform" : string,
    "guild_id" : int,
    "version" : string,
    "token" : string,
    "lang" : string
    "task" : "logged/guild/getguild"
}
```
As java model it would be
```java
import com.fasterxml.jackson.annotation.JsonProperty;
import project.path.Request;

public class GetGuildRequest extends Request {

    @JsonProperty("guild_id")
    private int guildID;

    public GetGuildRequest(int reqID, String token, String version, int guildID){
        super(token,reqID,"android",version,"logged/guild/getguild","us");
        this.guildID = guildID;

    }
}
```
