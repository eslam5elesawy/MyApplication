package com.example.myapplication;

public class Massage {

    private String fullTime;
    private String massage;
    private String UserID;

    public Massage() {
    }

    public Massage(String fullTime, String massage, String UserID) {
        this.fullTime = fullTime;
        this.massage = massage;
        this.UserID = UserID;
    }

    public String getFullTime() {

        return fullTime;
    }

    public void setFullTime(String fullTime) {
        this.fullTime = fullTime;
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

    public String show() {
        return (getMassage() + "  " + getFullTime() + " " + getUserID());
    }

    public String formatTime() {
        String time=fullTime;
        String t = time.substring(time.indexOf(":") - 2, time.indexOf(":") + 3);

        int HH = Integer.parseInt(t.charAt(0) + "" + t.charAt(1));

        if (HH > 12) {
            HH = HH - 12;
            time = HH + t.substring(2, t.length()) + " PM";
        } else if (HH == 12) {
            time = HH + t.substring(2, t.length()) + " PM";
        } else if (HH == 0) {
            time = 12 + t.substring(2, t.length()) + " AM";
        } else {
            time = HH + t.substring(2, t.length()) + " AM";
        }

        //StringBuilder sb = new StringBuilder(time);
        //sb.delete(time.indexOf(":")+2,time.length());
        // new SimpleDateFormat ("hh:mm a", Locale.ENGLISH).format(new Date())
        return time;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }
}
