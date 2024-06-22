package com.example.chatapp;

import com.example.chatapp.dto.SignUpDto;
import com.example.chatapp.entity.User;
import com.example.chatapp.repository.UserRepository;
import com.example.chatapp.service.AuthService;
import com.example.chatapp.service.AuthServiceImpl;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
        System.out.println("======== 유저저장 완료 ========");
    }

    @DisplayName("잘못된 유저 회원가입 테스트")
    @Test
    public void testWrongUserRegister(){
        // TODO
    }

    @DisplayName("AuthService API 응답속도 테스트")
    @Test
    public void testWrongUserRegister(){
        long testStartTime = System.currentTimeMillis();
        // TODO
        long testEndTime = System.currentTimeMillis();
        long offset = testEndTime - testStartTime;

    }
}
