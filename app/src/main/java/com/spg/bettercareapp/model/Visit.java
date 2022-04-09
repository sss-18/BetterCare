package com.spg.bettercareapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Visit {
    @SerializedName("visit_id")
    private int visit_id;
    @SerializedName("date")
    private Date date;
    @SerializedName("resident_id")
    private int resident_id;
    @SerializedName("note")
    private String note;

    public int getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(int visit_id) {
        this.visit_id = visit_id;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
