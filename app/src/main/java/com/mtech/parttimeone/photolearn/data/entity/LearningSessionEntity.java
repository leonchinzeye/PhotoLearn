package com.mtech.parttimeone.photolearn.data.entity;

/**
 * Created by karen on 20/3/2018.
 */

public class LearningSessionEntity {
    private String sessionId;
    private String courseTitle;
    private String moduleName;
    private String startDate;
    private String createdDate;

    public LearningSessionEntity(String sessionId, String courseTitle, String moduleName, String startDate, String createdDate) {
        this.sessionId = sessionId;
        this.courseTitle = courseTitle;
        this.moduleName = moduleName;
        this.startDate = startDate;
        this.createdDate = createdDate;
    }

    public String getSessionId() { return sessionId; }

    public void setSessionId(String sessionId) {this.sessionId = sessionId; }

    public String getCourseTitle() {return courseTitle; }

    public void setCourseTitle(String courseTitle) {this.courseTitle = courseTitle; }

    public String getModuleName() { return moduleName; }

    public void setModuleName(String moduleName) {this.moduleName = moduleName; }

    public String getStartDate() { return startDate; }

    public void setStartDate(String startDate) {this.startDate = startDate; }

    public String getCreatedDate() { return createdDate; }

    public void setCreatedDate(String createdDate) {this.createdDate = createdDate; }
}
