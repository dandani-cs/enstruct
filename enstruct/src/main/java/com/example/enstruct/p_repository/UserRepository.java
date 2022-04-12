package com.example.enstruct.p_repository;

import com.example.enstruct.p_model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
