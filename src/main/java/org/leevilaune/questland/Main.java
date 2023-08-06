package org.leevilaune.questland;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.leevilaune.questland.api.WebSocketEcho;
import org.leevilaune.questland.api.models.Deserialization;
import org.leevilaune.questland.api.models.guild.Guild;
import org.leevilaune.questland.api.models.player.PInfo;
import org.leevilaune.questland.api.models.player.Player;
import org.leevilaune.questland.api.models.player.PlayerInfo;
import org.leevilaune.questland.api.models.requests.PlayerRequest;
import org.leevilaune.questland.api.models.requests.Request;
import org.leevilaune.questland.csv.models.QuestlandGuild;
import org.leevilaune.questland.csv.models.QuestlandPlayer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args){

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            Deserialization deserialization = new Deserialization();
            String guildRequest = "{\n" +
                    "   \"req_id\":0,\n" +
                    "   \"platform\":\"android\",\n" +
                    "   \"guild_id\":200155,\n" +
                    "   \"version\":\"4.11.2.3896\",\n" +
                    "   \"token\":\"9d5ccbae75c5d35c6a56f78d3855df9a\",\n" +
                    "   \"lang\":\"en\",\n" +
                    "   \"task\":\"logged/guild/getguild\"\n" +
                    "}";
            Guild g = mapper.readerFor(Guild.class).readValue(deserialization.reformat(202597,"guild_info",Files.readString(Path.of("src/main/resources/invictafooking.json"))));
            WebSocketEcho webSocketEcho = new WebSocketEcho();
            PlayerRequest request = new PlayerRequest(0,"android",0,"4.11.3.3899","9d5ccbae75c5d35c6a56f78d3855df9a","en","logged/player/getprofile");
            QuestlandGuild guild = new QuestlandGuild();
            for (int i : g.getGuildsList().getGuildInfo().getMembers()){
                request.setPlayerId(i);
                webSocketEcho.setRequest(request);
                webSocketEcho.setPlayerID(i);
                System.out.println(i);
                webSocketEcho.run();
                Thread.sleep(2000);
            }

            for(Player player : webSocketEcho.getCompleted()){
                System.out.println(player);
                if(player.getPinfo() == null){
                    continue;
                }
                PlayerInfo p = player.getPinfo().getPlayerInfo();
                guild.addPlayer(new QuestlandPlayer(p.getP().getVip(),
                        p.getP().getName(),
                        p.getP().getGuildRank(),
                        p.getP().getCc(),
                        p.getP().getGuildName(),
                        p.getP().getFame(),
                        p.getP().getAttr().getAttack(),
                        p.getP().getAttr().getHealth(),
                        p.getP().getAttr().getDefence(),
                        p.getP().getAttr().getMagic(),
                        p.getBattleEvent().getMultiplier(),
                        p.getBattleEvent().getScore()));
            }
            guild.printPlayers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}