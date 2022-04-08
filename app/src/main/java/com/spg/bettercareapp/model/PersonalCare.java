package com.spg.bettercareapp.model;

public class PersonalCare {
    private boolean bathing;
    private boolean skinCare;
    private boolean oralCare;
    private boolean dressing;
    private int padChange;
    private boolean grooming;

    public PersonalCare(boolean bathing, boolean skinCare, boolean oralCare, boolean dressing, int padChange, boolean grooming) {
        this.bathing = bathing;
        this.skinCare = skinCare;
        this.oralCare = oralCare;
        this.dressing = dressing;
        this.padChange = padChange;
        this.grooming = grooming;
    }

    public boolean isBathing() {
        return bathing;
    }

    public void setBathing(boolean bathing) {
        this.bathing = bathing;
    }

    public boolean isSkinCare() {
        return skinCare;
    }

    public void setSkinCare(boolean skinCare) {
        this.skinCare = skinCare;
    }

    public boolean isOralCare() {
        return oralCare;
    }

    public void setOralCare(boolean oralCare) {
        this.oralCare = oralCare;
    }

    public boolean isDressing() {
        return dressing;
    }

    public void setDressing(boolean dressing) {
        this.dressing = dressing;
    }

    public int getPadChange() {
        return padChange;
    }

    public void setPadChange(int padChange) {
        this.padChange = padChange;
    }

    public boolean isGrooming() {
        return grooming;
    }

    public void setGrooming(boolean grooming) {
        this.grooming = grooming;
    }
}
