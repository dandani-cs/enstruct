package com.example.enstruct.service;

import com.example.enstruct.model.Assignment;

import java.util.List;

public interface IAssignmentService {

    Assignment getAssignment(Long assignmentId);
    Assignment addAssignment(Assignment assignment);
    Assignment updateAssignment(Assignment assignment);
    void deleteAssignment(Long assignmentId);

    List<Assignment> getPendingAssignmentsByCourseCode(String courseCode);
    List<Assignment> getAssignmentsInCourse(String courseCode);
}
