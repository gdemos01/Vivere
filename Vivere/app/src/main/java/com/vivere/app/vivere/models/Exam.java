package com.vivere.app.vivere.models;

import java.sql.Timestamp;

/**
 * Created by kyria_000 on 24/11/2016.
 */

public class Exam {

    private int id;
    private String type;
    private String results;
    private Timestamp timestamp;
    private String advice;
    private String username;
    private String msusername;

    public Exam() {
        this.id = -1;
        this.type = null;
        this.results = null;
        this.timestamp = null;
        this.advice = null;
        this.username = null;
        this.msusername = null;
    }

    public Exam(int id, String type, String results, Timestamp timestamp, String advice, String username, String msusername) {
        this.id = id;
        this.type = type;
        this.results = results;
        this.timestamp = timestamp;
        this.advice = advice;
        this.username = username;
        this.msusername = msusername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMsusername() {
        return msusername;
    }

    public void setMsusername(String msusername) {
        this.msusername = msusername;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", results='" + results + '\'' +
                ", timestamp=" + timestamp +
                ", advice='" + advice + '\'' +
                ", username='" + username + '\'' +
                ", msusername='" + msusername + '\'' +
                '}';
    }
}
