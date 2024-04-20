package com.caliberly.chat.controller.websocket;

import com.caliberly.chat.entity.WebSocketChatMessage;
import com.caliberly.chat.repository.WebSocketChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WebSocketChatController {

    @Autowired
    WebSocketChatMessageRepository webSocketChatMessageRepository;
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public WebSocketChatMessage sendMessage(
            @Payload WebSocketChatMessage chatMessage
    ) {
        webSocketChatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.deleteMessage")
    @SendTo("/topic/public")
    public List<WebSocketChatMessage> deleteMessage(
            @Payload String messageId
    ) {
        webSocketChatMessageRepository.deleteById(Long.valueOf(messageId));
        return webSocketChatMessageRepository.findAll();
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public List<WebSocketChatMessage> addUser(
            @Payload WebSocketChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return webSocketChatMessageRepository.findAll();
    }
}
