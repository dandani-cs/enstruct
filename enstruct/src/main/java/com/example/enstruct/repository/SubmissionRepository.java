package com.example.enstruct.repository;

import com.example.enstruct.model.Attachment;
import com.example.enstruct.model.Submission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends CrudRepository<Submission, Long> {
    // individual view of submissions per student
    @Query(value = "SELECT * FROM submissions WHERE user_id = ?1 AND assignment_id = ?2", nativeQuery = true)
    Submission getSubmissionOfStudentInAssignment(long studentNumber, long assignmentId);

    // all submissions of students in assignment
    @Query(value = "SELECT * FROM submissions WHERE assignment_id = ?1", nativeQuery = true)
    List<Submission> getAllSubmissionsInAssignment(long assignmentId);

    // for grade view in individual student
    @Query(value = "SELECT * FROM submissions WHERE user_id = ?1", nativeQuery = true)
    List<Submission> getAllSubmissionsOfStudent(long studentNumber);

    @Query(value = "select * from submissions order by submission_date DESC limit 0,1", nativeQuery = true)
    public Optional<Submission> getLatestSubmission();
}
