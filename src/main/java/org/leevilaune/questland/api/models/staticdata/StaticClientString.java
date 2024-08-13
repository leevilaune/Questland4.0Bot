package org.leevilaune.questland.api.models.staticdata;

public class StaticClientString {

    private int id;
    private String en;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    @Override
    public String toString() {
        return "StaticClientString{" +
                "id=" + id +
                ", en='" + en + '\'' +
                '}';
    }
}
