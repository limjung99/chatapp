package com.example.chatapp;

import com.example.chatapp.controller.AuthController;
import com.example.chatapp.dto.request.LoginRequest;
import com.example.chatapp.dto.request.SignupRequest;
import com.example.chatapp.entity.User;
import com.example.chatapp.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("유저 회원가입 컨트롤러 테스트")
    @Test
    public void userSignupControllerTest() throws Exception {
        SignupRequest signupRequest = SignupRequest.builder()
                .name("test")
                .email("test@test")
                .userId("test")
                .password("test")
                .build();

        User expectedUser = new User();
        expectedUser.setUserId("test");

        Mockito.when(authService.signUp(Mockito.any(SignupRequest.class)))
                .thenReturn(Optional.of(expectedUser));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @DisplayName("유저 로그인 컨트롤러 테스트")
    @Test
    public void userLoginControllerTest() throws Exception {
        LoginRequest loginRequest = LoginRequest.builder()
                .id("test")
                .password("test")
                .build();

        User expectedUser = new User();
        expectedUser.setUserId("test");

        Mockito.when(authService.login(Mockito.any(LoginRequest.class)))
                .thenReturn(Optional.of(expectedUser));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.refreshToken").exists());
    }
}
