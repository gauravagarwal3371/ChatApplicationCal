package com.caliberly.chat.service;

import static org.mockito.Mockito.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.caliberly.chat.entity.ChatRoom;
import com.caliberly.chat.repository.ChatRoomRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RoomServiceTest {

    @Mock
    private ChatRoomRepository chatRoomRepository;

    @InjectMocks
    private RoomService roomService;

    @Before
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

        Assert.assertEquals(expectedRoom.getId(), actualRoom.getId());
        Assert.assertEquals(expectedRoom.getName(), actualRoom.getName());
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetChatRoomById_NotFound() {
        Long roomId = 1L;

        when(chatRoomRepository.findById(roomId)).thenReturn(Optional.empty());

        roomService.getChatRoomById(roomId);
    }
}

