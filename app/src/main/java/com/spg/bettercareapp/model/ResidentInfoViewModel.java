package com.spg.bettercareapp.model;

import com.spg.bettercareapp.views.IResidentViewModel;

public class ResidentInfoViewModel implements IResidentViewModel {

    private String name;
    private String age;
    private String careType;

    private RowType rowType;

    public ResidentInfoViewModel(String name, String age, String careType, RowType rowType) {
        this.name = name;
        this.age = age;
        this.careType = careType;
        this.rowType = rowType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCareType() {
        return careType;
    }

    public void setCareType(String careType) {
        this.careType = careType;
    }

    @Override
    public void set(RowType rowType) {
        this.rowType = rowType;
    }

    @Override
    public RowType get() {
        return this.rowType;
    }
}
