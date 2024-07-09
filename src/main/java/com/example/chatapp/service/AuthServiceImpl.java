package com.example.chatapp.service;

import com.example.chatapp.dto.request.LoginRequest;
import com.example.chatapp.dto.request.SignupRequest;
import com.example.chatapp.entity.User;
import com.example.chatapp.exception.UserDoesNotExistException;
import com.example.chatapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    UserRepository userRepository;
    // TODO : password encoder di
    public AuthServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public Optional<User> login(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByUserId(loginRequest.getId());

        if(user.isEmpty())
            throw new UserDoesNotExistException("user does not exists");


        return user;
    }

    @Override
    public Optional<User> signUp(SignupRequest signupRequest) {
        User user = User.builder()
                        .userId(signupRequest.getUserId())
                        .email(signupRequest.getEmail())
                        .name(signupRequest.getName())
                        .password(signupRequest.getPassword())
                        .build();

        if(!user.getEmail().contains("@"))
            throw new UserDoesNotExistException("Invalid email format");

        return Optional.of(userRepository.save(user));
    }
}
