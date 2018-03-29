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

    private String titleId;
    private String photoURL;
    private String itemDesc; //use common name so can be used for photo desc and item desc

    public ItemBO () {

    }

    public ItemBO(String itemtitle, String titleId, String photoURL, String itemDesc) {
        this.itemtitle = itemtitle;
        this.titleId = titleId;
        this.photoURL = photoURL;
        this.itemDesc = itemDesc;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    //Added by Zhikai

    public String getItemtitle() {
        return itemtitle;
    }

    public void setItemtitle(String itemtitle) {
        this.itemtitle = itemtitle;
    }

}
