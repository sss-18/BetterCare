package com.spg.bettercareapp.model;

public class ResidentDetail {
    private String name;
    private String dateOfBirth;
    private String sex;
    private String roomNo;

    public ResidentDetail(String name, String dateOfBirth, String sex, String roomNo) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.roomNo = roomNo;
    }

    public ResidentDetail() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "ResidentDetail{" +
                "name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", sex='" + sex + '\'' +
                ", roomNo='" + roomNo + '\'' +
                '}';
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }
}
