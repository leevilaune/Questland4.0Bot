package org.leevilaune.questland.api;

import org.leevilaune.questland.api.models.guild.Guild;
import org.leevilaune.questland.api.models.player.Player;

public class QuestlandClient {

    private PlayerClient playerClient;
    private GuildClient guildClient;
    private GuildSearchClient guildSearchClient;

    public QuestlandClient(PlayerClient playerClient, GuildClient guildClient, GuildSearchClient guildSearchClient){
        this.playerClient = playerClient;
        this.guildClient = guildClient;
        this.guildSearchClient = guildSearchClient;
    }

    public Player findPlayer(String name, String guildName){
        try {
            return playerClient.getPlayer(name,guildName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Guild findGuild(String name){
        try {
            return guildSearchClient.getGuild(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Guild();
    }

}
