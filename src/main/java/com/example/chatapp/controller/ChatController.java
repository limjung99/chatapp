package com.example.chatapp.controller;

import com.example.chatapp.dto.ChatMessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @MessageMapping("/chat/sendMessage")
    @SendTo("/topic/public")
    public ChatMessageDto messageBroker(){
        ChatMessageDto chatMessageDto = new ChatMessageDto();
        chatMessageDto.setMessage("hello!");
        return chatMessageDto;
    }
}
