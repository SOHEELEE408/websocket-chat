package com.websocket.chat.domain.room;

import com.websocket.chat.domain.message.ChatMessage;
import com.websocket.chat.service.ChatService;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String roomId;

    private String name;

    @Transient
    private final Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roodId, String name){
        this.roomId=roodId;
        this.name=name;
    }

    public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService){
        if(chatMessage.getType().equals(ChatMessage.MessageType.ENTER)){
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender()+"님이 입장하셨습니다.");
        }
        sendMessage(chatMessage, chatService);
    }

    public <T> void sendMessage(T message, ChatService chatService){
        sessions.parallelStream().forEach(session->chatService.sendMessage(session, message));
    }
}
