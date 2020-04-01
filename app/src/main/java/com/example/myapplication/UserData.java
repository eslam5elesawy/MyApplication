package com.example.myapplication;

public class UserData {

    private String userID;
    private String name;
    private String photo;

    public UserData() {
    }

    public UserData(String userID, String name, String photo) {
        this.userID = userID;
        this.name = name;
        this.photo = photo;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


}
