package com.example.enstruct.service;

import com.example.enstruct.model.Assignment;

import java.util.Date;
import java.util.List;

public interface IAssignmentService {

    Assignment getAssignment(Long assignmentId);
    Assignment addAssignment(Assignment assignment);
    Assignment updateAssignment(Assignment assignment);
    void deleteAssignment(Long assignmentId);
    List<Assignment> getAllAssignments();
    public List<Assignment> getAllAssignmentsWithinDatesByCourseCode(Date from, Date to, String courseCode);
    public List<Assignment> getAllAssignmentsWithinDates(Date from, Date to);
    public List<Assignment> getAllPendingAssignments();
    List<Assignment> getPendingAssignmentsByCourseCode(String courseCode);
    List<Assignment> getAssignmentsInCourse(String courseCode);
}
