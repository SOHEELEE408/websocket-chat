package com.websocket.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.websocket.chat.domain.message.ChatMessage;
import com.websocket.chat.domain.message.ChatMessageRepository;
import com.websocket.chat.domain.room.ChatRoom;
import com.websocket.chat.domain.room.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms;

    private final ChatRoomRepository chatRoomRepository;
    //private final ChatMessageRepository chatMessageRepository;

    @PostConstruct
    private void init(){
        chatRooms = new LinkedHashMap<>();
    }

    @Transactional
    public String saveRoom(ChatRoom chatRoom){
        return chatRoomRepository.save(chatRoom).getRoomId();
    }

    public List<ChatRoom> findAllRooms(){
        return chatRoomRepository.findAll();
    }

    public ChatRoom findRoomById(String roomId){
        ChatRoom room = chatRoomRepository.findById(roomId).orElseThrow(()->new IllegalArgumentException("해당 채팅방이 없습니다. id= "+roomId));
        return room;
    }

    public ChatRoom createRoom(String name){
        String randomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.builder()
                .roodId(randomId)
                .name(name)
                .build();
        saveRoom(chatRoom);
        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch(IOException e){
            log.error(e.getMessage(),e);
        }
    }
}
