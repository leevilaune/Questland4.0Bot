package org.leevilaune.questland.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.leevilaune.questland.api.models.domain.Item;

public class ItemClient {


    private String itemSrc;
    private String colItemSrc;
    private String weaponSrc;
    private List<Item> itemList;
    private List<Item> weapons;
    private boolean isLoaded;

    public ItemClient(){
        //itemSrc = "https://docs.google.com/spreadsheets/d/e/2PACX-1vQACdbvpCIg7Uri2UZ_ZpoPLqEQzB0tWtnf8J8awM7s7DwvZQkoet1V-8TYyEKYPPo_CtU4QdtQDHxo/pub?gid=599842681&single=true&output=csv";
        itemSrc = "https://docs.google.com/spreadsheets/d/e/2PACX-1vQACdbvpCIg7Uri2UZ_ZpoPLqEQzB0tWtnf8J8awM7s7DwvZQkoet1V-8TYyEKYPPo_CtU4QdtQDHxo/pub?gid=1747573334&single=true&output=csv";
        colItemSrc = "https://docs.google.com/spreadsheets/d/e/2PACX-1vQACdbvpCIg7Uri2UZ_ZpoPLqEQzB0tWtnf8J8awM7s7DwvZQkoet1V-8TYyEKYPPo_CtU4QdtQDHxo/pub?gid=1120254239&single=true&output=csv";
        weaponSrc = "https://docs.google.com/spreadsheets/d/e/2PACX-1vQACdbvpCIg7Uri2UZ_ZpoPLqEQzB0tWtnf8J8awM7s7DwvZQkoet1V-8TYyEKYPPo_CtU4QdtQDHxo/pub?gid=1278893465&single=true&output=csv";

        itemList = new ArrayList<>();
        weapons = new ArrayList<>();
        isLoaded = false;
        try {
            load();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void UrlInfo(String url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection)new URL(url).openConnection();
        for(int i=1;i<=25;i++){
            System.out.println(urlConnection.getHeaderFieldKey(i)+" = "+urlConnection.getHeaderField(i));
        }
    }
    public void load() throws FileNotFoundException {
        int index = 0;
        try{
            URL url=new URL(itemSrc);
            URL url2 = new URL(colItemSrc);
            Scanner scanner = new Scanner(url.openStream());
            Scanner scanner2 = new Scanner(url2.openStream());
            while(scanner.hasNextLine()){
                String[] parts = scanner.nextLine().split(",");
                //System.out.println(parts[1]);

                if(index==0){
                    index++;
                    continue;
                }
                if(parts[2].equalsIgnoreCase("DEFENSE")){
                    parts[2] = "DEFENCE";
                    //System.out.println(parts[2]);
                }
                Item i = InitItem(parts);
                itemList.add(i);
                index++;
            }
            index = 0;
            while(scanner2.hasNextLine()) {
                String[] parts = scanner2.nextLine().split(",");
                //System.out.println(parts[1]);

                if (index == 0) {
                    index++;
                    continue;
                }
                if (parts[2].equalsIgnoreCase("DEFENSE")) {
                    parts[2] = "DEFENCE";
                    //System.out.println(parts[2]);
                }
                Item i = InitItem(parts);
                itemList.add(i);
                index++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        loadWeapons("weaponData.csv");
        isLoaded = true;
        for (Item i : itemList){
            //System.out.println(i);
        }

    }
    public Item InitItem(String[] parts){

        if(parts.length==16){
            String iconLink = "";
            String fullLink = "";
            if(parts[15].equalsIgnoreCase("new")){
                iconLink = "https://storage.googleapis.com/ql4-files-eu/"+parts[13]+".png";
                fullLink = "https://storage.googleapis.com/ql4-files-eu/"+parts[14]+".png";
            }
            return new Item(parts[0].toUpperCase(),
                    parts[1].toUpperCase(),
                    parts[2].toUpperCase(),
                    parts[3].toUpperCase(),
                    Integer.parseInt(parts[4]),
                    Integer.parseInt(parts[5]),
                    Integer.parseInt(parts[6]),
                    Integer.parseInt(parts[7]),
                    Integer.parseInt(parts[8]),
                    parts[9].toUpperCase(),
                    parts[10].toUpperCase(),
                    parts[11].toUpperCase(),
                    parts[12],
                    iconLink,
                    fullLink);
        }else{
            return new Item(parts[0].toUpperCase(),
                    parts[1].toUpperCase(),
                    parts[2].toUpperCase(),
                    parts[3].toUpperCase(),
                    Integer.parseInt(parts[4]),
                    Integer.parseInt(parts[5]),
                    Integer.parseInt(parts[6]),
                    Integer.parseInt(parts[7]),
                    Integer.parseInt(parts[8]),
                    parts[9].toUpperCase(),
                    parts[10].toUpperCase(),
                    parts[11].toUpperCase(),
                    parts[12]);
        }
    }
    public void loadWeapons(String file) throws FileNotFoundException {
        int index = 0;
        try{
            URL url=new URL(weaponSrc);
            Scanner scanner = new Scanner(url.openStream());
            while(scanner.hasNextLine()){
                String[] parts = scanner.nextLine().split(",");
                //System.out.println(parts[1]);

                if(index==0){
                    index++;
                    continue;
                }
                if(parts[2].equalsIgnoreCase("DEFENSE")){
                    parts[2] = "DEFENCE";
                    //System.out.println(parts[2]);
                }
                Item i = new Item(parts[0].toUpperCase(),
                        parts[1].toUpperCase(),
                        null,
                        parts[2].toUpperCase(),
                        Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4]),
                        Integer.parseInt(parts[5]),
                        Integer.parseInt(parts[6]),
                        Integer.parseInt(parts[7]),
                        null,
                        null,
                        null,
                        null);
                itemList.add(i);
                index++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        for (Item i : weapons){
            //System.out.println(i);
        }

    }
    public Item getItem(String name){
        for(Item i : itemList){
            if(i.getName().equalsIgnoreCase(name)){
                return i;
            }
        }
        for(Item i : itemList){
            if(i.getIdentifier().equalsIgnoreCase(name)){
                return i;
            }
        }
        return null;
    }
    private String splitString(String s){
        String[] parts = s.split("-");

        if(parts.length < 2){
            return parts[0].toUpperCase();
        }

        return parts[1].toUpperCase();
    }

    public List<Item> getItemList() {
        return itemList;
    }
    public List<String> getItemNames(){
        List<String> itemNames = new ArrayList<>();
        for (Item i : itemList){
            itemNames.add(i.getName());
        }
        return itemNames;
    }
}
