package com.spg.bettercareapp.model;

public class ResidentRecord {
    private PersonalCares mPersonalCares;
    private Meals mMeals;
    private String fluid;
    private String activityRecord;
    private String accidentRecord;
    private String moodRecord;
    private String visitationRecord;
    private String weaklyCatalogueRecord;
    private String commentsRecord;

    public ResidentRecord(PersonalCares personalCares, Meals meals, String fluid, String activityRecord, String accidentRecord, String moodRecord, String visitationRecord, String weaklyCatalogueRecord, String commentsRecord) {
        this.mPersonalCares = personalCares;
        this.mMeals = meals;
        this.fluid = fluid;
        this.activityRecord = activityRecord;
        this.accidentRecord = accidentRecord;
        this.moodRecord = moodRecord;
        this.visitationRecord = visitationRecord;
        this.weaklyCatalogueRecord = weaklyCatalogueRecord;
        this.commentsRecord = commentsRecord;
    }

    public PersonalCares getPersonalCare() {
        return mPersonalCares;
    }

    public void setPersonalCare(PersonalCares personalCares) {
        this.mPersonalCares = personalCares;
    }

    public Meals getMeal() {
        return mMeals;
    }

    public void setMeal(Meals meals) {
        this.mMeals = meals;
    }

    public String getFluid() {
        return fluid;
    }

    public void setFluid(String fluid) {
        this.fluid = fluid;
    }

    public String getActivityRecord() {
        return activityRecord;
    }

    public void setActivityRecord(String activityRecord) {
        this.activityRecord = activityRecord;
    }

    public String getAccidentRecord() {
        return accidentRecord;
    }

    public void setAccidentRecord(String accidentRecord) {
        this.accidentRecord = accidentRecord;
    }

    public String getMoodRecord() {
        return moodRecord;
    }

    public void setMoodRecord(String moodRecord) {
        this.moodRecord = moodRecord;
    }

    public String getVisitationRecord() {
        return visitationRecord;
    }

    public void setVisitationRecord(String visitationRecord) {
        this.visitationRecord = visitationRecord;
    }

    public String getWeaklyCatalogueRecord() {
        return weaklyCatalogueRecord;
    }

    public void setWeaklyCatalogueRecord(String weaklyCatalogueRecord) {
        this.weaklyCatalogueRecord = weaklyCatalogueRecord;
    }

    public String getCommentsRecord() {
        return commentsRecord;
    }

    public void setCommentsRecord(String commentsRecord) {
        this.commentsRecord = commentsRecord;
    }
}
