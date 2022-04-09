package com.example.enstruct.service;

import com.example.enstruct.model.Submission;
import com.example.enstruct.repository.SubmissionRepository;

import java.util.List;
import java.util.Optional;

public class SubmissionService implements ISubmissionService {
    private SubmissionRepository repository;

    @Override
    public Submission getSubmission(long submissionId) {
        Optional submission = repository.findById(submissionId);
        if (submission.isPresent())
            return (Submission) submission.get();
        return null;
    }

    @Override
    public Submission addSubmission(Submission submission) {
        return repository.save(submission);
    }

    @Override
    public void deleteSubmission(long submissionId) {
        repository.deleteById(submissionId);
    }

    @Override
    public Submission getSubmissionOfStudentInAssignment(long studentNumber, long assignmentId) {
        return repository.getSubmissionOfStudentInAssignment(studentNumber, assignmentId);
    }

    @Override
    public List<Submission> getAllSubmissionsInAssignment(long assignmentId) {
        return repository.getAllSubmissionsInAssignment(assignmentId);
    }

    @Override
    public List<Submission> getAllSubmissionsOfStudent(long studentNumber) {
        return repository.getAllSubmissionsOfStudent(studentNumber);
    }
}
