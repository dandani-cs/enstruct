package com.example.enstruct.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long enrollmentId;

    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "courseCode")
    private Classes courseCode;

    public Enrollment() {
    }

    public Enrollment(Long enrollmentId, User user, Classes classes) {
        this.enrollmentId = enrollmentId;
        this.user = user;
        this.courseCode = classes;
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Classes getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Classes classesCode) {
        this.courseCode = classesCode;
    }
}
