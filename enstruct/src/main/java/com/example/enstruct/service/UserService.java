package com.example.enstruct.service;

import com.example.enstruct.model.User;
import com.example.enstruct.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() { return repository.findAll(); }

    @Override
    public User addUser(User user) { return repository.save(user); }

    @Override
    public User updateUser(User user) { return repository.save(user); }

    @Override
    public User getUser(long userId) {
        Optional opt = repository.findById(userId);
        return !opt.isPresent() ? null : (User) opt.get();
    }

    @Override
    public void deleteUser(User user) { repository.delete(user); }
}
