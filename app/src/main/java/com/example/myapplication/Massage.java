package com.example.myapplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

        String t = time.substring(time.indexOf(":")-2,time.indexOf(":")+2);

        int HH = Integer.parseInt(t.charAt(0)+""+t.charAt(1));

        if (HH > 12){
            HH = HH - 12;
            time = HH + t.substring(2,t.length())+" PM";
        }else if (HH == 12){
            time = HH + t.substring(2,t.length())+" PM";
        }else if (HH == 0){
            time = 12 + t.substring(2,t.length())+" AM";
        }else {
            time = HH + t.substring(2,t.length())+" AM";
        }

        //StringBuilder sb = new StringBuilder(time);
        //sb.delete(time.indexOf(":")+2,time.length());
        // new SimpleDateFormat ("hh:mm a", Locale.ENGLISH).format(new Date())

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
