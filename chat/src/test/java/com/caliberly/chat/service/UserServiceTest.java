package com.caliberly.chat.service;

import com.caliberly.chat.entity.User;
import com.caliberly.chat.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser() {
        String username = "testuser";
        String password = "password";
        User expectedUser = new User();
        expectedUser.setUsername(username);
        expectedUser.setPassword(password);

        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        User actualUser = userService.createUser(username, password);

        Assertions.assertEquals(expectedUser.getUsername(), actualUser.getUsername());
        Assertions.assertEquals(expectedUser.getPassword(), actualUser.getPassword());
    }

    @Test
    public void testGetUserByUsername() {
        String username = "testuser";
        User expectedUser = new User();
        expectedUser.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(expectedUser);

        User actualUser = userService.getUserByUsername(username);

        Assertions.assertEquals(expectedUser.getUsername(), actualUser.getUsername());
    }

    @Test
    public void testAuthenticateUser_Success() {
        String username = "testuser";
        String password = "password";
        User expectedUser = new User();
        expectedUser.setUsername(username);
        expectedUser.setPassword(password);

        when(userRepository.findByUsername(username)).thenReturn(expectedUser);

        User authenticatedUser = userService.authenticateUser(username, password);

        Assertions.assertEquals(expectedUser, authenticatedUser);
    }

    @Test
    public void testAuthenticateUser_Failure() {
        String username = "testuser";
        String password = "password";
        User expectedUser = new User();
        expectedUser.setUsername(username);
        expectedUser.setPassword(password);

        when(userRepository.findByUsername(username)).thenReturn(expectedUser);

        User authenticatedUser = userService.authenticateUser(username, "incorrect");

        Assertions.assertNull(authenticatedUser);
    }
}
