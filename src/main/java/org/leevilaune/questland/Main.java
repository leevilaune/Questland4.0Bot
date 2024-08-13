package org.leevilaune.questland;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kotlin.concurrent.ThreadsKt;
import org.leevilaune.questland.api.*;
import org.leevilaune.questland.api.models.Deserialization;
import org.leevilaune.questland.api.models.guild.Guild;
import org.leevilaune.questland.api.models.player.P;
import org.leevilaune.questland.api.models.player.PInfo;
import org.leevilaune.questland.api.models.player.Player;
import org.leevilaune.questland.api.models.player.PlayerInfo;
import org.leevilaune.questland.api.models.requests.PlayerRequest;
import org.leevilaune.questland.api.models.requests.Request;
import org.leevilaune.questland.bot.Bot;
import org.leevilaune.questland.bot.CsvGenerator;
import org.leevilaune.questland.csv.models.QuestlandGuild;
import org.leevilaune.questland.csv.models.QuestlandPlayer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        String apiValues = Files.readString(Path.of("src/main/resources/questlandApiValues.csv"));
        String[] values = apiValues.split(",");
        String token = values[0];
        String version = values[1];
        GuildClient guildClient = new GuildClient(version,token);
        GuildSearchClient guildSearchClient = new GuildSearchClient(guildClient,version,token);
        GuildRankingClient guildRankingClient = new GuildRankingClient();
        PlayerClient playerClient = new PlayerClient(guildSearchClient,token,version);
        ArenaClient arenaClient = new ArenaClient(token,version);
        BattleEventRankingClient beRanking = new BattleEventRankingClient(playerClient);
        beRanking.getBattleEventRanking();

        //guildSearchClient.getGuild("impero del lupo");

        PlayerSearchClient playerSearchClient = new PlayerSearchClient(token,version);
        //playerSearchClient.run();

        //playerSearchClient.getGuilds();

        //guildRankingClient.getGuildRanking();
        //arenaClient.getRanking();
        //System.out.println(guildSearchClient.getGuild("legione fooking").print());
        System.out.println(playerClient.getPlayer("nullvalue","legione fooking").print());

        GuildRankingClient grc = new GuildRankingClient();
        //System.out.println(grc.getGuildRanking());

        CsvGenerator generator = new CsvGenerator(playerClient,guildClient,guildSearchClient,guildRankingClient);
        //generator.generateGuildCsv("chronos",2243);

        QuestlandClient questlandClient = new QuestlandClient(playerClient,guildClient,guildSearchClient,version,token);
        Bot bot = new Bot(questlandClient);
        try{
            //bot.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}