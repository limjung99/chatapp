package com.example.chatapp.controller;

import com.example.chatapp.dto.ChatMessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public class ChatController {

    /**
     * Now Chatroom is inmemory
     * Later, Change with JPA Persist area;
     */

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

    @MessageMapping("/chatting/{chatRoomId}")
    @SendTo("/topic/chatting/{chatRoomId}")
    public ChatMessageDto chat(@DestinationVariable Long chatRoomId, String message){
        ChatMessageDto chatMessageDto = new ChatMessageDto();
        chatMessageDto.setMessage(message);
        return chatMessageDto;
    }
}
