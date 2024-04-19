package com.caliberly.chat.service;

import com.caliberly.chat.entity.ChatRoom;
import com.caliberly.chat.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    ChatRoomRepository chatRoomRepository;
    public ChatRoom getChatRoomById(Long id) {
        return chatRoomRepository.findById(id).get();
    }
}
