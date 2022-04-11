package com.example.enstruct.repository;

import com.example.enstruct.model.Enrollment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {
    public List<Enrollment> findAll();
    public List<Enrollment> findByUserUserId(long userId);
    public List<Enrollment> findByUserUserIdAndClassesCourseCode(long userId, long courseId);
}
