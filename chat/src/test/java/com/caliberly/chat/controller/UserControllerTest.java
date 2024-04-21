package com.caliberly.chat.controller;

import com.caliberly.chat.entity.User;
import com.caliberly.chat.exception.AuthenticationFailed;
import com.caliberly.chat.exception.BadRequestException;
import com.caliberly.chat.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
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

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(user, response.getBody());
    }

    @Test
    public void testRegisterUser_UserAlreadyExists() {
        User user = new User();
        user.setUsername("existinguser");
        user.setPassword("password");

        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);

        Assertions.assertThrows(BadRequestException.class, () -> userController.registerUser(user));
    }

    @Test
    public void testLoginUser_Success() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        when(userService.authenticateUser(user.getUsername(), user.getPassword())).thenReturn(user);

        ResponseEntity<?> response = userController.loginUser(user);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testLoginUser_AuthenticationFailed() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        when(userService.authenticateUser(user.getUsername(), user.getPassword())).thenReturn(null);

        Assertions.assertThrows(AuthenticationFailed.class, () -> userController.loginUser(user));
    }
}
