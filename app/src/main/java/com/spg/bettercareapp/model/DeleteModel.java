package com.spg.bettercareapp.model;

import com.google.gson.annotations.SerializedName;

public class DeleteModel {
    @SerializedName("count")
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
