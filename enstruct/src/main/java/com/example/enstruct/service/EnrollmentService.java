package com.example.enstruct.service;

import com.example.enstruct.model.Enrollment;
import com.example.enstruct.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService implements IEnrollmentService {
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
        return  !opt.isPresent() ? null : (Enrollment) opt.get();
    }

    @Override
    public List<Enrollment> findByUserId(long userId) { return repository.findByUserUserId(userId); }

    @Override
    public List<Enrollment> findByCourseId(String courseId) {
        return repository.findByCourseCodeCourseCode(courseId);
    }

    @Override
    public Enrollment findByCourseAndUserId(long userId, long courseId) {
        Optional opt = repository.findByUserUserIdAndCourseCodeCourseCode(userId, courseId);
        return !opt.isPresent() ? null : (Enrollment) opt.get();
    }

    @Override
    public void deleteEnrollment(Enrollment enrollment) { repository.delete(enrollment); }
}
