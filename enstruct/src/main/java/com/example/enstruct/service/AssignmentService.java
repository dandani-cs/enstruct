package com.example.enstruct.service;

import com.example.enstruct.model.Assignment;
import com.example.enstruct.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService implements IAssignmentService{
    @Autowired
    private AssignmentRepository repository;


    @Override
    public Assignment getAssignment(Long assignmentId) {
        Optional<Assignment> assignment = repository.findById(assignmentId);
        return assignment.isPresent() ? assignment.get() : null;
    }

    @Override
    public Assignment addAssignment(Assignment assignment) {
        return repository.save(assignment);
    }

    @Override
    public Assignment updateAssignment(Assignment assignment) {
        return repository.save(assignment);
    }

    @Override
    public void deleteAssignment(Long assignmentId) {
        repository.deleteById(assignmentId);
    }

    @Override
    public List<Assignment> getAllAssignments() {
        return (List<Assignment>) repository.findAll();
    }

    @Override
    public List<Assignment> getPendingAssignmentsByCourseCode(String courseCode) {
        return repository.getPendingAssignmentsByCourseCode(courseCode);
    }

    @Override
    public List<Assignment> getAssignmentsInCourse(String courseCode) {
        return repository.getAssignmentsInCourse(courseCode);
    }
}
