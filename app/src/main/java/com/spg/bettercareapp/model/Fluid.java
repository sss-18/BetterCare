package com.spg.bettercareapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Fluid {
    @SerializedName("dailyactivity_id")
    private int fluid_id;
    @SerializedName("date")
    private Date date;
    @SerializedName("resident_id")
    private int resident_id;
    @SerializedName("150ml")
    private int ml150;
    @SerializedName("250ml")
    private int ml250;
    @SerializedName("350ml")
    private int ml350;
    @SerializedName("more")
    private int more;

    public int getFluid_id() {
        return fluid_id;
    }

    public void setFluid_id(int fluid_id) {
        this.fluid_id = fluid_id;
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

    public int getMl150() {
        return ml150;
    }

    public void setMl150(int ml150) {
        this.ml150 = ml150;
    }

    public int getMl250() {
        return ml250;
    }

    public void setMl250(int ml250) {
        this.ml250 = ml250;
    }

    public int getMl350() {
        return ml350;
    }

    public void setMl350(int ml350) {
        this.ml350 = ml350;
    }

    public int getMore() {
        return more;
    }

    public void setMore(int more) {
        this.more = more;
    }
}
