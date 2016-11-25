package com.vivere.app.vivere.models;

import java.util.Date;

/**
 * Created by georg on 25-Nov-16.
 */

public class Advice {
    private String doctor;
    private String patient;
    private Date date;
    private String type;
    private String results;
    private String advice;

    public Advice(){

    }

    public Advice(String doctor, String patient, Date date, String type, String results, String advice) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.type = type;
        this.results = results;
        this.advice = advice;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getPatient() {
        return patient;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getResults() {
        return results;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setResults(String results) {
        this.results = results;
    }
}
