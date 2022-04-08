package com.spg.bettercareapp.model;

import com.google.gson.annotations.SerializedName;

public class Admin {
    @SerializedName("admin_id")
    private int admin_id;
    @SerializedName("user_name")
    private String user_name;
    @SerializedName("password")
    private String password;

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
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
