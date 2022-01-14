package com.websocket.chat.web;

import com.websocket.chat.domain.message.ChatMessage;
import com.websocket.chat.domain.room.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message){
        if(ChatMessage.MessageType.ENTER.equals(message.getType())){
            message.setMessage(message.getSender()+"님이 입장하셨습니다.");
        }
        messagingTemplate.convertAndSend("/sub/chat/room/"+message.getRoomId(), message);
    }

//    @PostMapping
//    public ChatRoom createRoom(@RequestParam String name){
//        return chatService.createRoom(name);
//    }
//
//    @GetMapping
//    public List<ChatRoom> findAllRooms(){
//        return chatService.findAllRooms();
//    }
}
