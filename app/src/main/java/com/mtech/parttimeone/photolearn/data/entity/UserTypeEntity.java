package com.mtech.parttimeone.photolearn.data.entity;

/**
 * Created by karen on 25/3/2018.
 */

public class UserTypeEntity {
    private String userId;
    private String key; //sessionId for sessions or titleId for titles

    private UserTypeEntity (String userId, String key) {
        this.userId = userId;
        this.key = key;
    }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public String getKey() { return key; }

    public void setKey(String key) {this.key = key; }
}
