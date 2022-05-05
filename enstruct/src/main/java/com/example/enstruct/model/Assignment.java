package com.example.enstruct.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long assignmentId;

    private String name;
    private String instruction;
    private int maxScore;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate deadline;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate deadline_date;

    @DateTimeFormat(pattern="HH:mm")
    private LocalTime deadline_time;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate availabilityStart;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate availabilityEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"assignments", "hibernateLazyInitializer"})
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "courseCode")
    private Classes course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"assignments", "hibernateLazyInitializer"})
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "attachmentId")
    private Attachment attachment;

    public Assignment() {

    }

    public long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public LocalDate getAvailabilityStart() {
        return availabilityStart;
    }

    public void setAvailabilityStart(LocalDate availabilityStart) {
        this.availabilityStart = availabilityStart;
    }
    public void setAvailabilityStart(String availabilityStart) {
        String[] deadlineArr = availabilityStart.split("/");
        this.availabilityStart = LocalDate.of(Integer.parseInt(deadlineArr[2]), Integer.parseInt(deadlineArr[0]), Integer.parseInt(deadlineArr[1]));
    }

    public LocalDate getAvailabilityEnd() {
        return availabilityEnd;
    }

    public void setAvailabilityEnd(LocalDate availabilityEnd) {
        this.availabilityEnd = availabilityEnd;
    }

    public Classes getCourse() {
        return course;
    }

    public void setCourse(Classes course) {
        this.course = course;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setDeadline_date(String deadline_date_str) {
        String[] deadlineArr = deadline_date_str.split("/");
        this.deadline_date = LocalDate.of(Integer.parseInt(deadlineArr[2]), Integer.parseInt(deadlineArr[0]), Integer.parseInt(deadlineArr[1]));
    }

    public LocalDate getDeadline_date() {
        return deadline_date;
    }

    public void setDeadline_date(LocalDate deadline_date) {
        this.deadline_date = deadline_date;
    }

    public LocalTime getDeadline_time() {
        return deadline_time;
    }

    public void setDeadline_time(LocalTime deadline_time) {
        this.deadline_time = deadline_time;
    }


}