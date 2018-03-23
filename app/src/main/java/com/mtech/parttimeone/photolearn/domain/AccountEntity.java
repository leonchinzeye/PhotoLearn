package com.mtech.parttimeone.photolearn.domain;

public class AccountEntity {
    private String name;
    private String email;
    private String lastActive;

    public AccountEntity(String name, String email, String lastActive) {
        this.name = name;
        this.email = email;
        this.lastActive = lastActive;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getUserUid() {
        return userUid;
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
