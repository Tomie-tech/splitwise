package com.splitwise.splitwise.controller;

import com.splitwise.splitwise.dto.request.RegisterRequest;
import com.splitwise.splitwise.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody RegisterRequest request) {
        return authService.login(request);
    }
}