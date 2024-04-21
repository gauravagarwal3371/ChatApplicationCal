package com.caliberly.chat.service;

import com.caliberly.chat.entity.ChatMessage;
import com.caliberly.chat.repository.ChatMessageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ChatServiceTest {

    @Mock
    private ChatMessageRepository chatMessageRepository;

    @InjectMocks
    private ChatService chatService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetChatHistory() {
        ChatMessage message1 = new ChatMessage();
        message1.setMessage("Hello");
        ChatMessage message2 = new ChatMessage();
        message2.setMessage("World");
        List<ChatMessage> expectedMessages = Arrays.asList(message1, message2);

        when(chatMessageRepository.findAllWithUserAndChatRoom()).thenReturn(expectedMessages);

        List<ChatMessage> actualMessages = chatService.getChatHistory();

        Assertions.assertEquals(expectedMessages.size(), actualMessages.size());
        Assertions.assertEquals(expectedMessages.get(0).getMessage(), actualMessages.get(0).getMessage());
        Assertions.assertEquals(expectedMessages.get(1).getMessage(), actualMessages.get(1).getMessage());
    }

    @Test
    public void testSendMessage() {
        ChatMessage message = new ChatMessage();
        message.setMessage("Hello, world!");

        chatService.sendMessage(message);

        verify(chatMessageRepository, times(1)).save(message);
    }

    @Test
    public void testFindByUsername() {
        String username = "user1";
        ChatMessage message1 = new ChatMessage();
        message1.setMessage("Hello");
        message1.setSender(username);
        ChatMessage message2 = new ChatMessage();
        message2.setMessage("World");
        message2.setSender(username);
        List<ChatMessage> expectedMessages = Arrays.asList(message1, message2);

        when(chatMessageRepository.findByUsername(username)).thenReturn(expectedMessages);

        List<ChatMessage> actualMessages = chatService.findByUsername(username);

        Assertions.assertEquals(expectedMessages.size(), actualMessages.size());
        Assertions.assertEquals(expectedMessages.get(0).getMessage(), actualMessages.get(0).getMessage());
        Assertions.assertEquals(expectedMessages.get(1).getMessage(), actualMessages.get(1).getMessage());
    }
}
