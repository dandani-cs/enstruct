package com.example.enstruct.service;

import com.example.enstruct.model.Enrollment;
import com.example.enstruct.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class EnrollmentService implements IEntrollmentService {
    @Autowired
    private EnrollmentRepository repository;

    @Override
    public List<Enrollment> findAll() { return repository.findAll(); }

    @Override
    public Enrollment addEnrollment(Enrollment enrollment) { return repository.save(enrollment); }

    @Override
    public Enrollment updateEnrollment(Enrollment enrollment) { return repository.save(enrollment); }

    @Override
    public Enrollment findByEnrollmentId(long enrollmentId)
    {
        Optional opt = repository.findById(enrollmentId);
        return opt.isEmpty() ? null : (Enrollment) opt.get();
    }

    @Override
    public List<Enrollment> findByUserId(long userId) { return repository.findByUserUserId(userId); }

    @Override
    public List<Enrollment> findCoursesWithUserId(long userId, long courseId) {
        return repository.findByUserUserIdAndClassesCourseCode(userId, courseId);
    }

    @Override
    public void deleteEnrollment(Enrollment enrollment) { repository.delete(enrollment); }
}
