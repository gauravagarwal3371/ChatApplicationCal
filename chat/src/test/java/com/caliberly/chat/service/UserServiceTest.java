package com.caliberly.chat.service;

import static org.mockito.Mockito.*;

import com.caliberly.chat.entity.User;
import com.caliberly.chat.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Before
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

        Assert.assertEquals(expectedUser.getUsername(), actualUser.getUsername());
        Assert.assertEquals(expectedUser.getPassword(), actualUser.getPassword());
    }

    @Test
    public void testGetUserByUsername() {
        String username = "testuser";
        User expectedUser = new User();
        expectedUser.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(expectedUser);

        User actualUser = userService.getUserByUsername(username);

        Assert.assertEquals(expectedUser.getUsername(), actualUser.getUsername());
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

        Assert.assertEquals(expectedUser, authenticatedUser);
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

        Assert.assertNull(authenticatedUser);
    }
}

