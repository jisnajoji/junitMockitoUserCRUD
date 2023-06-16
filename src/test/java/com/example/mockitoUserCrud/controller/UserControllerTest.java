package com.example.mockitoUserCrud.controller;

import com.example.mockitoUserCrud.model.Users;
import com.example.mockitoUserCrud.service.DefaultUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private DefaultUserService userService;

    private UserController controller;

    @BeforeEach
    void setUp() {
        controller = new UserController(userService);
    }


    //get all api
    @Test
    public void testGetAllUser_Success() {

        // Create a sample list of users
        List<Users> users = Arrays.asList(new Users(1, "John", "fgh"), new Users(2, "Jane", "sdsd"));

        // Specify the behavior of userServiceMock
        when(userService.getAllUsers()).thenReturn(users);

        // Call the getAllUser method
        List<Users> result = controller.getAllUser();

        // Verify that the userService's getAllUsers method was called exactly once
        verify(userService, times(1)).getAllUsers();

        // Verify the result
        assertEquals(users, result);
    }

    @Test
    public void testGetAllUser_Failure() {

        // Specify the behavior of userServiceMock to throw an exception
        when(userService.getAllUsers()).thenReturn(null);

        // Call the getAllUser method
        List<Users> result = controller.getAllUser();

        // Verify that the userService's getAllUsers method was called exactly once
        verify(userService,times(1)).getAllUsers();

        // Verify the result is null or an empty list
        assertNull(result); // or Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void saveUser_ValidUser_Success() {
        Users user = new Users(1,"dskj", "sds");

        when(userService.saveUser(user)).thenReturn(user);
        Users userResult = controller.saveUser(user);
        verify(userService, times(1)).saveUser(user);
        assertEquals(user, userResult);
    }

    @Test
    public void saveUser_EmptyRequestBody_BadRequest() {
        Users user = new Users();
        when(userService.saveUser(user)).thenReturn(null);
        Users result = controller.saveUser(user);
        verify(userService,times(1)).saveUser(user);
        assertNull(result);
    }

    //get by id

    @Test
    public void testGetUsersById_Success() {
        Users user = new Users(1, "John", "dsds");
        user.setId(1L);
        when(userService.getUsersById(1L)).thenReturn(user);
        Users result = controller.getUsersById(1L);
        verify(userService, times(1)).getUsersById(1L);
        assertEquals(user, result);
    }

    @Test
    public void testGetUsersById_Failure() {
        when(userService.getUsersById(1L)).thenThrow(new IllegalArgumentException("user not found"));
        Users result = controller.getUsersById(1L);
        verify(userService, times(1)).getUsersById(1L);
        assertNull(result);
    }


}



//    String baseUrl = "http://localhost:8080";
//    String uri = baseUrl + "/users";
//    @Test
//    public void saveUser_ValidUser_Success() {
//        Users user = new Users();
//        when(userService.saveUser(any(Users.class))).thenReturn(user);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> request = new HttpEntity<>(headers);
//
//        ResponseEntity<String> response = new RestTemplate().postForEntity(uri, request, String.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(user, response.getBody());
//        verify(userService, times(1)).saveUser(eq(user));
//    }
//
//    @Test
//    public void saveUser_EmptyRequestBody_BadRequest() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> request = new HttpEntity<>(headers);
//        ResponseEntity<String> response = new RestTemplate().postForEntity(uri, request, String.class);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        verifyNoInteractions(userService);
//    }