package com.example.enstruct.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity(name = "classes")
public class Classes {
    @Id
    public String courseCode;

    private String courseName;
    private Boolean enabled;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action= OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "userId")
    private User teacherId;

    public Classes() {
    }

    public Classes(String courseCode, String courseName, Boolean enabled, User teacherId) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.enabled = enabled;
        this.teacherId = teacherId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public User getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(User teacherId) {
        this.teacherId = teacherId;
    }
}
