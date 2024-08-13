package org.leevilaune.questland.api.models.staticdata;

public class StaticFile {

    private int id;
    private String name;
    private int itemID;
    private String hash;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String generateURL(){
        name.replace(" ","%20");
        if(this.hash.isBlank()){
            return null;
        }
        String url =  "https://storage.googleapis.com/ql4-files-eu/"+name+"_"+hash+".png";
        return url;
    }
}
