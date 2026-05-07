package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthRequest request) {

        // simple demo validation
        if ("admin".equals(request.getUsername()) &&
                "password".equals(request.getPassword())) {

            String token = jwtService.generateToken(request.getUsername());
            return Map.of("token", token);
        }

        throw new RuntimeException("Invalid credentials");
    }
}