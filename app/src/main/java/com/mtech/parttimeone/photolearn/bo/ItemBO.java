package com.mtech.parttimeone.photolearn.bo;

import java.math.BigInteger;

/**
 * @author Leon
 * @date 3/12/18
 */

public abstract class ItemBO {

    private BigInteger titleId;
    private String userID;
    private String photoURL;
    private String photoDesc;

    public ItemBO () {

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
}
