package com.example.chatapp.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    Long id;
    @Column(name = "user_id")
    String userId;
    String password;
    String email;
    String name;
}
