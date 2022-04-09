package com.spg.bettercareapp.model;

public class Meals {

    private boolean breakfast;
    private boolean snacks;
    private boolean lunch;
    private boolean dinner;

    public Meals(boolean breakfast, boolean snacks, boolean lunch, boolean dinner) {
        this.breakfast = breakfast;
        this.snacks = snacks;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public boolean isSnacks() {
        return snacks;
    }

    public void setSnacks(boolean snacks) {
        this.snacks = snacks;
    }

    public boolean isLunch() {
        return lunch;
    }

    public void setLunch(boolean lunch) {
        this.lunch = lunch;
    }

    public boolean isDinner() {
        return dinner;
    }

    public void setDinner(boolean dinner) {
        this.dinner = dinner;
    }
}
