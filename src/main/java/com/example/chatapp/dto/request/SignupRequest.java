package com.example.chatapp.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignupRequest {
    String userId;
    String password;
    String email;
    String name;
}
