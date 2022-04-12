package com.example.enstruct.p_testcontroller;

import com.example.enstruct.p_model.User;
import com.example.enstruct.p_service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserService service;

    @RequestMapping(value = "/getUsers")
    public List<User> getUsers() {
        System.out.println("halo");
        return service.getUser();
    }

    @RequestMapping(value = "/getUser/{user_id}")
    public User getUser(@PathVariable Long user_id) {
        return service.getUser(user_id);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    @RequestMapping(value = "/updateUser/{user_name}", method = RequestMethod.POST)
    public User updateUser(@PathVariable Long user_name,  @RequestBody User user) {
        return service.updateUser(user);
    }

    @RequestMapping(value = "/deleteUser/{user_id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long user_id) {
        service.deleteUser(user_id);
    }
}
