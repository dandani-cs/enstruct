package com.example.enstruct.service;

import com.example.enstruct.model.Submission;

import java.util.List;

public interface ISubmissionService {

    Submission getSubmission(long submissionId);
    Submission addSubmission(Submission submission);
    void deleteSubmission(long submissionId);

    Submission getSubmissionOfStudentInAssignment(long studentNumber, long assignmentId);
    List<Submission> getAllSubmissionsInAssignment(long assignmentId);
    List<Submission> getAllSubmissionsOfStudent(long studentNumber);
}
