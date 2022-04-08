package com.spg.bettercareapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Mood {
    @SerializedName("mood_id")
    private int mood_id;
    @SerializedName("date")
    private Date date;
    @SerializedName("resident_id")
    private int resident_id;
    @SerializedName("mood_type")
    private String mood_type;

    public int getMood_id() {
        return mood_id;
    }

    public void setMood_id(int mood_id) {
        this.mood_id = mood_id;
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

    public String getMood_type() {
        return mood_type;
    }

    public void setMood_type(String mood_type) {
        this.mood_type = mood_type;
    }
}
