package com.mtech.parttimeone.photolearn.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leon
 * @date 3/12/18
 */

public abstract class TitleBO {

    private String uuid;
    private String sessionId;
    private String titleId;
    private String title;
    private String createdBy;

    //Constructor by zhikai. can delete once the model is completed
    //Question. Who is the creator of the title? How do I get that?

    public TitleBO () {

    }

    public TitleBO(String sessionId, String titleId, String title, String createdBy) {
        this.sessionId = sessionId;
        this.titleId = titleId;
        this.title = title;
        this.createdBy = createdBy;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTitleId() { return titleId; }

    public void setTitleId(String titleId) { this.titleId = titleId; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedBy() { return createdBy; }

    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
}
