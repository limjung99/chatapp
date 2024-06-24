package com.example.chatapp.exception;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(String message){
        super(message);
    }
}
