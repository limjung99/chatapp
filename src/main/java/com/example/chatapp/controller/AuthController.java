package com.example.chatapp.controller;

import com.example.chatapp.dto.request.LoginRequest;
import com.example.chatapp.dto.request.SignupRequest;
import com.example.chatapp.entity.User;
import com.example.chatapp.service.AuthService;
import com.example.chatapp.service.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    AuthService authService;
    public AuthController(AuthServiceImpl authService){
        this.authService = authService;
    }

    @GetMapping("/health")
    public ResponseEntity<?> healthCheck(){
        return ResponseEntity.ok("ok");
    }
    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Optional<User> user = authService.login(loginRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest){
        Optional<User> user = authService.signUp(signupRequest);
        return ResponseEntity.ok().build();
    }
}
