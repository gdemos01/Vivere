package com.vivere.app.vivere.models;

/**
 * Created by kyria_000 on 29/11/2016.
 */

public class Inheritance {
    private String iname;
    private String username;

    public Inheritance() {
        this.iname = null;
        this.username = null;
    }

    public Inheritance(String iname, String username) {
        this.iname = iname;
        this.username = username;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Inheritance{" +
                "iname='" + iname + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
