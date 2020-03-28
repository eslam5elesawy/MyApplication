package com.example.myapplication;

public class Massage {

    private String time;
    private String massage;
    private String UserID;

    public Massage() {
    }

    public Massage(String time, String massage,String UserID) {
        this.time = time;
        this.massage = massage;
        this.UserID = UserID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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
