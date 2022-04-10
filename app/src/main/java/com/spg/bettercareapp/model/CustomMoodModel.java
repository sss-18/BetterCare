package com.spg.bettercareapp.model;

public class CustomMoodModel {
    private String mood;
    private int moodId;

    public CustomMoodModel(String mood, int moodId) {
        this.mood = mood;
        this.moodId = moodId;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public int getMoodId() {
        return moodId;
    }

    public void setMoodId(int moodId) {
        this.moodId = moodId;
    }
}
