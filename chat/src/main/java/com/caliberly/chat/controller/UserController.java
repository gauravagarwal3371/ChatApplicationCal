package com.caliberly.chat.controller;

import com.caliberly.chat.entity.User;
import com.caliberly.chat.exception.AuthenticationFailed;
import com.caliberly.chat.exception.BadRequestException;
import com.caliberly.chat.exception.NotFoundException;
import com.caliberly.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User existingUser = userService.getUserByUsername(user.getUsername());
        if (existingUser != null) {
            throw new BadRequestException("User Already exists. Please use different username");
        }
        User newUser = userService.createUser(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        User user = userService.authenticateUser(username, password);
        if (user != null) {
            return ResponseEntity.ok().build(); // Authentication successful
        } else {
            throw new AuthenticationFailed("Invalid username or password ");
        }
    }
}

