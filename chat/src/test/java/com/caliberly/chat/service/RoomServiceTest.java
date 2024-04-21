package com.caliberly.chat.service;

import com.caliberly.chat.entity.ChatRoom;
import com.caliberly.chat.repository.ChatRoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class RoomServiceTest {

    @Mock
    private ChatRoomRepository chatRoomRepository;

    @InjectMocks
    private RoomService roomService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetChatRoomById() {
        Long roomId = 1L;
        ChatRoom expectedRoom = new ChatRoom();
        expectedRoom.setId(roomId);
        expectedRoom.setName("Room 1");

        when(chatRoomRepository.findById(roomId)).thenReturn(Optional.of(expectedRoom));

        ChatRoom actualRoom = roomService.getChatRoomById(roomId);

        Assertions.assertEquals(expectedRoom.getId(), actualRoom.getId());
        Assertions.assertEquals(expectedRoom.getName(), actualRoom.getName());
    }

    @Test
    public void testGetChatRoomById_NotFound() {
        Long roomId = 1L;

        when(chatRoomRepository.findById(roomId)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            roomService.getChatRoomById(roomId);
        });
    }
}
