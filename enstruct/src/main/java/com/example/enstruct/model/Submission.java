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
    private int grade;

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
    private Course courseCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"submissions", "hibernateLazyInitializer"})
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "attachmentId")
    private Attachment attachmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"submissions", "hibernateLazyInitializer"})
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "userId")
    private User teacherId;

    public Submission() {

    }

    public Submission(Long submissionId, Date submissionDate, int grade, Assignment assignmentId, User studentNumber, Course courseCode, Attachment attachmentId, User teacherId) {
        this.submissionId = submissionId;
        this.submissionDate = submissionDate;
        this.grade = grade;
        this.assignmentId = assignmentId;
        this.studentNumber = studentNumber;
        this.courseCode = courseCode;
        this.attachmentId = attachmentId;
        this.teacherId = teacherId;
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
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

    public Course getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Course courseCode) {
        this.courseCode = courseCode;
    }

    public Attachment getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Attachment attachmentId) {
        this.attachmentId = attachmentId;
    }

    public User getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(User teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Submission)) return false;
        Submission that = (Submission) o;
        return getGrade() == that.getGrade() && getSubmissionId().equals(that.getSubmissionId()) && getSubmissionDate().equals(that.getSubmissionDate()) && getAssignmentId().equals(that.getAssignmentId()) && getStudentNumber().equals(that.getStudentNumber()) && getCourseCode().equals(that.getCourseCode()) && getAttachmentId().equals(that.getAttachmentId()) && getTeacherId().equals(that.getTeacherId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubmissionId(), getSubmissionDate(), getGrade(), getAssignmentId(), getStudentNumber(), getCourseCode(), getAttachmentId(), getTeacherId());
    }
}
