package com.example.chatapp.service;

import com.example.chatapp.dto.request.LoginRequest;
import com.example.chatapp.dto.request.SignupRequest;
import com.example.chatapp.entity.User;

import java.util.Optional;

public interface AuthService {
    Optional<User> login(LoginRequest loginRequest);
    Optional<User> signUp(SignupRequest signupRequest);
}
