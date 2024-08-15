package org.leevilaune.questland.bot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.leevilaune.questland.api.GuildClient;
import org.leevilaune.questland.api.GuildRankingClient;
import org.leevilaune.questland.api.GuildSearchClient;
import org.leevilaune.questland.api.PlayerClient;
import org.leevilaune.questland.api.models.Deserialization;
import org.leevilaune.questland.api.models.guild.Guild;
import org.leevilaune.questland.api.models.player.Player;
import org.leevilaune.questland.api.models.player.PlayerInfo;
import org.leevilaune.questland.csv.models.QuestlandGuild;
import org.leevilaune.questland.csv.models.QuestlandPlayer;

import java.util.ArrayList;
import java.util.List;

public class CsvGenerator {

    private ObjectMapper mapper;
    private PlayerClient playerClient;
    private GuildClient guildClient;
    private GuildSearchClient guildSearchClient;
    private GuildRankingClient guildRankingClient;
    private Deserialization deserialization;

    public CsvGenerator(PlayerClient playerClient,GuildClient guildClient, GuildSearchClient guildSearchClient,GuildRankingClient guildRankingClient){
        this.playerClient = playerClient;
        this.guildClient = guildClient;
        this.guildSearchClient = guildSearchClient;
        this.guildRankingClient = guildRankingClient;

        this.mapper = new ObjectMapper();
        this.deserialization = new Deserialization();

    }


    public void generateGuildCsv(String guildName, int id) throws Exception {
        int ind = 0;

        Guild g = this.guildSearchClient.getGuild(guildName);
        QuestlandGuild guild = new QuestlandGuild();
        List<Player> players = playerClient.getMultiple(g.getGuildsList().getGuildInfo().getMembers());
        System.out.println(g.getpInfo());

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
    }
}
