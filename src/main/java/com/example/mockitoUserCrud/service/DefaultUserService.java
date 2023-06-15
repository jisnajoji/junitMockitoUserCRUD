package com.example.mockitoUserCrud.service;

import com.example.mockitoUserCrud.model.Users;
import com.example.mockitoUserCrud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultUserService {

    private final UserRepository userRepository;

    public Users saveUser(Users user) {
        System.out.println("saving to db" + user.getName());
        return userRepository.save(user);
    }
    public List<Users> getAllUsers() {
       List<Users> users =  userRepository.findAll();
        System.out.println("Getting data from db"+ users.size());
        return users;
    }

    public Optional<Users> getUsersById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
