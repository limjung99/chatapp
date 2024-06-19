package com.example.chatapp.service;

import com.example.chatapp.dto.LoginDto;
import com.example.chatapp.dto.SignUpDto;
import com.example.chatapp.entity.User;

public interface AuthService {
    boolean login(LoginDto loginDto);
    User signUp(SignUpDto signUpDto);
}
