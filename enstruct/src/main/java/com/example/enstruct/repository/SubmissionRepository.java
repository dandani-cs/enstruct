package com.example.enstruct.repository;

import com.example.enstruct.model.Submission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubmissionRepository extends CrudRepository<Submission, Long> {
    // individual view of submissions per student
    @Query(value = "SELECT s FROM submissions s WHERE studentNumber = ?1 AND assignmentId = ?2", nativeQuery = true)
    Submission getSubmissionOfStudentInAssignment(long studentNumber, long assignmentId);

    // all submissions of students in assignment
    @Query(value = "SELECT * FROM submissions WHERE assignmentId = ?!", nativeQuery = true)
    List<Submission> getAllSubmissionsInAssignment(long assignmentId);
}
