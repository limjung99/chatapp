package com.example.chatapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDto {
    String userId;
    String password;
    String email;
    String name;
}
