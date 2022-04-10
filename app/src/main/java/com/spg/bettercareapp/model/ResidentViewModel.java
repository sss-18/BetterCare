package com.spg.bettercareapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.spg.bettercareapp.views.IResidentViewModel;

public class ResidentViewModel implements IResidentViewModel, Parcelable {
    private String name;
    private String age;
    private String careType;
    private int id;
    private String sex;
    private int roomNo;

    public ResidentViewModel(String name, String age, String careType, int id, String sex, int roomNo, RowType rowType) {
        this.name = name;
        this.age = age;
        this.careType = careType;
        this.id = id;
        this.sex = sex;
        this.roomNo = roomNo;
        this.rowType = rowType;
    }

    private RowType rowType;

    public ResidentViewModel(String name, String age, String careType, int id, RowType rowType) {
        this.name = name;
        this.age = age;
        this.careType = careType;
        this.id = id;
        this.rowType = rowType;
    }

    public ResidentViewModel(String name, String age, String careType, RowType rowType) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    protected ResidentViewModel(Parcel in) {
        name = in.readString();
        age = in.readString();
        careType = in.readString();
        id = in.readInt();
        sex = in.readString();
        roomNo = in.readInt();
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
        parcel.writeString(sex);
        parcel.writeInt(roomNo);
    }
}
