package com.example.enstruct.repository;

import com.example.enstruct.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public List<User> findAll();
    public Optional<User> findByUserName(String username);

    @Query(value = "select * from users where is_teacher = 0", nativeQuery = true)
    public List<User> findAllStudents();
}
