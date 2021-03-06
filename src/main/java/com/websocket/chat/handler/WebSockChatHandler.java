//package com.websocket.chat.handler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.websocket.chat.domain.message.ChatMessage;
//import com.websocket.chat.domain.room.ChatRoom;
//import com.websocket.chat.domain.room.ChatRoomRepository;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class WebSockChatHandler extends TextWebSocketHandler {
//    private final ObjectMapper objectMapper;
//    private final ChatRoomRepository chatRoomRepository;
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String payload = message.getPayload();
//        log.info("payload {}", payload);
////        TextMessage textMessage = new TextMessage("Welcome chatting server");
////        session.sendMessage(textMessage);
//        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
//        ChatRoom room = chatRoomRepository.findRoomById(chatMessage.getRoomId());
////        room.handleActions(session, chatMessage, chatRoomRepository);
//    }
//}
