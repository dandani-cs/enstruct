package com.example.enstruct.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "classes")
public class Classes {
    @Id
    public String courseCode;

    public Classes() {
    }

    public Classes(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
