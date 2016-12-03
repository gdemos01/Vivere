package com.vivere.app.vivere.models;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by georg on 11-Nov-16.
 */

public class Appointment {

    private String doctor;
    private String patient;
    private String doctorName;
    private Timestamp date;
    private String description;

    public Appointment() {
        this.doctorName=null;
        this.doctor = null;
        this.patient = null;
        this.date = null;
        this.description = null;
    }

    public Appointment(String doctor, String patient, Timestamp date, String description,String d) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.description = description;
        this.doctorName = d;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
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

    public void setDescription(String description) {
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "doctor='" + doctor + '\'' +
                ", patient='" + patient + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
