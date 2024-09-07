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
        client.sendRequest(new GetProfileRequest(0,token,version,11987825));
    }
}
```
If we did everything correctly, we should get following json return.
```json
{
  "pinfo": {
    "11987825": {
      "p": {
        "g_name": "Plankton-ville",
        "g_sname": "",
        "g_rank": "member",
        "g_don_ts": 1715115406,
        "g_cre_ts": 0,
        "del": 0,
        "power": 15174287,
        "frame": 235,
        "g_id": 301379,
        "flags": 23,
        "g_shop_c": 22,
        "col1": {
          "slots": 10,
          "upg": [
            275,
            275,
            275,
            275,
            275,
            275,
            275,
            275,
            275,
            275
          ]
        },
        "sex": "female",
        "name": "NUUSKAPOEKA",
        "suffix": 2,
        "lts": 1725660963,
        "g_flvl": 11,
        "attr": {
          "hp": 3762673,
          "mag": 2929223,
          "crit": 1.5,
          "critch": 11,
          "dod": 6,
          "dmg": 5636717,
          "def": 2845674
        },
        "col2": {
          "slots": 10,
          "upg": [
            275,
            275,
            275,
            275,
            275,
            275,
            275,
            275,
            275,
            275
          ]
        },
        "id": 11987825,
        "fce": [
          0,
          1025,
          2337,
          775,
          0,
          523,
          2377,
          2353,
          96,
          4,
          6
        ],
        "g_banner": [
          3739,
          3631,
          3611,
          39555,
          3765,
          3663
        ],
        "cc": "fi",
        "vip": 14,
        "lvl": 100
      },
      "battle_event": {
        "score": 125549,
        "taken_guild_ms": 0,
        "multiplier": 324,
        "ticket_timers": {
          "be_campaign": 1725660300,
          "be_boss_normal": 1725660300,
          "be_boss_hard": 1725660300
        },
        "spirit_bonus": {
          "red": 6
        },
        "taken_ms": 32767,
        "taken_pass_ms": 0,
        "slots": [
          {
            "m": 80,
            "y": 1,
            "x": 1,
            "i": "w409051510"
          },
          {
            "y": 1,
            "x": 2,
            "i": "w420284132",
            "m": 65
          },
          {
            "y": 1,
            "x": 3,
            "i": "w420284148",
            "m": 45
          },
          {
            "y": 1,
            "x": 4,
            "i": "u44776669",
            "m": 20
          },
          {
            "y": 2,
            "x": 1,
            "i": "o20206982",
            "m": 30
          },
          {
            "y": 2,
            "x": 2,
            "i": "o20215413",
            "m": 30
          },
          {
            "y": 2,
            "x": 3,
            "i": "o20214048",
            "m": 10
          },
          {
            "y": 2,
            "x": 4,
            "i": "o20214050",
            "m": 10
          },
          {
            "x": 1,
            "i": "w408405850",
            "m": 6,
            "y": 3
          },
          {
            "y": 3,
            "x": 2,
            "i": "w420639894",
            "m": 6
          },
          {
            "y": 4,
            "x": 1,
            "i": "u44779897",
            "m": 10
          },
          {
            "y": 4,
            "x": 2,
            "i": "w408408908",
            "m": 6
          },
          {
            "y": 4,
            "x": 3,
            "i": "w420639896",
            "m": 6
          }
        ],
        "bonuses": {
          "1": {
            "1": {
              "id": 537,
              "attr": "\u003csize=12\u003e PASSIVE SKILL #2\u003c/size\u003e",
              "val": "ACTIVE"
            }
          },
          "3": {
            "2": {
              "id": 372,
              "attr": "\u003csize=12\u003eATTACK\u003c/size\u003e",
              "val": "+15%"
            }
          },
          "4": {
            "4": {
              "id": 400,
              "attr": "\u003csize=12\u003eMAGIC RESISTANCE\u003c/size\u003e",
              "val": "+10%"
            }
          }
        }
      },
      "talents": {
        "2": [
          [
            10051,
            10
          ],
          [
            10001,
            8
          ],
          [
            10003,
            13
          ]
        ],
        "1": [
          [
            10039,
            10
          ],
          [
            10037,
            9
          ],
          [
            10003,
            13
          ]
        ],
        "4": [
          [
            31959,
            11
          ],
          [
            34527,
            11
          ],
          [
            10043,
            7
          ]
        ],
        "3": [
          [
            31959,
            11
          ],
          [
            34527,
            11
          ],
          [
            10045,
            8
          ]
        ]
      },
      "items_list": {
        "s10242162": [
          38865,
          1,
          {
            "s": 1
          }
        ],
        "o19807833": [
          39135,
          1,
          {
            "orb": [
              "off_hand",
              200,
              9,
              24845,
              331,
              0.3
            ]
          }
        ],
        "s10161288": [
          38652,
          1,
          {
            "s": 1
          }
        ],
        "w407974930": [
          39078,
          1,
          {
            "links": [
              1,
              1
            ],
            "wear": [
              1,
              200,
              0,
              158401420,
              155000,
              0,
              111639,
              84639,
              0,
              27000,
              0,
              1774600
            ]
          }
        ],
        "w409064030": [
          39033,
          1,
          {
            "links": [
              1
            ],
            "wear": [
              2,
              200,
              0,
              158401420,
              155000,
              2,
              115818,
              0,
              0,
              115818,
              0,
              1781300
            ]
          }
        ],
        "s9811958": [
          37839,
          1,
          {
            "s": 1
          }
        ],
        "s10251449": [
          38766,
          1,
          {
            "s": 1
          }
        ],
        "o20206982": [
          39585,
          1,
          {
            "orb": [
              "",
              1,
              4,
              0,
              0,
              0
            ]
          }
        ],
        "w375965620": [
          38351,
          1,
          {
            "links": [
              1
            ],
            "wear": [
              3,
              1,
              0,
              0,
              0,
              6
            ]
          }
        ],
        "w405424832": [
          38924,
          1,
          {
            "links": [
              1
            ],
            "wear": [
              3,
              1,
              0,
              0,
              0,
              10
            ]
          }
        ],
        "o19666645": [
          38889,
          1,
          {
            "orb": [
              "talisman",
              200,
              9,
              0,
              282,
              0.6
            ]
          }
        ],
        "o19953040": [
          39224,
          1,
          {
            "orb": [
              "main_hand",
              200,
              9,
              0,
              298,
              0.5
            ]
          }
        ],
        "w345821055": [
          37950,
          1,
          {
            "wear": [
              1,
              200,
              0,
              158401420,
              155000,
              0,
              100893,
              57893,
              0,
              43000,
              0,
              1130200
            ]
          }
        ],
        "w408406145": [
          38763,
          1,
          {
            "links": [
              1,
              1
            ],
            "wear": [
              1,
              200,
              0,
              158401420,
              155000,
              0,
              106664,
              65000,
              0,
              41664,
              0,
              1552180
            ]
          }
        ],
        "w402284396": [
          38522,
          1,
          {
            "links": [
              1
            ],
            "wear": [
              2,
              1,
              0,
              0,
              0,
              8
            ]
          }
        ],
        "w409063545": [
          39063,
          1,
          {
            "links": [
              1
            ],
            "wear": [
              2,
              1,
              0,
              0,
              0,
              4
            ]
          }
        ],
        "w409062994": [
          39093,
          1,
          {
            "wear": [
              2,
              1,
              0,
              0,
              0,
              1
            ],
            "links": [
              1
            ]
          }
        ],
        "o19652411": [
          38469,
          1,
          {
            "orb": [
              "chest",
              200,
              10,
              0,
              292,
              0.2
            ]
          }
        ],
        "o19863685": [
          39133,
          1,
          {
            "orb": [
              "main_hand",
              200,
              9,
              200,
              303,
              0.4
            ]
          }
        ],
        "o19923465": [
          39223,
          1,
          {
            "orb": [
              "main_hand",
              200,
              9,
              200,
              323,
              0.1
            ]
          }
        ],
        "o19938064": [
          39225,
          1,
          {
            "orb": [
              "head",
              200,
              9,
              25600,
              286,
              0.1
            ]
          }
        ],
        "s9888272": [
          37029,
          1,
          {
            "s": 1
          }
        ],
        "w408405850": [
          38786,
          1,
          {
            "links": [
              1,
              1
            ],
            "wear": [
              1,
              200,
              0,
              158401420,
              155000,
              0,
              106266,
              68266,
              0,
              38000,
              0,
              1044220
            ]
          }
        ],
        "w408408627": [
          38816,
          1,
          {
            "links": [
              1,
              0
            ],
            "wear": [
              2,
              1,
              0,
              0,
              0,
              7
            ]
          }
        ],
        "o20214048": [
          39584,
          1
        ],
        "w400032494": [
          37514,
          1
        ],
        "w405424251": [
          38917,
          1,
          {
            "links": [
              1
            ],
            "wear": [
              3,
              200,
              0,
              158401420,
              155000,
              8,
              120395,
              0,
              0,
              120395,
              0,
              3187900
            ]
          }
        ],
        "w407974731": [
          39019,
          1,
          {
            "links": [
              1,
              1
            ],
            "wear": [
              1,
              200,
              0,
              158401420,
              155000,
              0,
              111440,
              76440,
              0,
              35000,
              0,
              1867200
            ]
          }
        ],
        "o19131755": [
          38469,
          1,
          {
            "orb": [
              "off_hand",
              200,
              9,
              0,
              281,
              0.5
            ]
          }
        ],
        "o19247512": [
          38469,
          1,
          {
            "orb": [
              "gloves",
              200,
              10,
              0,
              284,
              0.4
            ]
          }
        ],
        "s10161287": [
          38653,
          1,
          {
            "s": 1
          }
        ],
        "u44776669": [
          6063,
          1
        ],
        "w402285327": [
          38748,
          1,
          {
            "links": [
              1
            ],
            "wear": [
              2,
              1,
              0,
              0,
              0,
              6
            ]
          }
        ],
        "w402285477": [
          38778,
          1,
          {
            "links": [
              1
            ],
            "wear": [
              3,
              1,
              0,
              0,
              0,
              1
            ]
          }
        ],
        "o19807838": [
          39137,
          1,
          {
            "orb": [
              "amulet",
              200,
              10,
              0,
              324,
              0.5
            ]
          }
        ],
        "w420284132": [
          39429,
          1,
          {
            "wear": [
              0,
              1,
              6
            ]
          }
        ],
        "w284868309": [
          32367,
          1,
          {
            "wear": [
              0,
              1,
              8,
              0,
              0,
              0,
              0,
              0,
              0,
              0,
              0,
              0,
              1
            ]
          }
        ],
        "w408408908": [
          38734,
          1,
          {
            "links": [
              1,
              0
            ],
            "wear": [
              2,
              1,
              0,
              0,
              0,
              5
            ]
          }
        ],
        "w409049841": [
          39397,
          1,
          {
            "links": [
              1,
              0
            ],
            "wear": [
              1,
              200,
              0,
              158401420,
              155000,
              0,
              113629,
              88000,
              0,
              25629,
              0,
              2288150
            ]
          }
        ],
        "o20079534": [
          38844,
          1,
          {
            "orb": [
              "head",
              200,
              9,
              100,
              292,
              1.2
            ]
          }
        ],
        "o20214050": [
          39584,
          1
        ],
        "o19728359": [
          38584,
          1,
          {
            "orb": [
              "amulet",
              200,
              9,
              100,
              297,
              0.7
            ]
          }
        ],
        "o19818108": [
          39136,
          1,
          {
            "orb": [
              "head",
              200,
              9,
              0,
              330,
              0.1
            ]
          }
        ],
        "w406652510": [
          38938,
          1,
          {
            "wear": [
              3,
              1,
              0,
              0,
              0,
              7
            ],
            "links": [
              1
            ]
          }
        ],
        "o19212518": [
          38585,
          1,
          {
            "orb": [
              "gloves",
              200,
              10,
              0,
              291,
              0.6
            ]
          }
        ],
        "o20079591": [
          39226,
          1,
          {
            "orb": [
              "talisman",
              200,
              9,
              0,
              305,
              0.6
            ]
          }
        ],
        "w402285105": [
          38808,
          1,
          {
            "wear": [
              3,
              1,
              0,
              0,
              0,
              2
            ],
            "links": [
              1
            ]
          }
        ],
        "w405425702": [
          39048,
          1,
          {
            "links": [
              1,
              1
            ],
            "wear": [
              1,
              200,
              0,
              158401420,
              155000,
              0,
              111241,
              91000,
              0,
              20241,
              0,
              1457470
            ]
          }
        ],
        "w375965410": [
          38358,
          1,
          {
            "links": [
              1
            ],
            "wear": [
              3,
              1,
              0,
              0,
              0,
              5
            ]
          }
        ],
        "w420639894": [
          5131,
          1
        ],
        "o19707705": [
          38890,
          1,
          {
            "orb": [
              "ring",
              200,
              9,
              0,
              297,
              0.9
            ]
          }
        ],
        "o20173790": [
          39576,
          1,
          {
            "orb": [
              "amulet",
              200,
              9,
              100,
              314,
              0.2
            ]
          }
        ],
        "w402284092": [
          38492,
          1,
          {
            "links": [
              1
            ],
            "wear": [
              2,
              1,
              0,
              0,
              0,
              10
            ]
          }
        ],
        "o19225423": [
          38467,
          1,
          {
            "orb": [
              "gloves",
              200,
              10,
              0,
              300,
              0.3
            ]
          }
        ],
        "w408409377": [
          38793,
          1,
          {
            "wear": [
              2,
              1,
              0,
              0,
              0,
              9
            ],
            "links": [
              1,
              0
            ]
          }
        ],
        "o19658548": [
          38844,
          1,
          {
            "orb": [
              "feet",
              200,
              10,
              0,
              298,
              0.8
            ]
          }
        ],
        "o19923466": [
          39222,
          1,
          {
            "orb": [
              "feet",
              200,
              9,
              0,
              322,
              0.9
            ]
          }
        ],
        "o19953241": [
          38844,
          1,
          {
            "orb": [
              "chest",
              200,
              9,
              0,
              294,
              0.4
            ]
          }
        ],
        "w402181034": [
          38638,
          1,
          {
            "wear": [
              0,
              200,
              0,
              158401420,
              155000,
              0,
              109848,
              0,
              0,
              60000,
              49848,
              1169240,
              1
            ]
          }
        ],
        "w375965056": [
          38344,
          1,
          {
            "links": [
              1
            ],
            "wear": [
              3,
              1,
              0,
              0,
              0,
              4
            ]
          }
        ],
        "w402286338": [
          39180,
          1,
          {
            "wear": [
              1,
              200,
              0,
              158401420,
              155000,
              0,
              114027,
              63027,
              0,
              51000,
              0,
              1000090
            ]
          }
        ],
        "w420639896": [
          19225,
          1
        ],
        "w408409618": [
          38756,
          1,
          {
            "links": [
              1,
              0
            ],
            "wear": [
              2,
              1,
              0,
              0,
              0,
              3
            ]
          }
        ],
        "w420284148": [
          39586,
          1,
          {
            "wear": [
              0,
              1,
              10
            ]
          }
        ],
        "o19863600": [
          39132,
          1,
          {
            "orb": [
              "ring",
              200,
              10,
              0,
              311,
              0.6
            ]
          }
        ],
        "o19975219": [
          39225,
          1,
          {
            "orb": [
              "chest",
              200,
              9,
              0,
              299,
              0.4
            ]
          }
        ],
        "o19978139": [
          39226,
          1,
          {
            "orb": [
              "feet",
              200,
              9,
              100,
              313,
              0.2
            ]
          }
        ],
        "o20211553": [
          39223,
          1,
          {
            "orb": [
              "off_hand",
              200,
              9,
              200,
              323,
              1
            ]
          }
        ],
        "w405424970": [
          38931,
          1,
          {
            "links": [
              1
            ],
            "wear": [
              3,
              1,
              0,
              0,
              0,
              9
            ]
          }
        ],
        "w409051510": [
          39428,
          1,
          {
            "links": [
              1,
              0
            ],
            "wear": [
              1,
              200,
              0,
              158401420,
              155000,
              0,
              113629,
              88000,
              0,
              25629,
              0,
              1255430
            ]
          }
        ],
        "o20161622": [
          39576,
          1,
          {
            "orb": [
              "ring",
              200,
              9,
              200,
              289,
              0.5
            ]
          }
        ],
        "o20215413": [
          39585,
          1,
          {
            "orb": [
              "",
              1,
              4,
              100,
              0,
              0
            ]
          }
        ],
        "u44779897": [
          4541,
          2
        ],
        "w408408706": [
          38741,
          1,
          {
            "links": [
              1,
              0
            ],
            "wear": [
              3,
              1,
              0,
              0,
              0,
              3
            ]
          }
        ],
        "o19138740": [
          38467,
          1,
          {
            "orb": [
              "talisman",
              200,
              9,
              940,
              288,
              0.2
            ]
          }
        ]
      },
      "g_invites": [],
      "pow_rank": 13,
      "pow_rank_newbie": 0
    }
  },
  "staticdata_ts": 1725621721,
  "ts": 1725706188
}

```
