package com.insomniacoder.authservice.user.controller;

import com.insomniacoder.authservice.user.dto.CreateUserDTO;
import com.insomniacoder.authservice.user.entitie.User;
import com.insomniacoder.authservice.user.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<User> getUser() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody CreateUserDTO creatingUser) {
        User user = User.builder().email(creatingUser.getEmail()).name(creatingUser.getName()).password(creatingUser.getPassword()).role(creatingUser.getRole()).build();
        return userRepository.save(user);
    }

}
