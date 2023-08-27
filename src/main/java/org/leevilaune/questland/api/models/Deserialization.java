package org.leevilaune.questland.api.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.leevilaune.questland.api.models.guild.GuildPlayer;
import org.leevilaune.questland.api.models.guildranking.GuildRanking;
import org.leevilaune.questland.api.models.guildranking.RankingGuild;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
}
