package com.vivere.app.vivere.models;

/**
 * Created by kyria_000 on 26/11/2016.
 */

public class MedicalSpecialist {
    private String msusername;
    private String password;
    private String name;
    private String surname;
    private String speciality;
    private String address;
    private int telephone;
    private String type;

    public MedicalSpecialist() {
    }

    public MedicalSpecialist(String msusername, String password, String name, String surname, String speciality, String address, int telephone, String type) {
        this.msusername = msusername;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
        this.address = address;
        this.telephone = telephone;
        this.type = type;
    }

    public String getMsusername() {
        return msusername;
    }

    public void setMsusername(String msusername) {
        this.msusername = msusername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MedicalSpecialist{" +
                "msusername='" + msusername + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", speciality='" + speciality + '\'' +
                ", address='" + address + '\'' +
                ", telephone=" + telephone +
                ", type='" + type + '\'' +
                '}';
    }
}
