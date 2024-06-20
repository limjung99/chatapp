package com.example.chatapp.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    int id;
    @Column(name = "user_id")
    String userId;
    String password;
    String email;
    String name;
}
