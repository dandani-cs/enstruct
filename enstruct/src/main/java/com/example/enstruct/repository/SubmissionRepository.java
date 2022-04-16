package com.example.enstruct.repository;

import com.example.enstruct.model.Submission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends CrudRepository<Submission, Long> {
    // individual view of submissions per student
    @Query(value = "SELECT s FROM submissions s WHERE studentNumber = ?1 AND assignmentId = ?2", nativeQuery = true)
    Submission getSubmissionOfStudentInAssignment(long studentNumber, long assignmentId);

    // all submissions of students in assignment
    @Query(value = "SELECT * FROM submissions WHERE assignmentId = ?1", nativeQuery = true)
    List<Submission> getAllSubmissionsInAssignment(long assignmentId);

    // for grade view in individual student
    @Query(value = "SELECT * FROM submissions WHERE studentNumber = ?1", nativeQuery = true)
    List<Submission> getAllSubmissionsOfStudent(long studentNumber);
}
