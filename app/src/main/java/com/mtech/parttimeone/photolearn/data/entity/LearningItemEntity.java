package com.mtech.parttimeone.photolearn.data.entity;

/**
 * Created by karen on 23/3/2018.
 */

public class LearningItemEntity {
    private String titleId;
    private String userId;
    private String title;
    private String photoURL;
    private String itemDesc;
    private String GPS;

    private void LearningItemEntity(String titleId, String userId, String title, String photoURL, String itemDesc, String GPS) {
        this.titleId = titleId;
        this.userId = userId;
        this.title = title;
        this.photoURL = photoURL;
        this.itemDesc = itemDesc;
        this.GPS = GPS;
    }

    public String getTitleId() { return titleId; }

    public void setTitleId(String titleId) { this.titleId = titleId; }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getPhotoURL() { return photoURL; }

    public void setPhotoURL(String photoURL) { this.photoURL = photoURL; }

    public String getItemDesc() { return itemDesc; }

    public void setItemDesc(String itemDesc) { this.itemDesc = itemDesc; }

    public String getGPS() { return GPS; }

    public void setGPS(String GPS) { this.GPS = GPS; }
}
