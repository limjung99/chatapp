package com.example.chatapp.service;

import com.example.chatapp.dto.LoginDto;
import com.example.chatapp.dto.SignUpDto;
import com.example.chatapp.entity.User;

import java.util.Optional;

public interface AuthService {
    Optional<User> login(LoginDto loginDto);
    Optional<User> signUp(SignUpDto signUpDto);
}
