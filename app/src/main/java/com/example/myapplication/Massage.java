package com.example.myapplication;

public class Massage {

    private int time;
    private String massage;
    private String UserID;

    public Massage() {
    }

    public Massage(int time, String massage,String UserID) {
        this.time = time;
        this.massage = massage;
        this.UserID = UserID;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }
}
