package com.example.chatapp;

import com.example.chatapp.dto.SignUpDto;
import com.example.chatapp.entity.User;
import com.example.chatapp.repository.UserRepository;
import com.example.chatapp.service.AuthService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    public void testUserLoginSuccess(){

    }

    public void testUserLoginFailure(){

    }

    public void testUserResiter(){
        SignUpDto signUpDto = SignUpDto.builder()
                .password("test")
                .name("test")
                .userId("test")
                .email("test")
                .build();
        Optional<User> signUpUser = authService.signUp(signUpDto);

    }
}
