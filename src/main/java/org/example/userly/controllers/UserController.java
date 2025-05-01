package org.example.userly.controllers;

import org.example.userly.dto.SignupRequest;
import org.example.userly.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody SignupRequest req) {
        Optional<String> result = userService.createUser(req);
        if (result.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("Error", result.get()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Success", "User created successfully"));
    }

}
