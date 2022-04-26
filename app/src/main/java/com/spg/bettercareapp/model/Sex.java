package com.spg.bettercareapp.model;

import java.util.ArrayList;
import java.util.List;

public class Sex {
    private String sex;
    private int id;
    public static List<Sex> gender = new ArrayList<>();
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Sex(String sex, int id) {
        this.sex = sex;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static  void initGender(){
        gender.add(new Sex("Male",1));
        gender.add(new Sex("Female",0));
        gender.add(new Sex("Choose not to say",2));
    }

    public static List<Sex> getGender() {
        return gender;
    }

    public static void setGender(List<Sex> gender) {
        Sex.gender = gender;
    }
}
