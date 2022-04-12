package com.example.enstruct.p_service;

import com.example.enstruct.p_model.User;

import java.util.List;

public interface IUserService {
    List<User> getUser();
    User getUser(Long user_name);
    User addUser(User user);
    User updateUser(User user);
    public void deleteUser(Long user_id);
}
