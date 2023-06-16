package com.example.mockitoUserCrud.service;

import com.example.mockitoUserCrud.model.Users;
import com.example.mockitoUserCrud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultUserService {

    private final UserRepository userRepository;

    public Users saveUser(Users user) {
        if (user == null) {
            throw new IllegalArgumentException("request body is mandatory");
        }
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalStateException("something unexpected occurred", e);
        }
    }

    public List<Users> getAllUsers() {
        try {
            List<Users> users = userRepository.findAll();
            return users;
        } catch (Exception e) {
            throw new IllegalStateException("something unexpected occurred", e);
        }
    }

    public Users getUsersById(Long id) {
        if (id == 0) {
            return null;
        }
        System.out.println(id);
        Optional<Users> byId;
        try {
            byId = userRepository.findById(id);
        } catch (Exception e) {
            throw new IllegalStateException("something unexpected occured", e);
        }
        return byId
                .orElseThrow(() -> new IllegalArgumentException("user not found"));
    }

    public void deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalStateException("something unexpected occurred", e);
        }
    }
}
