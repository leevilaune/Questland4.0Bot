package org.leevilaune.questland.api.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.leevilaune.questland.api.models.guild.GuildPlayer;
import org.leevilaune.questland.api.models.guildranking.GuildRanking;
import org.leevilaune.questland.api.models.guildranking.RankingGuild;
import org.leevilaune.questland.api.models.player.BattleEvent;
import org.leevilaune.questland.api.models.player.ItemOrb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Deserialization {

    private ObjectMapper mapper;
    public Deserialization(){
        this.mapper = new ObjectMapper();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    public String reformat(int id,String replacement, String json){

        String newJson = json.replace("\""+id+"\":{","\""+replacement+"\":{");

        return newJson;
    }
    public List<GuildPlayer> deserializeGuildPlayers(String json) throws Exception {

        List<GuildPlayer> players = new ArrayList<>();
        JsonNode node = new ObjectMapper().readerFor(JsonNode.class).readValue(json);
        Iterator<String> fields = node.get("pinfo").fieldNames();
        while(fields.hasNext()){
            players.add(mapper.readerFor(GuildPlayer.class).readValue(node.get("pinfo").get(fields.next()).get("p")));
        }
        System.out.println(node);
        return players;
    }
    public GuildRanking deserializeGuildRanking(String json) throws Exception{
        List<RankingGuild> guilds = new ArrayList<>();
        GuildRanking ranking = mapper.readerFor(GuildRanking.class).readValue(json);
        JsonNode node = mapper.readerFor(JsonNode.class).readValue(json);
        Iterator<String> fields = node.get("guilds_list").fieldNames();
        while(fields.hasNext()){
            guilds.add(mapper.readerFor(RankingGuild.class).readValue(node.get("guilds_list").get(fields.next())));
        }
        ranking.setGuildsList(guilds);
        return ranking;
    }
    public BattleEvent deserializePlayerBattleEvent(String json, int id) throws Exception {
        BattleEvent battleEvent = new BattleEvent();
        JsonNode pinfo = mapper.readerFor(JsonNode.class).readValue(json);
        JsonNode be = pinfo.get("pinfo").get(String.valueOf(id)).get("battle_event");
        battleEvent.setMultiplier(Double.valueOf(be.get("multiplier").asText()));
        battleEvent.setScore(Integer.valueOf(be.get("score").asText()));
        JsonNode bonuses = be.get("bonuses");
        JsonNode firstBonus = bonuses.get(String.valueOf(1));
        String firstBonusName = firstBonus.get(firstBonus.fieldNames().next()).get("attr").asText().split(">")[1].split("<")[0];
        String firstBonusValue = firstBonus.get(firstBonus.fieldNames().next()).get("val").asText();
        battleEvent.setFirstBonus(firstBonusName + " " + firstBonusValue);
        JsonNode thirdBonus = bonuses.get(String.valueOf(3));
        String thirdBonusName = thirdBonus.get(thirdBonus.fieldNames().next()).get("attr").asText().split(">")[1].split("<")[0];
        String thirdBonusValue = thirdBonus.get(thirdBonus.fieldNames().next()).get("val").asText();
        battleEvent.setThirdBonus(thirdBonusName + " " +thirdBonusValue);
        JsonNode fourthBonus = bonuses.get(String.valueOf(4));
        String fourthBonusName = fourthBonus.get(fourthBonus.fieldNames().next()).get("attr").asText().split(">")[1].split("<")[0];
        String fourthBonusValue = fourthBonus.get(fourthBonus.fieldNames().next()).get("val").asText();
        battleEvent.setFourthBonus(fourthBonusName + " " +fourthBonusValue);

        System.out.println(battleEvent);
        //System.out.println(be.get(String.valueOf(1)).get(1));

        return battleEvent;
    }
    public List<ItemOrb> deserializeOrbs(String json, int id) throws JsonProcessingException {
        List<ItemOrb> orbs = new ArrayList<>();
        JsonNode node = mapper.readValue(json,JsonNode.class);
        JsonNode items = node.get("pinfo").get(String.valueOf(id)).get("items_list");
        Iterator<String> itemsIt = items.fieldNames();
        int i = 1;
        while(itemsIt.hasNext()){
            String itemID = itemsIt.next();
            int size = items.get(itemID).size();
            if(String.valueOf(itemID.charAt(0)).equalsIgnoreCase("o") && size==3){
                ItemOrb orb = new ItemOrb();
                //String[] orbData = mapper.readValue(items.get(itemID).toString(),String[].class);
                //List<String> strings = Arrays.stream(orbData).collect(Collectors.toList());
                //orb.setOrb(strings);
                orb.setItemID(itemID);
                orb.setOrbID(items.get(itemID).get(0).asInt());
                JsonNode orbData = items.get(itemID).get(2).get("orb");
                orb.setSlot(orbData.get(0).asText());
                orb.setLevel(orbData.get(1).asInt());
                orb.setEnhance(orbData.get(2).asInt());
                orb.setPotential(orbData.get(4).asInt());
                orb.setBonus(orbData.get(5).asDouble());
                orbs.add(orb);
                System.out.println(i + "," + orb.getOrbID() + "," + orb.getSlot() + "," + orb.getEnhance() + ","+orb.getLevel()+","+orb.getPotential());

                i++;
            }
        }

        return orbs;
    }
}
