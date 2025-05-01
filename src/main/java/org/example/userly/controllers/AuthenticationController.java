package org.example.userly.controllers;

import org.example.userly.dto.LoginRequest;
import org.example.userly.dto.UserDTO;
import org.example.userly.models.User;
import org.example.userly.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        Optional<User> user = userService.loginUser(req);
        if (user.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("Error", "Invalid credentials"));
        }

        UserDTO userDTO = new UserDTO(user.get().getUserName(), user.get().getEmail());
        return ResponseEntity.ok(userDTO);
    }
}
