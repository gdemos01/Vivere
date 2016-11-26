package com.vivere.app.vivere.models;

/**
 * Created by kyria_000 on 26/11/2016.
 */

public class Illness {
    private String iname;
    private String factor;
    private String value;

    public Illness() {
        this.iname = null;
        this.factor = null;
        this.value = null;
    }

    public Illness(String iname, String factor, String value) {
        this.iname = iname;
        this.factor = factor;
        this.value = value;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Illness{" +
                "iname='" + iname + '\'' +
                ", factor='" + factor + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
