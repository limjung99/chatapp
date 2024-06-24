package com.example.chatapp.service;

import com.example.chatapp.dto.LoginDto;
import com.example.chatapp.dto.SignUpDto;
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
    public Optional<User> login(LoginDto loginDto) {
        Optional<User> user = userRepository.findByUserId(loginDto.getId());

        if(user.isEmpty())
            throw new UserDoesNotExistException("user does not exists");


        return user;
    }

    @Override
    public Optional<User> signUp(SignUpDto signUpDto) {
        User user = new User();
        user.setUserId(signUpDto.getUserId());
        user.setEmail(signUpDto.getEmail());
        user.setName(signUpDto.getName());
        user.setPassword(signUpDto.getPassword());

        if(!user.getEmail().contains("@"))
            throw new UserDoesNotExistException("Invalid email format");

        return Optional.of(userRepository.save(user));
    }
}
