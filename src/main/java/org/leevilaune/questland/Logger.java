package org.leevilaune.questland;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Logger {
    private String fileName;
    private Writer writer;
    public Logger(){
        this.fileName = "staticdata.txt";
        //this.fileName = "items.json";
        //this.fileName = "staticItems.json";
        try {
            this.writer = new FileWriter(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void log(String entry) {
        try {
            writer.append(entry + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}