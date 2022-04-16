package com.example.enstruct.controller;

import com.example.enstruct.model.User;
import com.example.enstruct.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {
    @Autowired
    private IUserService service;

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public List<User> getAllUsers()
    {
        return service.findAll();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public User addUser(@RequestBody User user)
    {
        return service.addUser(user);
    }
}
