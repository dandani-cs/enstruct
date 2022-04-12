package com.example.enstruct.repository;

import com.example.enstruct.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    public List<User> findAll();
}
