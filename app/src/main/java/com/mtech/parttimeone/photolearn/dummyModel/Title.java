package com.mtech.parttimeone.photolearn.dummyModel;

/**
 * Created by Zhikai on 17/3/2018.
 */

public class Title {
    private String SessionID;
    private String Title;
    private String Creator;
    private TitleType type;
    static public enum TitleType{QUIZ,TITLE}

    public Title(String sessionID, String title, String creator, TitleType type) {
        SessionID = sessionID;
        Title = title;
        Creator = creator;
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
}
