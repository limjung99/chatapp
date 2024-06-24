package com.example.chatapp;

import com.example.chatapp.dto.LoginDto;
import com.example.chatapp.dto.SignUpDto;
import com.example.chatapp.entity.User;
import com.example.chatapp.exception.UserDoesNotExistException;
import com.example.chatapp.repository.UserRepository;
import com.example.chatapp.service.AuthService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.util.Optional;

@SpringBootTest
public class AuthServiceTest {
    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp(){
        userRepository.deleteAll();
    }

    @DisplayName("유저 회원가입 서비스 테스트")
    @Test
    public void testUserRegister(){
        SignUpDto signUpDto = SignUpDto.builder()
                .userId("test")
                .password("test")
                .name("test")
                .email("test@test")
                .build();

        Optional<User> user = authService.signUp(signUpDto);
        assertThat(user).isPresent();
    }

    @DisplayName("잘못된 유저 회원가입 테스트")
    @Test
    public void testWrongUserRegister(){
        SignUpDto signUpDto = SignUpDto.builder()
                .userId("test")
                .password("test")
                .name("test")
                .email("testtest")
                .build();

        assertThatThrownBy(()-> authService.signUp(signUpDto))
                .isInstanceOf(UserDoesNotExistException.class);
    }

    @DisplayName("로그인 테스트")
    @Test
    public void testLogin(){
        SignUpDto signUpDto = SignUpDto.builder()
                .userId("test")
                .password("test")
                .name("test")
                .email("test@test")
                .build();

        authService.signUp(signUpDto);

        LoginDto loginDto = LoginDto.builder()
                .id("test")
                .password("test")
                .build();

        Optional<User> user = authService.login(loginDto);
        assertThat(user.get().getUserId()).isEqualTo(loginDto.getId());
    }
}
