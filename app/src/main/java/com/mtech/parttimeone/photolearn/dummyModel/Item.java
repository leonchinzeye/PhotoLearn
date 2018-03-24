package com.mtech.parttimeone.photolearn.dummyModel;

/**
 * Created by Zhikai on 17/3/2018.
 */

public class Item {
    private String SessionID;
    private String Title;
    private String Creator;
    private String Image;
    private String Description;
    private String Location;
    private String Date;
    private ItemType type;
    static public enum ItemType{QUIZ,LEARNING}

    public Item(String sessionID, String title, String creator, String image, String description, String location, String date, ItemType type) {
        SessionID = sessionID;
        Title = title;
        Creator = creator;
        Image = image;
        Description = description;
        Location = location;
        Date = date;
        this.type = type;
    }

    public String getSessionID() {
        return SessionID;
    }

    public void setSessionID(String sessionID) {
        SessionID = sessionID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
