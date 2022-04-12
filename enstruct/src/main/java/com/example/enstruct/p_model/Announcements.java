package com.example.enstruct.p_model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Announcements")
public class Announcements {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long announcement_id;

    private String text;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"Announcements", "hibernateLazyInitializer"})
    @OnDelete(action= OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "course_code")
    private Classes course_code;

    public Announcements() {
    }

    public Announcements(Long announcement_id, String text, LocalDate date, Classes course_code) {
        this.announcement_id = announcement_id;
        this.text = text;
        this.date = date;
        this.course_code = course_code;
    }

    public Long getAnnouncement_id() {
        return announcement_id;
    }

    public void setAnnouncement_id(Long announcement_id) {
        this.announcement_id = announcement_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Classes getCourse_code() {
        return course_code;
    }

    public void setCourse_code(Classes course_code) {
        this.course_code = course_code;
    }
}
