package com.example.chatapp.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;

@Entity
public class User {
    @Id
    @GeneratedValue
    int id;
    String userId;
    String password;
    String email;
    String name;
}
