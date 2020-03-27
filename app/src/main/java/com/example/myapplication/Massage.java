package com.example.myapplication;

public class Massage {

    int time;
    String massage;

    public Massage() {
    }

    public Massage(int time, String massage) {
        this.time = time;
        this.massage = massage;
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
}
