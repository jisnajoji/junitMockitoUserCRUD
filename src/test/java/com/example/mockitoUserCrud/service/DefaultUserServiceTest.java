package com.example.mockitoUserCrud.service;

import com.example.mockitoUserCrud.model.Users;
import com.example.mockitoUserCrud.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultUserServiceTest {

    @Mock
    private UserRepository repository;
    private DefaultUserService userService;


    @BeforeEach
    void initialize() {
        userService = new DefaultUserService(repository);
    }

    // get by Id
    @Test
    void shouldReturnUser() {
        Users user = new Users();
        user.setId(1);

        when(repository.findById(1L)).thenReturn(Optional.of(user));

        var result = userService.getUsersById(1L);

        assertNotNull(result);
        assertEquals(result.getId(), user.getId());
        assertEquals(result, user);
    }


    @Test
    void shouldThrowNotFound_whenRepositoryReturnsEmpty() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        var e = assertThrows(IllegalArgumentException.class, () -> userService.getUsersById(1L));
        assertEquals("user not found", e.getMessage());
    }


    @Test
    void shouldThrowError_whenRepositoryThrowsError() {
        RuntimeException cause = mock(RuntimeException.class);
        when(repository.findById(any())).thenThrow(cause);

        var e = assertThrows(IllegalStateException.class, () -> userService.getUsersById(1L));
        assertEquals(e.getMessage(), "something unexpected occured");
        assertEquals(e.getCause(), cause);
    }

    @Test
    void shouldReturnNull_whenIdIsZero() {
        var result = userService.getUsersById(0L);
        assertNull(result);
    }

    // get all api

    @Test
    void getAllUsers_WithEmptyUserList() {
        List<Users> emptyUserList = new ArrayList<>();
        when(repository.findAll()).thenReturn(emptyUserList);

        List<Users> result = userService.getAllUsers();

        assertEquals(emptyUserList, result);
        verify(repository, times(1)).findAll();

    }

    @Test
    void getAllUsers_WithSingleUser() {
        List<Users> singleUser = new ArrayList<>();
        Users user = new Users(1, "Jisna", "Maria");
        singleUser.add(user);
        when(repository.findAll()).thenReturn(singleUser);

        List<Users> result =  userService.getAllUsers();

        assertEquals(singleUser, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    void getAllUsers_WithMultipleUsers() {
        List<Users> multipleUsers = new ArrayList<>();
        Users user1 = new Users(1, "Jisna ", "Maria");
        Users user2 =  new Users(2, "Pauls", "Joji");
        multipleUsers.add(user1);
        multipleUsers.add(user2);
        when(repository.findAll()).thenReturn(multipleUsers);

        List<Users> usersList = userService.getAllUsers();

        assertEquals(multipleUsers, usersList);
        verify(repository, times(1)).findAll();
    }

    @Test
    void getAllUsers_WithNullUserList() {
        when(repository.findAll()).thenReturn(null);

        List<Users> result = userService.getAllUsers();

        assertNull(result);
        verify(repository, times(1)).findAll();
    }

    @Test
    void getAllShouldThrowError_whenRepoThrowsError() {
        RuntimeException cause = mock(RuntimeException.class);
        when(repository.findAll()).thenThrow(cause);
        var e = assertThrows(IllegalStateException.class, () -> userService.getAllUsers());
        assertEquals(e.getMessage(), "something unexpected occurred");
        assertEquals(e.getCause(), cause);
        verify(repository, times(1)).findAll();
    }

    // save new user

    @Test
    void saveUser_SuccessCase_ReturnSavedUser() {
        Users user = new Users();
        when(repository.save(user)).thenReturn(user);
        Users savedUser = userService.saveUser(user);
        assertEquals(user, savedUser);
    }

    @Test
    void saveUser_BadRequest_ThrowBadRequestException() {
        Users user = null;
        var e = assertThrows(IllegalArgumentException.class, () -> userService.saveUser(user));
        assertEquals("request body is mandatory", e.getMessage());
        verifyNoInteractions(repository);
    }

    @Test
    void saveUser_InternalServerError_ThrowInternalServerException() {
        RuntimeException cause = mock(RuntimeException.class);
        Users user = new Users(1, "", "");
        when(repository.save(any())).thenThrow(cause);
        var e = assertThrows(IllegalStateException.class, () -> userService.saveUser(user));
        assertEquals(e.getMessage(), "something unexpected occurred");
        assertEquals(e.getCause(), cause);
    }

    @Test
    public void deleteUserById_SuccessCase() {

       userService.deleteUserById(1L);

        // Verify that userRepository.deleteById() is called once with the correct id
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteUserById_FailureCase_ThrowsIllegalStateException() {
        doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(1L);

        assertThrows(IllegalStateException.class,
                () -> userService.deleteUserById(1L));

        verify(repository, times(1)).deleteById(1L);
    }

}