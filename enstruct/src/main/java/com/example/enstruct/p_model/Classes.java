package com.example.enstruct.p_model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "Classes")
public class Classes {

    @Id
    private String course_code;

    private String course_name;
    private Boolean enabled;

    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action= OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "user_id")
    private User teacher_id;

    public Classes() {
    }

    public Classes(String course_code, String course_name, Boolean enabled, User teacher_id) {
        this.course_code = course_code;
        this.course_name = course_name;
        this.enabled = enabled;
        this.teacher_id = teacher_id;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public User getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(User teacher_id) {
        this.teacher_id = teacher_id;
    }
}
