package com.spg.bettercareapp.model;

import com.google.gson.annotations.SerializedName;

public class Care {
    @SerializedName("care_id")
    private int care_id;
    @SerializedName("user_name")
    private String user_name;
    @SerializedName("password")
    private String password;

    public int getCare_id() {
        return care_id;
    }

    public void setCare_id(int care_id) {
        this.care_id = care_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
