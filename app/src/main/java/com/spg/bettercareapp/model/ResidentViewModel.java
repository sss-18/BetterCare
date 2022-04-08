package com.spg.bettercareapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.spg.bettercareapp.views.IResidentViewModel;

public class ResidentViewModel implements IResidentViewModel, Parcelable {
    private String name;
    private String age;
    private String careType;

    public ResidentViewModel(String name, String age, String careType) {
        this.name = name;
        this.age = age;
        this.careType = careType;
    }

    public ResidentViewModel(String name, String age, String careType, RowType rowType) {
        this.name = name;
        this.age = age;
        this.careType = careType;
        this.rowType = rowType;
    }

    private RowType rowType;

    protected ResidentViewModel(Parcel in) {
        name = in.readString();
        age = in.readString();
        careType = in.readString();
    }

    public static final Creator<ResidentViewModel> CREATOR = new Creator<ResidentViewModel>() {
        @Override
        public ResidentViewModel createFromParcel(Parcel in) {
            return new ResidentViewModel(in);
        }

        @Override
        public ResidentViewModel[] newArray(int size) {
            return new ResidentViewModel[size];
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
    }
}
