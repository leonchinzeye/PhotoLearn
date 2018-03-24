package com.mtech.parttimeone.photolearn.data.entity;

/**
 * Created by karen on 23/3/2018.
 */

public class LearningItemEntity {
    private String titleId;
    private String title;
    private String photoURL;
    private String photoDesc;
    private String GPS;

    private void LearningItemEntity(String titleId, String title, String photoURL, String photoDesc, String GPS) {
        this.titleId = titleId;
        this.title = title;
        this.photoURL = photoURL;
        this.photoDesc = photoDesc;
        this.GPS = GPS;
    }

    public String getTitleId() { return titleId; }

    public void setTitleId(String titleId) { this.titleId = titleId; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getPhotoURL() { return photoURL; }

    public void setPhotoURL(String photoURL) { this.photoURL = photoURL; }

    public String getPhotoDesc() { return photoDesc; }

    public void setPhotoDesc(String photoDesc) { this.photoDesc = photoDesc; }

    public String getGPS() { return GPS; }

    public void setGPS(String GPS) { this.GPS = GPS; }
}
