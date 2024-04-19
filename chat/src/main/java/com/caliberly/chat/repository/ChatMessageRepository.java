package com.caliberly.chat.repository;

import com.caliberly.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query("SELECT cm FROM ChatMessage cm JOIN cm.user u JOIN cm.chatRoom")
    List<ChatMessage> findAllWithUserAndChatRoom();

    @Query("SELECT cm FROM ChatMessage cm JOIN cm.user u JOIN cm.chatRoom where cm.sender =:username")
    List<ChatMessage> findByUsername(String username);
}

