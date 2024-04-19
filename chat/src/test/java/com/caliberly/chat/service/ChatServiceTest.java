package com.caliberly.chat.service;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.caliberly.chat.entity.ChatMessage;
import com.caliberly.chat.repository.ChatMessageRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ChatServiceTest {

    @Mock
    private ChatMessageRepository chatMessageRepository;

    @InjectMocks
    private ChatService chatService;

    @Before
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

        Assert.assertEquals(expectedMessages.size(), actualMessages.size());
        Assert.assertEquals(expectedMessages.get(0).getMessage(), actualMessages.get(0).getMessage());
        Assert.assertEquals(expectedMessages.get(1).getMessage(), actualMessages.get(1).getMessage());
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

        Assert.assertEquals(expectedMessages.size(), actualMessages.size());
        Assert.assertEquals(expectedMessages.get(0).getMessage(), actualMessages.get(0).getMessage());
        Assert.assertEquals(expectedMessages.get(1).getMessage(), actualMessages.get(1).getMessage());
    }

}

