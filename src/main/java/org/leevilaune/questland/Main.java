package org.leevilaune.questland;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        GuildClient guildClient = new GuildClient();
        GuildSearchClient guildSearchClient = new GuildSearchClient(guildClient);
        PlayerClient playerClient = new PlayerClient(guildSearchClient);

        QuestlandClient questlandClient = new QuestlandClient(playerClient,guildClient,guildSearchClient);
        Bot bot = new Bot(questlandClient);
        try{
            bot.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}