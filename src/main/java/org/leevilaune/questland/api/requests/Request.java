package org.leevilaune.questland.api.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {

    @JsonProperty("token")
    protected String token;
    @JsonProperty("red_id")
    protected int reqId;
    @JsonProperty("platform")
    protected String platform;
    @JsonProperty("version")
    protected String version;
    @JsonProperty("task")
    protected String task;
    @JsonProperty("lang")
    protected String lang;

    public Request(String token, int reqId, String platform, String version, String task, String lang) {
        this.token = token;
        this.reqId = reqId;
        this.platform = platform;
        this.version = version;
        this.task = task;
        this.lang = lang;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "Request{" +
                "token='" + token + '\'' +
                ", reqId='" + reqId + '\'' +
                ", platform='" + platform + '\'' +
                ", version='" + version + '\'' +
                ", task='" + task + '\'' +
                ", lang='" + lang + '\'' +
                '}';
    }
}
