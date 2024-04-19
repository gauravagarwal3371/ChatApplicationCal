package com.caliberly.chat.service;

import com.caliberly.chat.entity.ChatMessage;
import com.caliberly.chat.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public void sendMessage(ChatMessage message) {
        chatMessageRepository.save(message);
    }

    public List<ChatMessage> getChatHistory() {
        return chatMessageRepository.findAllWithUserAndChatRoom();
    }

    public List<ChatMessage> findByUsername(String username){
        return chatMessageRepository.findByUsername(username);
    }
}

