package org.leevilaune.questland.api.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerRequest {
    @JsonProperty("red_id")
    private int reqId;
    private String platform;
    @JsonProperty("player_id")
    private int playerId;
    private String version;
    private String token;
    private String lang;
    private String task;

    public PlayerRequest(int reqId, String platform, int playerId, String version, String token, String lang, String task) {
        this.reqId = reqId;
        this.platform = platform;
        this.playerId = playerId;
        this.version = version;
        this.token = token;
        this.lang = lang;
        this.task = task;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "PlayerRequest{" +
                "reqId=" + reqId +
                ", platform='" + platform + '\'' +
                ", playerId=" + playerId +
                ", version='" + version + '\'' +
                ", token='" + token + '\'' +
                ", lang='" + lang + '\'' +
                ", task='" + task + '\'' +
                '}';
    }
}
