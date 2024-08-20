package org.leevilaune.questland;

import org.leevilaune.questland.api.*;
import org.leevilaune.questland.bot.Bot;
import org.leevilaune.questland.bot.CsvGenerator;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws Exception {
        String apiValues = Files.readString(Path.of("src/main/resources/questlandApiValues.csv"));
        String[] values = apiValues.split(",");
        String token = values[0];
        String version = values[1];

        WebSocketClient webSocketClient = new WebSocketClient();
        webSocketClient.run();

        GuildClient guildClient = new GuildClient(version,token,webSocketClient);
        GuildSearchClient guildSearchClient = new GuildSearchClient(guildClient,version,token);
        GuildRankingClient guildRankingClient = new GuildRankingClient();
        PlayerClient playerClient = new PlayerClient(guildSearchClient,token,version,webSocketClient);
        ArenaClient arenaClient = new ArenaClient(token,version);
        BattleEventRankingClient beRanking = new BattleEventRankingClient(playerClient);
        StaticDataClient staticDataClient = new StaticDataClient(token,version);
        //staticDataClient.getStaticData();
        //beRanking.getBattleEventRanking();

        //arenaClient.getRanking();

        HeroClient heroClient = new HeroClient(token,version,webSocketClient);
        //System.out.println(heroClient.getPlayer(11987825).print());

        //System.out.println(playerClient.getPlayer(11987825).print());
        //System.out.println(playerClient.getPlayer(10556233));

        StaticDataManager staticDataManager = new StaticDataManager();
        //staticDataManager.combineEvents();
        staticDataManager.combineItems();
        staticDataManager.saveImages("graphics/");

        //guildSearchClient.getGuild("impero del lupo");

        //playerSearchClient.getGuilds();

        //guildRankingClient.getGuildRanking();
        //arenaClient.getRanking();
        //System.out.println(guildSearchClient.getGuild("legione fooking").print());
        //System.out.println(playerClient.getPlayer("nullvalue","legione fooking").print());

        GuildRankingClient grc = new GuildRankingClient();
        //System.out.println(grc.getGuildRanking());

        CsvGenerator generator = new CsvGenerator(playerClient,guildClient,guildSearchClient,guildRankingClient);
        //generator.generateGuildCsv("chronos - suikoden",2243);

        QuestlandClient questlandClient = new QuestlandClient(playerClient,guildClient,guildSearchClient,version,token);
        Bot bot = new Bot(questlandClient);
        try{
            //bot.run();
        }catch (Exception e){
            e.printStackTrace();
        }
        webSocketClient.close();
    }
}