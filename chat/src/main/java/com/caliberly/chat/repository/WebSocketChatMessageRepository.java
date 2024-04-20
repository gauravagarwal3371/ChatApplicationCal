package com.caliberly.chat.repository;

import com.caliberly.chat.entity.ChatMessage;
import com.caliberly.chat.entity.WebSocketChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebSocketChatMessageRepository extends JpaRepository<WebSocketChatMessage, Long> {
}
