package com.example.enstruct.service;

import com.example.enstruct.model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    User addUser(User user);
    User updateUser(User user);
    User getUser(long userId);
    User findByUsername(String username);
    void deleteUser(User user);
    List<User> findAllStudents();

}
