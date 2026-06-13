package com.splitwise.splitwise.service;


import com.splitwise.splitwise.dto.request.RegisterRequest;
import com.splitwise.splitwise.entity.User;
import com.splitwise.splitwise.repository.UserRepository;
import com.splitwise.splitwise.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already registered";
        }
        User newUser = new User(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );
        userRepository.save(newUser);
        return "User registered successfully";
    }

    public String login(RegisterRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isEmpty()) {
            return "User not found";
        }
        User user = userOptional.get();
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return jwtUtil.generateToken(user.getEmail());
        }
        return "Invalid credentials";
    }
}