package com.spg.bettercareapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PersonalCare {
    @SerializedName("personal_id")
    private int personal_id;
    @SerializedName("date")
    private Date date;
    @SerializedName("accident_id")
    private int resident_id;
    @SerializedName("bathing")
    private int bathing;
    @SerializedName("skin_care")
    private int skin_care;
    @SerializedName("oral_care")
    private int oral_care;
    @SerializedName("dressing")
    private int dressing;
    @SerializedName("pad_change")
    private int pad_change;
    @SerializedName("hair_care")
    private int hair_care;

    public int getPersonal_id() {
        return personal_id;
    }

    public void setPersonal_id(int personal_id) {
        this.personal_id = personal_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getResident_id() {
        return resident_id;
    }

    public void setResident_id(int resident_id) {
        this.resident_id = resident_id;
    }

    public int getBathing() {
        return bathing;
    }

    public void setBathing(int bathing) {
        this.bathing = bathing;
    }

    public int getSkin_care() {
        return skin_care;
    }

    public void setSkin_care(int skin_care) {
        this.skin_care = skin_care;
    }

    public int getOral_care() {
        return oral_care;
    }

    public void setOral_care(int oral_care) {
        this.oral_care = oral_care;
    }

    public int getDressing() {
        return dressing;
    }

    public void setDressing(int dressing) {
        this.dressing = dressing;
    }

    public int getPad_change() {
        return pad_change;
    }

    public void setPad_change(int pad_change) {
        this.pad_change = pad_change;
    }

    public int getHair_care() {
        return hair_care;
    }

    public void setHair_care(int hair_care) {
        this.hair_care = hair_care;
    }
}
