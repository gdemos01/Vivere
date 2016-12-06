package com.vivere.app.vivere.models;

import java.sql.Date;

/**
 * Created by kyria_000 on 24/11/2016.
 */

public class Medication {
    private String name;
    private int duration;
    private String frequency;
    private int dose;
    private int timestaken;
    //private Date lastupdated;
    private String username;

    public Medication() {
        this.name = null;
        this.duration = -1;
        this.frequency = null;
        this.dose = -1;
        this.timestaken = -1;
        //this.lastupdated = null;
        this.username = null;
    }

    public Medication(String name, int duration, String frequency, int dose, int timestaken, Date lastupdated, String username) {
        this.name = name;
        this.duration = duration;
        this.frequency = frequency;
        this.dose = dose;
        this.timestaken = timestaken;
        //this.lastupdated = lastupdated;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public int getTimestaken() {
        return timestaken;
    }

    public void setTimestaken(int timestaken) {
        this.timestaken = timestaken;
    }

//    public Date getLastupdated() {
//        return lastupdated;
//    }
//
//    public void setLastupdated(Date lastupdated) {
//        this.lastupdated = lastupdated;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", frequency='" + frequency + '\'' +
                ", dose=" + dose +
                ", timestaken=" + timestaken +
                ", username='" + username + '\'' +
                '}';
    }
}
