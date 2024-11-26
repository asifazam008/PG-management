package com.pg_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pg_management.entity.LoginRequestDTO;
import com.pg_management.entity.User;
import com.pg_management.entity.UserRegistrationDTO;
import com.pg_management.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000") // Allow specific origin
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint to register a new user.
     *
     * @param userRegistrationDTO the registration details
     * @return ResponseEntity with success or error message
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        try {
            userService.registerUser(userRegistrationDTO);
            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error registering user: " + e.getMessage());
        }
    }

    /**
     * Endpoint to retrieve all registered users.
     *
     * @return List of all users
     */
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /**
     * Endpoint for user login.
     *
     * @param loginRequest LoginRequestDTO with email and password
     * @return ResponseEntity with login success or error message
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest) {
        User user = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());

        if (user != null) {
            return ResponseEntity.ok("Login successful for role: " + user.getRole());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
