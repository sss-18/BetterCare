package com.spg.bettercareapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class OtherComment {
    @SerializedName("othercomment_id")
    private int othercomment_id;
    @SerializedName("date")
    private Date date;
    @SerializedName("resident_id")
    private int resident_id;
    @SerializedName("note")
    private String note;

    public int getOthercomment_id() {
        return othercomment_id;
    }

    public void setOthercomment_id(int othercomment_id) {
        this.othercomment_id = othercomment_id;
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
