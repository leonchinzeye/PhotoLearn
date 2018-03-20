package com.mtech.parttimeone.photolearn.domain;

public class Account {
    private String userUid;
    private String name;
    private String email;
    private String lastActive;

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getUserUid() {
        return userUid;
    }

    public Account(){

    }

    public Account(String userUid, String name, String email, String lastActive) {
        this.userUid = userUid;
        this.name = name;
        this.email = email;
        this.lastActive = lastActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = email;
    }

    public String getLastActive() {
        return lastActive;
    }

    public void setLastActive(String lastActive) {
        this.lastActive = lastActive;
    }
}
