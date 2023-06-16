package com.example.mockitoUserCrud.controller;

import com.example.mockitoUserCrud.model.Users;
import com.example.mockitoUserCrud.repository.UserRepository;
import com.example.mockitoUserCrud.service.DefaultUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class UserControllerTest {

    @Mock
    DefaultUserService userService;

    UserController controller;

    @BeforeEach
    void setUp() {
        controller = new UserController(userService);
    }



}