package com.example.chatapp.controller;

import com.example.chatapp.dto.LoginDto;
import com.example.chatapp.dto.SignUpDto;
import com.example.chatapp.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @PostMapping("users/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){

    }

    @PostMapping("users")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto){

    }
}
