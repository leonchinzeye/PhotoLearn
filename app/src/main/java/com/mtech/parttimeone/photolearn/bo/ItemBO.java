package com.mtech.parttimeone.photolearn.bo;

import java.math.BigInteger;

/**
 * @author Leon
 * @date 3/12/18
 */

public abstract class ItemBO {
    //Added by zhikai. Need an Item title.
    //Not sure what and how to use titleid
    //Where is the location field?
    private String itemtitle;
//    private String itemLocation;
//    private String itemDate;


    private BigInteger titleId;
    private String userID;
    private String photoURL;
    private String photoDesc;

    public ItemBO () {

    }

    public ItemBO(String itemtitle, String userID, String photoURL, String photoDesc) {
        this.itemtitle = itemtitle;
        this.userID = userID;
        this.photoURL = photoURL;
        this.photoDesc = photoDesc;
    }

    public BigInteger getTitleId() {
        return titleId;
    }

    public void setTitleId(BigInteger titleId) {
        this.titleId = titleId;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getPhotoDesc() {
        return photoDesc;
    }

    public void setPhotoDesc(String photoDesc) {
        this.photoDesc = photoDesc;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    //Added by Zhikai

    public String getItemtitle() {
        return itemtitle;
    }

    public void setItemtitle(String itemtitle) {
        this.itemtitle = itemtitle;
    }
}
