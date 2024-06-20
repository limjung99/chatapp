package com.example.chatapp.controller;

import com.example.chatapp.dto.LoginDto;
import com.example.chatapp.dto.SignUpDto;
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
    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        Optional<User> user = authService.login(loginDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto){
        Optional<User> user = authService.signUp(signUpDto);
        return ResponseEntity.ok().build();
    }
}
