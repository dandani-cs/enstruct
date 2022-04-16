package com.example.enstruct.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long submissionId;

    private Date submissionDate;
    private Double grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"submissions", "hibernateLazyInitializer"})
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "assignmentId")
    private Assignment assignmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"submissions", "hibernateLazyInitializer"})
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "userId")
    private User studentNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"submissions", "hibernateLazyInitializer"})
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "courseCode")
    private Classes course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"submissions", "hibernateLazyInitializer"})
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "attachmentId")
    private Attachment attachment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"submissions", "hibernateLazyInitializer"})
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "userId")
    private User teacherId;

    public Submission() {

    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Assignment getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Assignment assignmentId) {
        this.assignmentId = assignmentId;
    }

    public User getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(User studentNumber) {
        this.studentNumber = studentNumber;
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

    public User getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(User teacherId) {
        this.teacherId = teacherId;
    }


}
