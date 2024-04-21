package com.caliberly.chat.controller;

import com.caliberly.chat.entity.ChatMessage;
import com.caliberly.chat.entity.ChatRoom;
import com.caliberly.chat.entity.User;
import com.caliberly.chat.service.ChatService;
import com.caliberly.chat.service.RoomService;
import com.caliberly.chat.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ChatControllerTest {

    @Mock
    private ChatService chatService;

    @Mock
    private UserService userService;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private ChatController chatController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendMessage() {
        ChatMessage message = new ChatMessage();
        message.setSender("testuser");
        User user = new User();
        user.setUsername("testuser");
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setId(1L);

        when(userService.getUserByUsername(message.getSender())).thenReturn(user);
        when(roomService.getChatRoomById(1L)).thenReturn(chatRoom);

        ResponseEntity<?> response = chatController.sendMessage(message);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetMessage() {
        String username = "testuser";
        ChatMessage message1 = new ChatMessage();
        message1.setMessage("Hello");
        ChatMessage message2 = new ChatMessage();
        message2.setMessage("World");
        List<ChatMessage> chatMessages = Arrays.asList(message1, message2);

        when(chatService.findByUsername(username)).thenReturn(chatMessages);

        ResponseEntity<List<ChatMessage>> response = chatController.getMessage(username);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(chatMessages, response.getBody());
    }

    @Test
    public void testGetChatHistory() {
        ChatMessage message1 = new ChatMessage();
        message1.setMessage("Hello");
        ChatMessage message2 = new ChatMessage();
        message2.setMessage("World");
        List<ChatMessage> chatHistory = Arrays.asList(message1, message2);

        when(chatService.getChatHistory()).thenReturn(chatHistory);

        ResponseEntity<List<ChatMessage>> response = chatController.getChatHistory();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(chatHistory, response.getBody());
    }
}
