package com.example.enstruct.service;

import com.example.enstruct.model.Enrollment;

import java.util.List;

public interface IEntrollmentService {
    public List<Enrollment> findAll();
    public List<Enrollment> findByUserId(long userId);
    public List<Enrollment> findCoursesWithUserId(long userId, long courseId);

    public Enrollment addEnrollment(Enrollment enrollment);
    public Enrollment updateEnrollment(Enrollment enrollment);
    public Enrollment findByEnrollmentId(long enrollmentId);
    public void deleteEnrollment(Enrollment enrollment);
}
