package com.example.chatapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignUpDto {
    String userId;
    String password;
    String email;
    String name;
}
