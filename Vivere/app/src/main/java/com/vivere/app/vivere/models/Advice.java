package com.vivere.app.vivere.models;

import java.sql.Timestamp;

/**
 * Created by Giorgos on 25-Nov-16.
 */

public class Advice {
    private String doctor;
    private String patient;
    private Timestamp date;
    private String type;
    private String results;
    private String advice;
    private String doctorName;
    private int examId;

    public Advice(){

    }

    public Advice(String doctor, String patient, Timestamp date, String type, String results, String advice, String doctorName, int examId) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.type = type;
        this.results = results;
        this.advice = advice;
        this.doctorName = doctorName;
        this.examId = examId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
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

    public Timestamp getDate() {
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

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setResults(String results) {
        this.results = results;
    }
}
