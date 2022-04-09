package com.example.enstruct.repository;

import com.example.enstruct.model.Assignment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignmentRepository extends CrudRepository<Assignment, Long> {
    // for students to know upcoming assignments
    @Query(value = "SELECT * FROM Assignment WHERE courseCode = ?1 AND DATE(deadline) > CURDATE()", nativeQuery = true)
    List<Assignment> getPendingAssignmentsByCourseCode(long courseCode);

    // for instructor's view of assignments
    @Query(value = "SELECT * FROM Assignment WHERE courseCode = ?1", nativeQuery = true)
    List<Assignment> getAssignmentsInCourse(long courseCode);

}
