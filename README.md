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

### 2.1 `logged/guild/getguild`
To get guild by id we need to send `getguild`-request. For this you need to know the id of
the guild youre trying to get.
As JSON:
```json
{
    "req_id" : 0,
    "platform" : "",
    "guild_id" : 0,
    "version" : "",
    "token" : "",
    "lang" : "",
    "task" : "logged/guild/getguild"
}
```
As Java model:
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
The return is a single guild json.

### 2.2 `logged/guild/getguild`
Searching guild you dont need to know the id of the guild.
As JSON:
```json
{
    "req_id" : 0,
    "platform" : "",
    "name" : "",
    "country" : "",
    "level" : 0,
    "req_level" : 0,
    "red_heropower" : 0,
    "req_vip" : 0,
    "not_full" : 0,
    "recommended" : 0,
    "version" : "",
    "token" : "",
    "lang" : "",
    "task" : "logged/guild/getguild"
}
```
Most of the fields can be left as default values, and theyre just used
for filtering. `not_full` and `recommended` fields only take values `0,1`
As Java model:
```java
import com.fasterxml.jackson.annotation.JsonProperty;
import project.path.Request;

public class SearchGuildRequest extends Request {

    @JsonProperty("name")
    private String name;
    @JsonProperty("country")
    private String country;
    @JsonProperty("level")
    private int level;
    @JsonProperty("req_level")
    private int redLevel;
    @JsonProperty("req_heropower")
    private int reqHeroPower;
    @JsonProperty("req_vip")
    private int reqVip;
    @JsonProperty("not_full")
    private int notFull;
    @JsonProperty("recommended")
    private int recommended;


    public SearchGuildRequest(int reqID, String token,String version,String name){
        super(token,reqID,"android",version,"logged/guild/searchguild","us");
        this.name = name;
    }
}
```
The return is list of guilds, even if there is only one result

### 2.3 `logged/player/getprofile`
Used to get player data, has information about player gear, stats, battle event and other
As JSON:
```json
{
    "req_id" : 0,
    "platform" : "",
    "player_id" : 0,
    "version" : "",
    "token" : "",
    "lang" : "",
    "task" : "logged/player/getprofile"
}
```
As Java model:
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

### 2.4 `unlogged/getstaticdata`
Get static data, has everything from items to crafts. Everytime something is pushed
from server, static data is updated.
As JSON:
```json
{
    "req_id" : 0,
    "platform" : "",
    "ts" : 0,
    "version" : "",
    "token" : "",
    "lang" : "",
    "task" : "unlogged/getstaticdata"
}
```
As Java model:
```java
import com.fasterxml.jackson.annotation.JsonProperty;
import project.path.Request;

import java.time.Instant;

public class StaticDataRequest extends Request {

    @JsonProperty("ts")
    private long ts;

    public StaticDataRequest(int reqID,String token,String version){
        super(token,reqID,"android",version,"unlogged/getstaticdata","us");
        this.ts = Instant.now().getEpochSecond();
    }
}

```

## 03 Finding values

### 3.1 Finding player token on bluestacks.

#### 1. Open up Media Manager
#### 2. Go to `Android/data/com.gamesture.questland/files/beskar/logs`
In there you have 5 latest log files named with Unix timestamp. Search for any request
for example `logged/player/void` below you'll find json form request which has `token`
field, there is your player token
🔴Important🔴
Using this api is against ToS, it is not recommended to use your main account's token
