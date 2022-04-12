package com.example.enstruct.p_service;

import com.example.enstruct.p_model.User;
import com.example.enstruct.p_repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> getUser() {
        return (List<User>) repository.findAll();
    }

    @Override
    public User getUser(Long user_id) {
        Optional optional = repository.findById(user_id);
        if(!optional.isPresent())
            return null;
        else
            return (User) optional.get();
    }

    @Override
    public User addUser(User user) {
        return repository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteUser(Long user_id) {
        repository.deleteById(user_id);
    }
}
