package com.caliberly.chat.controller;

import com.caliberly.chat.entity.User;
import com.caliberly.chat.exception.AuthenticationFailed;
import com.caliberly.chat.exception.BadRequestException;
import com.caliberly.chat.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterUser_Success() {
        // Mock data
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        when(userService.getUserByUsername(user.getUsername())).thenReturn(null);
        when(userService.createUser(user.getUsername(), user.getPassword())).thenReturn(user);

        ResponseEntity<?> response = userController.registerUser(user);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(user, response.getBody());
    }

    @Test(expected = BadRequestException.class)
    public void testRegisterUser_UserAlreadyExists() {
        User user = new User();
        user.setUsername("existinguser");
        user.setPassword("password");

        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);

        userController.registerUser(user);

    }

    @Test
    public void testLoginUser_Success() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        when(userService.authenticateUser(user.getUsername(), user.getPassword())).thenReturn(user);

        ResponseEntity<?> response = userController.loginUser(user);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test(expected = AuthenticationFailed.class)
    public void testLoginUser_AuthenticationFailed() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        when(userService.authenticateUser(user.getUsername(), user.getPassword())).thenReturn(null);

        userController.loginUser(user);
    }
}

