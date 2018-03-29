package com.mtech.parttimeone.photolearn.bo;

/**
 * @author Leon
 * @date 3/12/18
 */

public class LearningItemBO extends ItemBO {
    //By zhikai, can delete if necessary. dont know what is title id
    private String userId;
    private String GPS;

    public LearningItemBO() {

    }

    public LearningItemBO(String titleId, String itemtitle, String userId, String photoURL, String itemDesc, String GPS) {
        super(itemtitle, titleId, photoURL, itemDesc);
        this.userId = userId;
        this.GPS = GPS;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGPS() {
        return GPS;
    }

    public void setGPS(String GPS) {
        this.GPS = GPS;
    }

}
