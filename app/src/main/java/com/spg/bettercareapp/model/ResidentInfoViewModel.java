package com.spg.bettercareapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.spg.bettercareapp.views.IResidentViewModel;

public class ResidentInfoViewModel implements IResidentViewModel, Parcelable {

    private String name;
    private String age;
    private String careType;
    private int id;
    private RowType rowType;

    public ResidentInfoViewModel(String name, String age, String careType, int id) {
        this.name = name;
        this.age = age;
        this.careType = careType;
        this.id = id;
    }

    public ResidentInfoViewModel(String name, String age, String careType, int id, RowType rowType) {
        this.name = name;
        this.age = age;
        this.careType = careType;
        this.id = id;
        this.rowType = rowType;
    }

    protected ResidentInfoViewModel(Parcel in) {
        name = in.readString();
        age = in.readString();
        careType = in.readString();
        id = in.readInt();
    }

    public static final Creator<ResidentInfoViewModel> CREATOR = new Creator<ResidentInfoViewModel>() {
        @Override
        public ResidentInfoViewModel createFromParcel(Parcel in) {
            return new ResidentInfoViewModel(in);
        }

        @Override
        public ResidentInfoViewModel[] newArray(int size) {
            return new ResidentInfoViewModel[size];
        }
    };

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void set(RowType rowType) {
        this.rowType = rowType;
    }

    @Override
    public RowType get() {
        return rowType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(age);
        parcel.writeString(careType);
        parcel.writeInt(id);
    }
}
