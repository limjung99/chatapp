package com.example.chatapp;

import com.example.chatapp.dto.request.LoginRequest;
import com.example.chatapp.dto.request.SignupRequest;
import com.example.chatapp.entity.User;
import com.example.chatapp.exception.UserDoesNotExistException;
import com.example.chatapp.repository.UserRepository;
import com.example.chatapp.service.AuthService;
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
        SignupRequest signupRequest = SignupRequest.builder()
                .userId("test")
                .password("test")
                .name("test")
                .email("test@test")
                .build();

        Optional<User> user = authService.signUp(signupRequest);
        assertThat(user).isPresent();
    }

    @DisplayName("잘못된 유저 회원가입 테스트")
    @Test
    public void testWrongUserRegister(){
        SignupRequest signupRequest = SignupRequest.builder()
                .userId("test")
                .password("test")
                .name("test")
                .email("testtest")
                .build();

        assertThatThrownBy(()-> authService.signUp(signupRequest))
                .isInstanceOf(UserDoesNotExistException.class);
    }

    @DisplayName("로그인 테스트")
    @Test
    public void testLogin(){
        SignupRequest signupRequest = SignupRequest.builder()
                .userId("test")
                .password("test")
                .name("test")
                .email("test@test")
                .build();

        authService.signUp(signupRequest);

        LoginRequest loginRequest = LoginRequest.builder()
                .id("test")
                .password("test")
                .build();

        Optional<User> user = authService.login(loginRequest);
        assertThat(user.get().getUserId()).isEqualTo(loginRequest.getId());
    }
}
