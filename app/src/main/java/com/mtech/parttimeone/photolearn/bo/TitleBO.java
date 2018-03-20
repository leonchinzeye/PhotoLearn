package com.mtech.parttimeone.photolearn.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leon
 * @date 3/12/18
 */

public abstract class TitleBO {

    private String sessionId;
    private String title;
    private List<ItemBO> items = new ArrayList<>();

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ItemBO> getItems() {
        return items;
    }

    public void setItems(List<ItemBO> items) {
        this.items = items;
    }
}
