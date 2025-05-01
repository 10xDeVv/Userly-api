package org.example.userly.services;

import org.example.userly.dto.LoginRequest;
import org.example.userly.dto.SignupRequest;
import org.example.userly.models.User;
import org.example.userly.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<String> createUser(SignupRequest req) {
        if (userRepository.findDistinctByEmail(req.getEmail()).isPresent()){
            return Optional.of("Email already exists");
        }

        if (userRepository.findDistinctByUserName(req.getUsername()).isPresent()){
            return Optional.of("Username already exists");
        }

        User user = User.builder()
                .userName(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .email(req.getEmail())
                .build();

        userRepository.save(user);
        return Optional.empty();
    }

    public Optional<User> loginUser(LoginRequest req) {
        String userInfo = req.getUserInfo();
        String password = req.getPassword();

        Optional<User> user;

        if (isEmail(userInfo)){
            user = userRepository.findDistinctByEmail(userInfo);
        }else{
            user = userRepository.findDistinctByUserName(userInfo);
        }

        return user.filter(user1 -> passwordEncoder.matches(password, user1.getPassword()));
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findDistinctByEmail(email);
    }

    public Optional<User> getUserByName(String username) {
        return userRepository.findDistinctByUserName(username);
    }

    private boolean isEmail(String input) {
        return input.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}
