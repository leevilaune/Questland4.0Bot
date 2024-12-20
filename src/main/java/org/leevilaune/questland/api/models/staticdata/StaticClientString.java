package org.leevilaune.questland.api.models.staticdata;

public class StaticClientString {

    private int id;
    private String en;
    private String it;

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

    public String getIt() {
        return it;
    }

    public void setIt(String it){
        this.it = it;
    }

    @Override
    public String toString() {
        return "StaticClientString{" +
                "id=" + id +
                ", en='" + en + '\'' +
                '}';
    }
}
