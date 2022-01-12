package com.websocket.chat.domain.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
@Setter
public class ChatMessage {
    public enum MessageType{
        ENTER, TALK
    }

    private MessageType type;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String roodId;

    private String sender;
    private String message;
}
