package com.example.enstruct.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
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

    private Date deadline;
    private Date availabilityStart;
    private Date availabilityEnd;

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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getAvailabilityStart() {
        return availabilityStart;
    }

    public void setAvailabilityStart(Date availabilityStart) {
        this.availabilityStart = availabilityStart;
    }

    public Date getAvailabilityEnd() {
        return availabilityEnd;
    }

    public void setAvailabilityEnd(Date availabilityEnd) {
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
}