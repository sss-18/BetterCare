package com.spg.bettercareapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Resident {
    @SerializedName("resident_id")
    private int resident_id;
    @SerializedName("name")
    private String name;
    @SerializedName("dob")
    private Date dob;
    @SerializedName("care_type")
    private String care_type;
    @SerializedName("sex")
    private String sex;
    @SerializedName("room_no")
    private int room_no;

    public int getResident_id() {
        return resident_id;
    }

    public void setResident_id(int resident_id) {
        this.resident_id = resident_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCare_type() {
        return care_type;
    }

    public void setCare_type(String care_type) {
        this.care_type = care_type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getRoom_no() {
        return room_no;
    }

    public void setRoom_no(int room_no) {
        this.room_no = room_no;
    }
}
