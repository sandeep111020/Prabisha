package com.example.dailyreportapp.Models;

public class userprofile {


    public String username;
    public String userUID;



    public String token;

    public userprofile() {
    }

    public userprofile(String username, String userUID, String token) {
        this.username = username;
        this.userUID = userUID;
        this.token=token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }
}