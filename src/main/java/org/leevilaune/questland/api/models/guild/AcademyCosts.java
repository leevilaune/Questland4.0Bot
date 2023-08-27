package org.leevilaune.questland.api.models.guild;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AcademyCosts {

    private List<AcademyCostItem> levels;

    public AcademyCosts(){
        this.levels = new ArrayList<>();
        load();
    }

    private void load(){
        try(Scanner s = new Scanner(new File("src/main/resources/academy_bonuses.csv"))){
            s.next();
            while (s.hasNext()){
                String[] parts = s.next().split(",");
                levels.add(new AcademyCostItem(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2])));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<AcademyCostItem> getLevels() {
        return levels;
    }

    public void setLevels(List<AcademyCostItem> levels) {
        this.levels = levels;
    }
}
