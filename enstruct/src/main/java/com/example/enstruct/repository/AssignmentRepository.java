package com.example.enstruct.repository;

import com.example.enstruct.model.Assignment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, Long> {
    // for students to know upcoming assignments
    @Query(value = "SELECT * FROM Assignments WHERE course_code = ?1 AND DATE(deadline) > CURDATE()", nativeQuery = true)
    List<Assignment> getPendingAssignmentsByCourseCode(String courseCode);



    // for instructor's view of assignments
    @Query(value = "SELECT * FROM Assignments WHERE course_code = ?1", nativeQuery = true)
    List<Assignment> getAssignmentsInCourse(String courseCode);

    @Query(value = "SELECT * FROM Assignments WHERE DATE(deadline) >= ?1 AND DATE(deadline) <= ?2", nativeQuery = true)
    List<Assignment> getAllAssignmentsWithinDates(Date from, Date to);

    @Query(value = "SELECT * FROM Assignments WHERE DATE(deadline) >= ?1 AND DATE(deadline) <= ?2 AND course_code = ?3", nativeQuery = true)
    public List<Assignment> getAllAssignmentsWithinDatesByCourseCode(Date from, Date to, String courseCode);
    List<Assignment> findAll();
}
