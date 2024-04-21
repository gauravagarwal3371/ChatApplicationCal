package com.caliberly.chat.controller.websocket;

import com.caliberly.chat.entity.WebSocketChatMessage;
import com.caliberly.chat.repository.WebSocketChatMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class WebSocketChatControllerTest {

    @Mock
    private WebSocketChatMessageRepository messageRepository;

    @Mock
    private SimpMessageHeaderAccessor headerAccessor;

    @InjectMocks
    private WebSocketChatController chatController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSendMessage() {
        WebSocketChatMessage message = new WebSocketChatMessage();
        message.setMessage("Hello, world!");

        when(messageRepository.save(message)).thenReturn(message);

        WebSocketChatMessage result = chatController.sendMessage(message);

        assertEquals(message, result);
        verify(messageRepository, times(1)).save(message);
    }

    @Test
    void testDeleteMessage() {
        String messageId = "1";

        List<WebSocketChatMessage> messages = new ArrayList<>();
        WebSocketChatMessage message1 = new WebSocketChatMessage();
        message1.setId(1L);
        messages.add(message1);

        when(messageRepository.findAll()).thenReturn(messages);

        List<WebSocketChatMessage> result = chatController.deleteMessage(messageId);

        assertEquals(1, result.size());
        verify(messageRepository, times(1)).deleteById(1L);
    }

    @Test
    void testAddUser() {
        WebSocketChatMessage chatMessage = new WebSocketChatMessage();
        chatMessage.setSender("testUser");

        List<WebSocketChatMessage> messages = new ArrayList<>();
        WebSocketChatMessage message1 = new WebSocketChatMessage();
        message1.setMessage("Hello");
        WebSocketChatMessage message2 = new WebSocketChatMessage();
        message2.setMessage("World");
        messages.add(message1);
        messages.add(message2);
        Map<String, Object> sessionAttributes = new HashMap<>();
        when(headerAccessor.getSessionAttributes()).thenReturn(sessionAttributes);

        when(messageRepository.findAll()).thenReturn(messages);

        List<WebSocketChatMessage> result = chatController.addUser(chatMessage, headerAccessor);

        assertEquals(2, result.size());
        assertEquals("testUser", sessionAttributes.get("username"));
    }
}

