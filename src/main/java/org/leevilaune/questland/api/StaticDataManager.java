package org.leevilaune.questland.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.leevilaune.questland.api.models.Color;
import org.leevilaune.questland.api.models.staticdata.*;
import org.leevilaune.questland.Logger;
import java.io.*;
import java.net.URL;
import java.time.Instant;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class StaticDataManager {

    private FileWriter writer;
    private String filePath;
    private ObjectMapper mapper;
    private Logger logger;

    private List<StaticItemTemplate> items;
    private List<StaticClientString> clientStrings;
    private List<StaticFile> staticFiles;
    private List<GearModel> itemModels;
    private List<ItemModel> usables;
    private List<ItemModel> skins;
    private List<OrbModel> orbs;
    private List<ItemModel> others;
    private List<StaticQuestTask> questTasks;
    private List<StaticEvent> events;
    private List<StaticWearableSet> emblems;
    private List<StaticMilestone> milestones;

    private List<String> csvs;

    public StaticDataManager() throws Exception {
        this.writer = new FileWriter("items.csv");
        this.mapper = new ObjectMapper();
        this.logger = new Logger();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.itemModels = new ArrayList<>();
        this.usables = new ArrayList<>();
        this.skins = new ArrayList<>();
        this.orbs = new ArrayList<>();
        this.others = new ArrayList<>();
        this.csvs = new ArrayList<>();
        sortFile();
    }

    public void sortFile(){
        this.items = new ArrayList<>();
        this.clientStrings = new ArrayList<>();
        this.staticFiles = new ArrayList<>();
        this.questTasks = new ArrayList<>();
        this.events = new ArrayList<>();
        this.emblems = new ArrayList<>();
        this.milestones = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("staticdata1.txt"))) {
            String currentFile = "";
            for (String line; (line = br.readLine()) != null; ) {
                if(line.isBlank()){
                    continue;
                }
                if (String.valueOf(line.charAt(0)).equalsIgnoreCase("+")) {
                    if (line.equalsIgnoreCase("+static_itemtemplates")) {
                        currentFile = line;
                        continue;
                    } else if (line.equalsIgnoreCase("+static_clientstrings")) {
                        currentFile = line;
                        continue;
                    } else if (line.equalsIgnoreCase("+static_files")) {
                        currentFile = line;
                        continue;
                    } else if(line.equalsIgnoreCase("+static_quests")){
                        currentFile = line;
                        continue;
                    } else if(line.equalsIgnoreCase("+static_questgroups")){
                        currentFile = line;
                        continue;
                    } else if(line.equalsIgnoreCase("+static_wearablesets")){
                        currentFile = line;
                        continue;
                    } else if(line.equalsIgnoreCase("+static_milestones")){
                        currentFile = line;
                        continue;
                    }else{
                        currentFile = "";
                        continue;
                    }
                }
                if (currentFile.equalsIgnoreCase("+static_itemtemplates")) {
                    String[] parts = line.split(",", 9);
                    StaticItemTemplate item = new StaticItemTemplate();
                    item.setId(Integer.valueOf(parts[0]));
                    JsonNode node = mapper.readValue(parts[8], JsonNode.class);
                    if (node.has("stats")) {
                        item.setStats(mapper.readValue(node.get("stats").toString(), Stats.class));
                    }
                    if (node.has("set")) {
                        item.setSet(node.get("set").asInt());
                    }
                    item.setName(node.get("n").asInt());
                    item.setQuality(node.get("q").asText());
                    item.setSlot(node.get("s").asText());
                    item.setItemGraphic(node.get("i").asInt());
                    item.setItemGraphicSd(node.get("i_sd").asInt());
                    item.setItemGraphicHd(node.get("i_hd").asInt());
                    item.setItemGraphicSd(node.get("i_r").asInt());
                    item.setItemGraphicPreview(node.get("prvw").asInt());
                    if(node.has("links")){
                        StaticLinks[] links = mapper.readValue(node.get("links").toString(),StaticLinks[].class);
                        item.setLinks(Arrays.stream(links).collect(Collectors.toList()));
                    }
                    items.add(item);
                } else if (currentFile.equalsIgnoreCase("+static_clientstrings")) {
                    String[] parts = line.split(",", 3);
                    StaticClientString clientString = new StaticClientString();
                    if (parts.length < 3) {
                        continue;
                    }
                    JsonNode node = mapper.readValue(parts[2], JsonNode.class);
                    clientString.setId(Integer.valueOf(parts[0]));
                    clientString.setEn(node.get("en").asText());
                    clientString.setIt(node.get("it").asText());
                    clientStrings.add(clientString);
                } else if (currentFile.equalsIgnoreCase("+static_files")) {
                    String[] parts = line.split(",", 5);
                    StaticFile staticFile = new StaticFile();
                    staticFile.setId(Integer.valueOf(parts[0]));
                    staticFile.setName(parts[1].replace(" ", "%20"));
                    String[][] hashes = mapper.readValue(parts[4], String[][].class);
                    staticFile.setHash(hashes[0][1]);
                    if (staticFile.getHash().isBlank()) {
                        //continue;
                    }
                    staticFile.setItemID(Integer.valueOf(hashes[0][6]));
                    staticFiles.add(staticFile);
                } else if(currentFile.equalsIgnoreCase("+static_quests")){
                    String[] parts = line.split(",",2);
                    StaticQuestTask questTask = new StaticQuestTask();
                    JsonNode node = mapper.readValue(parts[1], JsonNode.class);
                    String[] parts2 = node.get(0).asText().split("\\{");
                    List<String> strings = Arrays.stream(parts2).filter(i->i.contains("STRING")).collect(Collectors.toList());
                    List<Integer> taskParts = new ArrayList<>();
                    for(String s : strings){
                        String[] parts3 = s.split("[}]");
                        int task = Integer.valueOf(parts3[0].split("\\.")[1]);
                        taskParts.add(task);
                    }
                    questTask.setId(Integer.valueOf(parts[0]));
                    questTask.setTask(taskParts);
                    questTask.setItem(node.get(1).asInt());
                    questTasks.add(questTask);
                }else if(currentFile.equalsIgnoreCase("+static_questgroups")){
                    String[] parts = line.split(",",2);
                    JsonNode node = mapper.readValue(parts[1], JsonNode.class);
                    JsonNode tabsNode = mapper.readValue(node.get(23).toString(), JsonNode.class);
                    List<StaticEventTab> tabs = new ArrayList<>();
                    StaticEvent event = new StaticEvent();
                    event.setId(Integer.valueOf(parts[0]));
                    event.setName(node.get(0).asInt());
                    event.setStartTS(node.get(5).asLong());
                    event.setEndTS(node.get(6).asLong());
                    event.setMilestones(node.get(25).asInt());
                    for(int i = 0; i<tabsNode.size();i++){
                        StaticEventTab tab = new StaticEventTab();
                        tab.setName(tabsNode.get(i).get(0).get(0).asText());
                        tab.setStartTS(tabsNode.get(i).get(0).get(1).asLong());
                        tab.setEndTS(tabsNode.get(i).get(0).get(2).asLong());
                        HashMap<Integer,Integer> tasks = new HashMap<>();
                        for(int j = 0;j<tabsNode.get(i).get(1).size();j++){
                            tasks.put(tabsNode.get(i).get(1).get(j).get(0).asInt(),tabsNode.get(i).get(1).get(j).get(2).asInt());
                        }
                        tab.setTasks(tasks);
                        tabs.add(tab);
                    }
                    event.setTabs(tabs);
                    this.events.add(event);
                } else if(currentFile.equalsIgnoreCase("+static_wearablesets")){
                    String[] parts = line.split(",",2);
                    StaticWearableSet emblem = new StaticWearableSet();
                    emblem.setId(Integer.valueOf(parts[0]));
                    JsonNode node = mapper.readValue(parts[1], JsonNode.class);
                    emblem.setName(node.get(0).asInt());
                    this.emblems.add(emblem);
                }else if(currentFile.equalsIgnoreCase("+static_milestones")){
                    String[] parts = line.split(",",2);
                    JsonNode node = mapper.readValue(parts[1],JsonNode.class);
                    StaticMilestone milestones = new StaticMilestone();
                    milestones.setId(Integer.valueOf(parts[0]));
                    List<StaticReward> rewardList = new ArrayList<>();
                    for(int i = 0;i<=node.get("rewards").size();i++){
                        JsonNode rewardsNode = node.get("rewards").get(i);
                        if(rewardsNode==null){
                            continue;
                        }
                        StaticReward reward = new StaticReward();
                        reward.setRequiredPoints(rewardsNode.get(0).asInt());
                        reward.setItem(rewardsNode.get(1).get(0).get(0).asInt());
                        reward.setAmount(rewardsNode.get(1).get(0).get(1).asInt());
                        rewardList.add(reward);
                    }
                    milestones.setRewards(rewardList);
                    //System.out.println(milestones);
                    this.milestones.add(milestones);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            this.writer = new FileWriter("data/clientStrings.json");
            String json = mapper.writeValueAsString(this.clientStrings);
            JsonNode node = mapper.readValue(json,JsonNode.class);
            writer.write(node.toPrettyString());
            writer.flush();
            this.writer = new FileWriter("data/staticFiles.json");
            json = mapper.writeValueAsString(this.staticFiles);
            node = mapper.readValue(json,JsonNode.class);
            writer.write(node.toPrettyString());
            writer.flush();
            this.writer = new FileWriter("data/itemTemplates.json");
            json = mapper.writeValueAsString(this.items);
            node = mapper.readValue(json,JsonNode.class);
            writer.write(node.toPrettyString());
            writer.flush();
            this.writer = new FileWriter("data/milestones.json");
            json = mapper.writeValueAsString(this.milestones);
            node = mapper.readValue(json,JsonNode.class);
            writer.write(node.toPrettyString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void combineItems(){
        String[] wearables = new String[]{"talisman","feet","gloves","head","chest","off_hand","amulet","main_hand","ring"};
        List<String> wearableSlots = new ArrayList<>(Arrays.stream(wearables).collect(Collectors.toList()));
        for(int i = items.size()-1; i>0;i--){
            int finalI = i;
            /*if(items.get(i).getId()<38000){
                continue;
            }*/
            List<String> urls = new ArrayList<>();
            try {
                urls.add(staticFiles.stream().filter(c->c.getId()==items.get(finalI).getItemGraphicSd()).findFirst().get().generateURL());
            } catch (Exception ignored) {
            }
            try {
                urls.add(staticFiles.stream().filter(c->c.getId()==items.get(finalI).getItemGraphicPreview()).findFirst().get().generateURL());
            } catch (Exception ignored) {
            }
            GearModel item = new GearModel();
            item.setSlot(items.get(i).getSlot());
            if(wearableSlots.contains(item.getSlot())){
                if(items.get(i).getStats()==null){
                    StaticItemTemplate itemTemplate = items.get(i);
                    item.setId(itemTemplate.getId());
                    item.setName(clientStrings.stream().filter(c->c.getId()==items.get(finalI).getName()).findFirst().get().getEn());
                    item.setSlot(itemTemplate.getSlot());
                    item.setQuality(itemTemplate.getQuality());
                    item.setUrls(urls);
                    this.skins.add(item);
                    continue;
                } else if(!items.get(i).getLinks().isEmpty()){
                    List<StaticLinks> l = items.get(i).getLinks();
                    item.setGearLinkType(l.get(0).getBonusStat());
                    List<Integer> gearLinks = new ArrayList<>();
                    for(List<Integer> link : l.get(0).getLinks()){
                        gearLinks.add(link.get(0));
                    }
                    item.setGearLinks(gearLinks);
                    if(items.get(i).getLinks().size()>1){
                        item.setOrbLinkType(l.get(1).getBonusStat());
                        List<Integer> orbLinks = new ArrayList<>();
                        for(List<Integer> link : l.get(1).getLinks()){
                            orbLinks.add(link.get(0));
                        }
                        item.setOrbLinks(orbLinks);
                    }
                }
                StaticItemTemplate itemTemplate = items.get(i);
                if(items.get(finalI).getSet()!=0){
                    int emblem = emblems.stream().filter(e->e.getId()==items.get(finalI).getSet()).findFirst().get().getName();
                    item.setEmblem(clientStrings.stream().filter(c->c.getId()==emblem).findFirst().get().getEn());
                }
                item.setId(itemTemplate.getId());
                item.setName(clientStrings.stream().filter(c->c.getId()==items.get(finalI).getName()).findFirst().get().getEn());
                item.setAttack(itemTemplate.getStats().getAttack().get(0));
                item.setAttackPotential(itemTemplate.getStats().getAttack().get(1));
                item.setHealth(itemTemplate.getStats().getHealth().get(0));
                item.setHealthPotential(itemTemplate.getStats().getHealth().get(1));
                item.setDefence(itemTemplate.getStats().getDefence().get(0));
                item.setDefencePotential(itemTemplate.getStats().getDefence().get(1));
                item.setMagic(itemTemplate.getStats().getMagic().get(0));
                item.setMagicPotential(itemTemplate.getStats().getMagic().get(1));
                item.setSlot(itemTemplate.getSlot());
                item.setQuality(itemTemplate.getQuality());
                item.setUrls(urls);
                System.out.println(item);
                if(item.getQuality().equalsIgnoreCase("legendary")) {
                    this.csvs.add(item.csv());
                }
                //System.out.println(item);
                this.itemModels.add(item);
            } else if(item.getSlot().equalsIgnoreCase("usable")){
                StaticItemTemplate itemTemplate = items.get(i);
                ItemModel itm = new ItemModel();
                itm.setId(itemTemplate.getId());
                itm.setName(clientStrings.stream().filter(c->c.getId()==items.get(finalI).getName()).findFirst().get().getEn());
                itm.setSlot(itemTemplate.getSlot());
                itm.setQuality(itemTemplate.getQuality());
                itm.setUrls(urls);
                this.usables.add(itm);
            } else if(item.getSlot().equalsIgnoreCase("orb")){
                StaticItemTemplate itemTemplate = items.get(i);
                OrbModel orb = new OrbModel();
                orb.setId(itemTemplate.getId());
                orb.setName(clientStrings.stream().filter(c->c.getId()==items.get(finalI).getName()).findFirst().get().getEn());
                orb.setSlot(itemTemplate.getSlot());
                orb.setQuality(itemTemplate.getQuality());
                orb.setStats(itemTemplate.getStats());
                //orb.setLinks(itemTemplate.getLinks().get(1));
                this.orbs.add(orb);
            }
            StaticItemTemplate itemTemplate = items.get(i);
            ItemModel itm = new ItemModel();
            itm.setId(itemTemplate.getId());
            itm.setName(clientStrings.stream().filter(c->c.getId()==items.get(finalI).getName()).findFirst().get().getEn());
            itm.setSlot(itemTemplate.getSlot());
            itm.setQuality(itemTemplate.getQuality());
            itm.setUrls(urls);
            this.others.add(itm);
            //System.out.println(itm);
        }
        try {
            this.writer = new FileWriter("data/items.json");
            String json = mapper.writeValueAsString(this.itemModels);
            JsonNode node = mapper.readValue(json,JsonNode.class);
            writer.write(node.toPrettyString());
            writer.flush();
            this.writer = new FileWriter("data/orbs.json");
            json = mapper.writeValueAsString(this.orbs);
            node = mapper.readValue(json,JsonNode.class);
            writer.write(node.toPrettyString());
            writer.flush();
            this.writer = new FileWriter("data/skins.json");
            json = mapper.writeValueAsString(this.skins);
            node = mapper.readValue(json,JsonNode.class);
            writer.write(node.toPrettyString());
            writer.flush();
            this.writer = new FileWriter("data/usables.json");
            json = mapper.writeValueAsString(this.usables);
            node = mapper.readValue(json,JsonNode.class);
            writer.write(node.toPrettyString());
            writer.flush();
            this.writer = new FileWriter("data/items.csv");
            for(String s : this.csvs){
                this.writer.write(s+"\n");
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void getGraphics(){
        for(int i = staticFiles.size()-1;i>0; i--){
            System.out.println(staticFiles.get(i).generateURL());
        }
    }
    private HashMap<Integer,List<RewardModel>> combineMilestones(){
        List<MilestoneModel> milestones = new ArrayList<>();
        HashMap<Integer,List<RewardModel>> milestonesMap = new HashMap<>();
        for(StaticMilestone sm : this.milestones){
            MilestoneModel mm = new MilestoneModel();
            mm.setId(sm.getId());
            List<RewardModel> rewards = new ArrayList<>();
            for(StaticReward sr : sm.getRewards()){
                RewardModel reward = new RewardModel();
                reward.setAmount(sr.getAmount());
                reward.setRequiredPoints(sr.getRequiredPoints());
                StaticItemTemplate sit = this.items.stream().filter(i -> i.getId()==sr.getItem()).findFirst().get();
                String name = this.getClientString(sit.getName());
                reward.setItem(name);
                rewards.add(reward);
            }
            milestonesMap.put(sm.getId(),rewards);
            mm.setRewards(rewards);
            milestones.add(mm);
            //System.out.println(mm);
        }
        return milestonesMap;
    }
    public void combineEvents(String lang){
        HashMap<Integer,List<RewardModel>> milestones = combineMilestones();
        for(int i = this.events.size()-1;i>=0;i--){
            Instant startTS = Instant.ofEpochMilli(events.get(i).getStartTS()*1000);
            Instant endTS = Instant.ofEpochMilli(events.get(i).getEndTS()*1000);
            System.out.print(Color.WHITE_UNDERLINED);
            System.out.println(getClientString(this.events.get(i).getName()));
            System.out.print(Color.RESET);
            System.out.print(Color.RED_BRIGHT);
            System.out.println(startTS.toString()+" - "+endTS.toString());
            System.out.print(Color.RESET);
            for(int j = 0; j<this.events.get(i).getTabs().size();j++){
                long tsts = events.get(i).getTabs().get(j).getStartTS();
                Instant tabStartTS = Instant.ofEpochMilli(events.get(i).getTabs().get(j).getStartTS()*1000);
                Instant tabEndTS = Instant.ofEpochMilli(events.get(i).getTabs().get(j).getEndTS()*1000);
                if(!this.events.get(i).getTabs().get(j).getName().isBlank()){
                    System.out.println("    "+this.events.get(i).getTabs().get(j).getName());
                }
                if(tsts>0){
                    System.out.print(Color.RED_BRIGHT);
                    System.out.println("    "+tabStartTS.toString()+" - "+tabEndTS.toString());
                    System.out.print(Color.RESET);
                }
                for(int k : this.events.get(i).getTabs().get(j).getTasks().keySet()){
                    System.out.println("    "+getTaskString(k,lang) + "="+ this.events.get(i).getTabs().get(j).getTasks().get(k));
                }
            }
            int finalI = i;
            List<RewardModel> milestone = milestones.get(this.events.get(finalI).getMilestones());
            if(milestone==null){
                continue;
            }
            System.out.println("    MILESTONES");
            for(RewardModel rm : milestone){
                System.out.println("     "+rm.getRequiredPoints() + ": " + rm.getItem() +" | "+ rm.getAmount());
            }
        }
        try {
            this.writer = new FileWriter("data/events.json");
            String json = mapper.writeValueAsString(this.events);
            JsonNode node = mapper.readValue(json,JsonNode.class);
            writer.write(node.toPrettyString());
            writer.flush();
            this.writer = new FileWriter("data/eventTasks.json");
            json = mapper.writeValueAsString(this.questTasks);
            node = mapper.readValue(json,JsonNode.class);
            writer.write(node.toPrettyString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getClientString(int id){
        for (StaticClientString c : this.clientStrings) {
            if (c.getId() == id) {
                return Optional.of(c).get().getEn();
            }
        }
        return "";
    }
    public String getStaticFile(int id){
        return this.staticFiles.stream().filter(s->s.getId()==id).findFirst().get().generateURL();
    }
    public StaticItemTemplate getItemTemplate(int id){
        return this.items.stream().filter(i->i.getId()==id).findFirst().get();
    }
    public String getItem(int id){
        return this.itemModels.stream().filter(i->i.getId()==id).findFirst().get().getName();
    }

    public String getTaskString(int id, String lang){
        return this.questTasks.stream().filter(q->q.getId()==id).findFirst().get().getTaskString(clientStrings,lang);
    }
    public StaticQuestTask getTask(int id){
        return this.questTasks.stream().filter(q->q.getId()==id).findFirst().get();
    }
    public void saveImages(String filePath) throws IOException {
        String[] wearables = new String[]{"talisman","shoes","gloves","helm","armor","off_hand","amulet","main_hand","ring"};
        List<String> wearableSlots = new ArrayList<>(Arrays.stream(wearables).collect(Collectors.toList()));
        for(StaticFile sf : this.staticFiles){
            System.out.println(sf.getId());
            if(sf.getId()<217582){
                break;
            }
            if(sf.generateURL()==null){
                continue;
            }
            String[] s = sf.generateURL().split("/");
            saveImage(sf.generateURL(),filePath+s[s.length-1]);
            /*if(s[s.length-1].contains("glyph")){
                saveImage(sf.generateURL(),filePath+"glyphs/"+s[s.length-1]);
            }else if(containsFromList(s[s.length-1],wearables)){
                saveImage(sf.generateURL(),filePath+"items/"+s[s.length-1]);
            }else if(s[s.length-1].contains("other")){
                saveImage(sf.generateURL(),filePath+"others/"+s[s.length-1]);
            }else{
                saveImage(sf.generateURL(),filePath+s[s.length-1]);
            }*/
            System.out.println(s[s.length-1]);
        }
    }
    public boolean containsFromList(String inputStr, String[] items) {
        return Arrays.stream(items).anyMatch(inputStr::contains);
    }
    public void saveImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }
}
