package com.example.enstruct.repository;

import com.example.enstruct.model.Enrollment;
import com.example.enstruct.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {
    public List<Enrollment> findAll();
    public List<Enrollment> findByUserUserId(long userId);
    public List<Enrollment> findByCourseCodeCourseCode(String courseId);
    public Optional<Enrollment> findByUserUserIdAndCourseCodeCourseCode(long userId, long courseId);
}
