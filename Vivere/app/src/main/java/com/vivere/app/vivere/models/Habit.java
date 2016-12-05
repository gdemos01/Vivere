package com.vivere.app.vivere.models;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by kyria_000 on 26/11/2016.
 */

public class Habit {
    private String hname;
    private String type;
    private int daystogo;
    private int daysdone;
    private Timestamp timestamp;
    private Timestamp lastupdated;
    private String username;

    public Habit() {
        this.hname = null;
        this.type = null;
        this.daystogo = -1;
        this.daysdone = -1;
        this.timestamp = null;
        this.lastupdated = null;
        this.username = null;
        this.lastupdated= null;
    }

    public Habit(String hname, String type, int daystogo, int daysdone, Timestamp timestamp, Timestamp lastupdated, String username) {
        this.hname = hname;
        this.type = type;
        this.daystogo = daystogo;
        this.daysdone = daysdone;
        this.timestamp = timestamp;
        this.lastupdated = lastupdated;
        this.username = username;
    }


    public int calculatePercentage(){
        int per = (int)((float)daysdone/66.0*100);
        return per;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDaystogo() {
        return daystogo;
    }

    public void setDaystogo(int daystogo) {
        this.daystogo = daystogo;
    }

    public int getDaysdone() {
        return daysdone;
    }

    public void setDaysdone(int daysdone) {
        this.daysdone = daysdone;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Timestamp getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(Timestamp lastupdated) {
        this.lastupdated = lastupdated;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Habit{" +
                "hname='" + hname + '\'' +
                ", type='" + type + '\'' +
                ", daystogo=" + daystogo +
                ", daysdone=" + daysdone +
                ", timestamp=" + timestamp +
                ", lastupdated=" + lastupdated +
                ", username='" + username + '\'' +
                '}';
    }
}
