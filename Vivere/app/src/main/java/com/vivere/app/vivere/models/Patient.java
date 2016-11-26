package com.vivere.app.vivere.models;

/**
 * Created by kyria_000 on 26/11/2016.
 */

public class Patient {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String gender;
    private String nationality;
    private String coutnry;
    private int age;

    public Patient() {
        this.username = null;
        this.password = null;
        this.name = null;
        this.surname = null;
        this.gender = null;
        this.nationality = null;
        this.coutnry = null;
        this.age = -1;
    }

    public Patient(String username, String password, String name, String surname, String gender, String nationality, String coutnry, int age) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.nationality = nationality;
        this.coutnry = coutnry;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCoutnry() {
        return coutnry;
    }

    public void setCoutnry(String coutnry) {
        this.coutnry = coutnry;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", nationality='" + nationality + '\'' +
                ", coutnry='" + coutnry + '\'' +
                ", age=" + age +
                '}';
    }
}
