package com.example.chatapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/chat")
public class ChatController {
    @PostMapping("")
    public ResponseEntity genChatRoom(){
        // TODO
        return null;
    }
    @GetMapping("")
    public ResponseEntity getAllChatRooms(){
        // TODO
        return null;
    }
}
