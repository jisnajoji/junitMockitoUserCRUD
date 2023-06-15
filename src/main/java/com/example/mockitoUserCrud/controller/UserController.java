package com.example.mockitoUserCrud.controller;

import com.example.mockitoUserCrud.model.Users;
import com.example.mockitoUserCrud.service.DefaultUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final DefaultUserService userService;

    @PostMapping
    public Users saveUser(@RequestBody Users user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public List<Users> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<Users> getUsersById(@PathVariable Long id) {
        return userService.getUsersById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

}
