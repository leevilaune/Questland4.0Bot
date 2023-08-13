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
import java.util.ArrayList;
import java.util.List;

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
            Guild g = mapper.readerFor(Guild.class).readValue(deserialization.reformat(200155,"guild_info",Files.readString(Path.of("src/main/resources/neverwinter.json"))));
            //Guild g = mapper.readerFor(Guild.class).readValue(deserialization.reformat(202597,"guild_info",Files.readString(Path.of("src/main/resources/invictafooking.json"))));

            WebSocketEcho webSocketEcho = new WebSocketEcho();
            PlayerRequest request = new PlayerRequest(0,"android",0,"4.11.4.3907","9d5ccbae75c5d35c6a56f78d3855df9a","en","logged/player/getprofile");
            QuestlandGuild guild = new QuestlandGuild();
            int ind = 0;
            for (int i : g.getGuildsList().getGuildInfo().getMembers()){
                System.out.println(i);
                webSocketEcho.getRequests().add(new PlayerRequest(ind,"android",i,"4.11.4.3907","9d5ccbae75c5d35c6a56f78d3855df9a","en","logged/player/getprofile"));
                PlayerRequest pr = new PlayerRequest(ind,"android",i,"4.11.4.3907","9d5ccbae75c5d35c6a56f78d3855df9a","en","logged/player/getprofile");
                webSocketEcho.setRequest(pr);
                webSocketEcho.run();
                Thread.sleep(2000);

            }
            List<Player> players = new ArrayList<>();
            int index = 0;
            for(String s : webSocketEcho.getCompleted()){
                Player p;
                try {
                    p = mapper.readerFor(Player.class).readValue(deserialization.reformat(g.getGuildsList().getGuildInfo().getMembers().get(index), "player_info", s));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                players.add(p);
                index++;
            }
            for(Player player : players){
                if(player.getPinfo() == null || player.getPinfo().getPlayerInfo() == null){
                    continue;
                }
                int bloodlustID = 10003;
                int chillingColdID = 10051;
                int bmrID = 31959;
                int bloodlust = -1;
                int chillingCold = -1;
                int bmr = -1;

                PlayerInfo p = player.getPinfo().getPlayerInfo();
                for(List<Integer> talent : p.getTalents().getSlot1()){
                    if(p.getTalents() == null){
                        break;
                    }
                    if(talent.get(0) == bloodlustID){
                        bloodlust = talent.get(1);
                    }else if(talent.get(0) == chillingColdID){
                        chillingCold = talent.get(1);
                    }else if(talent.get(0) == bmrID){
                        bmr = talent.get(1);
                    }
                }
                for(List<Integer> talent : p.getTalents().getSlot2()){
                    if(p.getTalents() == null){
                        break;
                    }
                    if(talent.get(0) == bloodlustID){
                        bloodlust = talent.get(1);
                    }else if(talent.get(0) == chillingColdID){
                        chillingCold = talent.get(1);
                    }else if(talent.get(0) == bmrID){
                        bmr = talent.get(1);
                    }
                }
                for(List<Integer> talent : p.getTalents().getSlot3()){
                    if(p.getTalents() == null){
                        break;
                    }
                    if(talent.get(0) == bloodlustID){
                        bloodlust = talent.get(1);
                    }else if(talent.get(0) == chillingColdID){
                        chillingCold = talent.get(1);
                    }else if(talent.get(0) == bmrID){
                        bmr = talent.get(1);
                    }
                }
                for(List<Integer> talent : p.getTalents().getSlot4()){
                    if(p.getTalents() == null){
                        break;
                    }
                    if(talent.get(0) == bloodlustID){
                        bloodlust = talent.get(1);
                    }else if(talent.get(0) == chillingColdID){
                        chillingCold = talent.get(1);
                    }else if(talent.get(0) == bmrID){
                        bmr = talent.get(1);
                    }
                }
                guild.addPlayer(new QuestlandPlayer(p.getP().getId(),
                        p.getP().getName(),
                        p.getP().getGuildRank(),
                        p.getP().getCc(),
                        p.getP().getGuildName(),
                        p.getP().getFame(),
                        p.getP().getAttr().getAttack(),
                        p.getP().getAttr().getHealth(),
                        p.getP().getAttr().getDefence(),
                        p.getP().getAttr().getMagic(),
                        p.getP().getPower(),
                        p.getBattleEvent().getMultiplier(),
                        p.getBattleEvent().getScore(),
                        bloodlust,
                        chillingCold,
                        bmr));
            }
            guild.printPlayers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}