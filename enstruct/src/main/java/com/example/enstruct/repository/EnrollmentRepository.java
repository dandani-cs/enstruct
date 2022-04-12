package com.example.enstruct.repository;

import com.example.enstruct.model.Enrollment;
import com.example.enstruct.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {
    public List<Enrollment> findAll();
    public List<Enrollment> findByUserUserId(long userId);
    public List<Enrollment> findByClassesCourseCode(long courseId);
    public Optional<Enrollment> findByUserUserIdAndClassesCourseCode(long userId, long courseId);
}
