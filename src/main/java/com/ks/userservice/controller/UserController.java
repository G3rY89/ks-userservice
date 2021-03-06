package com.ks.userservice.controller;

import com.ks.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ks.userservice.model.User;

import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository service){
        this.userRepository = service;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping(value = "/add-user")
    public void addUser(@RequestParam("username") String userName, @RequestParam("emailaddress") String emailAddress){
        userRepository.save(new User(userName, emailAddress));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void removeUser(@RequestParam("userid") String userId){
        User user = userRepository.getOne(Integer.parseInt(userId));
        userRepository.delete(user);
    }

}
