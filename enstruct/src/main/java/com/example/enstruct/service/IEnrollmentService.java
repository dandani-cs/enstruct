package com.example.enstruct.service;

import com.example.enstruct.model.Enrollment;

import java.util.List;

public interface IEnrollmentService {
    public List<Enrollment> findAll();

    // Find all enrollments of a particular student
    public List<Enrollment> findByUserId(long userId);

    // Find all enrollments in a particular course
    public List<Enrollment> findByCourseId(String courseId);

    // Find a particular enrollment of a particular student
    public Enrollment findByCourseAndUserId(long userId, long courseId);

    public Enrollment addEnrollment(Enrollment enrollment);
    public Enrollment updateEnrollment(Enrollment enrollment);
    public Enrollment findByEnrollmentId(long enrollmentId);
    public void deleteEnrollment(Enrollment enrollment);
}
