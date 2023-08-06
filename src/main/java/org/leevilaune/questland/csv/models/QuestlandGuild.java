package org.leevilaune.questland.csv.models;

import java.util.ArrayList;
import java.util.List;

public class QuestlandGuild {

    private List<QuestlandPlayer> playerList;

    public QuestlandGuild() {
        this.playerList = new ArrayList<>();
    }

    public void addPlayer(QuestlandPlayer p){
        playerList.add(p);
    }
    public void printPlayers(){
        System.out.println("vip,name,guildName,guildRank,cc,fame,attack,health,defence,magic,multi,score");
        for (QuestlandPlayer player : playerList){
            System.out.println(player.getId()+","+
                    player.getName()+","+
                    player.getGuildName()+","+
                    player.getGuildRank()+","+
                    player.getCc()+","+
                    player.getFame()+","+
                    player.getAttack()+","+
                    player.getHealth()+","+
                    player.getDefence()+","+
                    player.getMagic()+","+
                    player.getMulti()+","+
                    player.getScore());
        }
    }
    public List<QuestlandPlayer> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<QuestlandPlayer> playerList) {
        this.playerList = playerList;
    }

    @Override
    public String toString() {
        return "QuestlandGuild{" +
                "playerList=" + playerList +
                '}';
    }
}
