package com.insomniacoder.authservice.user.controller;

import com.insomniacoder.authservice.user.dto.UserDTO;
import com.insomniacoder.authservice.user.entity.User;
import com.insomniacoder.authservice.user.exception.UserNotFoundException;
import com.insomniacoder.authservice.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<User> getUser() {
        return userRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public User createUser(@RequestBody UserDTO creatingUser) {
        User user = User.builder().email(creatingUser.getEmail()).name(creatingUser.getName()).password(creatingUser.getPassword()).role(creatingUser.getRole()).build();
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO updatingUser) throws UserNotFoundException {

        if (userRepository.existsById(id)) {
            User user = User.builder().email(updatingUser.getEmail()).name(updatingUser.getName()).password(updatingUser.getPassword()).role(updatingUser.getRole()).build();
            user.setId(id);
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException(id, "user not found by id : ");
        }
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id) throws UserNotFoundException {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            System.out.println();
            throw new UserNotFoundException(id, "user not found by id : ");
        }

    }

}
