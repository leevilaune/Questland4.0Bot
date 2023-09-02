package org.leevilaune.questland.bot;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.Embed;
import discord4j.core.object.command.ApplicationCommandOption;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.core.spec.InteractionCallbackSpec;
import discord4j.core.spec.MessageEditSpec;
import discord4j.discordjson.json.ApplicationCommandData;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import org.leevilaune.questland.api.QuestlandClient;
import org.leevilaune.questland.api.models.domain.Item;
import org.leevilaune.questland.api.models.guild.Academy;
import org.leevilaune.questland.api.models.guild.AcademyCostItem;
import org.leevilaune.questland.api.models.guild.AcademyCosts;
import org.leevilaune.questland.api.models.guild.Guild;
import org.leevilaune.questland.api.models.player.Player;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Bot {
    private QuestlandClient questlandClient;

    public Bot(QuestlandClient questlandClient){
        this.questlandClient = questlandClient;
    }
    public void run() throws Exception{
        String token = Files.readString(Path.of("src/main/resources/discordToken.txt"));
        DiscordClient client = DiscordClient.create(token);
        GatewayDiscordClient gateway = client.login().block();

        // Get our application's ID
        long applicationId = gateway.getRestClient().getApplicationId().block();

// Build our command's definition
        ApplicationCommandRequest playerCmdRequest = ApplicationCommandRequest.builder()
                .name("hero")
                .description("finds a hero with name and guild name")
                .addOption(ApplicationCommandOptionData.builder()
                        .name("name")
                        .description("hero name")
                        .type(ApplicationCommandOption.Type.STRING.getValue())
                        .required(true)
                        .build())
                .addOption(ApplicationCommandOptionData.builder()
                        .name("guild")
                        .description("guild name")
                        .type(ApplicationCommandOption.Type.STRING.getValue())
                        .required(true)
                        .build())
                .build();
        ApplicationCommandRequest guildCmdRequest = ApplicationCommandRequest.builder()
                .name("guild")
                .description("finds a guild")
                .addOption(ApplicationCommandOptionData.builder()
                        .name("name")
                        .description("guild name")
                        .type(ApplicationCommandOption.Type.STRING.getValue())
                        .required(true)
                        .build())
                .build();
        ApplicationCommandRequest itemCmdRequest = ApplicationCommandRequest.builder()
                .name("item")
                .description("finds ql item")
                .addOption(ApplicationCommandOptionData.builder()
                        .name("name")
                        .required(true)
                        .description("item name")
                        .type(ApplicationCommandOption.Type.STRING.getValue())
                        .build())
                .build();
        // Get the commands from discord as a Map
        Map<String, ApplicationCommandData> discordCommands = gateway.getRestClient()
                .getApplicationService()
                .getGlobalApplicationCommands(applicationId)
                .collectMap(ApplicationCommandData::name)
                .block();
// Create the command with Discord
        gateway.getRestClient().getApplicationService()
                .createGlobalApplicationCommand(applicationId, playerCmdRequest)
                .subscribe();
        gateway.getRestClient().getApplicationService()
                .createGlobalApplicationCommand(applicationId, guildCmdRequest)
                .subscribe();
        gateway.getRestClient().getApplicationService()
                .createGlobalApplicationCommand(applicationId, itemCmdRequest)
                .subscribe();

        gateway.on(ChatInputInteractionEvent.class, event -> {
            if (event.getCommandName().equals("hero")) {
                try {
                    event.deferReply().subscribe();
                    String reply = playerLookup(event.getOption("name").get().getValue().get().getRaw(),
                            event.getOption("guild").get().getValue().get().getRaw());
                    event.editReply(reply).subscribe();
                } catch (Exception e) {
                    e.printStackTrace();
                    gateway.getRestClient().getChannelById(Snowflake.of("1147561619757482114")).createMessage(e.getMessage());
                }
            }
            else if(event.getCommandName().equals("guild")){
                try {
                    event.deferReply().subscribe();
                    String reply = guildLookup(event.getOption("name").get().getValue().get().getRaw());
                    event.editReply(reply).subscribe();
                } catch (Exception e) {
                    e.printStackTrace();
                    gateway.getRestClient().getChannelById(Snowflake.of("1147561619757482114")).createMessage(e.getMessage());
                }
            }
            else if(event.getCommandName().equals("item")){
                try {
                    event.deferReply().subscribe();
                    String reply = itemLookup(event.getOption("name").get().getValue().get().getRaw());
                    event.editReply(reply).subscribe();
                } catch (Exception e) {
                    e.printStackTrace();
                    gateway.getRestClient().getChannelById(Snowflake.of("1147561619757482114")).createMessage(e.getMessage());
                }
            }
            return new Mono<Object>() {
                @Override
                public void subscribe(CoreSubscriber<? super Object> actual) {

                }
            };
        }).subscribe();
        gateway.onDisconnect().block();
    }
    private String playerLookup(String playerName, String guildName) throws Exception{

        Player player = questlandClient.findPlayer(playerName,guildName);
        String response = "## __**" + player.getPinfo().getPlayerInfo().getP().getName() + "**__" +
                "\n>>> **ID**\n"+ player.getPinfo().getPlayerInfo().getP().getId()+
                "\n**Guild**\n"+player.getPinfo().getPlayerInfo().getP().getGuildName()+
                "\n**Hero Power**\n"+player.getPinfo().getPlayerInfo().getP().getPower()+
                "\n**Hall of Fame**\n"+player.getPinfo().getPlayerInfo().getPowRank()+
                "\n**Health**\n"+player.getPinfo().getPlayerInfo().getP().getAttr().getHealth()+
                "\n**Attack**\n"+player.getPinfo().getPlayerInfo().getP().getAttr().getAttack()+
                "\n**Defence**\n"+player.getPinfo().getPlayerInfo().getP().getAttr().getDefence()+
                "\n**Magic**\n"+player.getPinfo().getPlayerInfo().getP().getAttr().getMagic()+
                "\n**Multiplier**\n"+player.getPinfo().getPlayerInfo().getBattleEvent().getMultiplier()+
                "\n**Battle Event Trophies**\n"+player.getPinfo().getPlayerInfo().getBattleEvent().getScore()+"";
        return response;
    }
    private String guildLookup(String guildName) throws Exception{
        Guild guild = questlandClient.findGuild(guildName);
        AcademyCosts academyCosts = new AcademyCosts();
        Academy academy = guild.getGuildsList().getGuildInfo().getAcademy();
        int dmgLvl = academy.getDamage().get(0);
        AcademyCostItem dmgCosts = academyCosts.getLevels().stream().filter(c -> c.getLevel() == dmgLvl).findFirst().get();
        int defLvl = academy.getDefence().get(0);
        AcademyCostItem defCosts = academyCosts.getLevels().stream().filter(c -> c.getLevel() == defLvl).findFirst().get();
        int hpLvl = academy.getHp().get(0);
        AcademyCostItem hpCosts = academyCosts.getLevels().stream().filter(c -> c.getLevel() == hpLvl).findFirst().get();
        int magLvl = academy.getMagic().get(0);
        AcademyCostItem magCosts = academyCosts.getLevels().stream().filter(c -> c.getLevel() == magLvl).findFirst().get();
        String response = "## __**"+guild.getGuildsList().getGuildInfo().getN()+"**__"+
                "\n>>> **ID**\n"+guild.getpInfo().getGuildPlayers().stream().findFirst().get().getGuildID()+
                "\n**Description**\n"+guild.getGuildsList().getGuildInfo().getDesc()+
                "\n**Guild Power**\n"+guild.getGuildsList().getGuildInfo().getP()+
                "\n**Members**\n"+guild.getGuildsList().getGuildInfo().getM()+"/"+guild.getGuildsList().getGuildInfo().getMmc()+
                "\n**Owner**\n"+guild.getpInfo().getGuildPlayers().stream().filter(p -> p.getGuildRank().equalsIgnoreCase("owner")).findFirst().get().getName()+
                "\n**Research Levels**\n Attack:  " + dmgLvl+"\n Defence: " + defLvl + "\n Health:  " + hpLvl+"\n Magic:   " + magLvl+
                "\n**Rank Points**\n"+guild.getGuildsList().getGuildInfo().getQeRankPoints();
        return response;
    }
    private String itemLookup(String itemName){
        Item item = questlandClient.findItem(itemName);

        String header = "### [__**" + item.getName()+"**__]("+item.getFullUrl()+")";
        if(item.getFullUrl().isEmpty()){
            header = "### __**" + item.getName()+"**__";
        }

        String response = header+
                "\n>>> **Emblem**\n"+item.getEmblem()+
                "\n**Slot**\n"+item.getSlot()+
                "\n**Type**\n" + item.getTypeID()+
                "\n**Potential**\n"+item.getPotential()+
                "\n** Health**\n "+item.getHealth()+
                "\n** Attack**\n "+item.getAttack()+
                "\n** Defence**\n "+item.getDefence()+
                "\n** Magic**\n "+item.getMagic()+
                "\n**Links**"+
                "\n "+item.getLinks().get(0)+
                "\n "+item.getLinks().get(1)+
                "\n "+item.getLinks().get(2);

        return response;
    }
}
