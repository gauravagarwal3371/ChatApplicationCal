package com.caliberly.chat.controller;

import com.caliberly.chat.entity.ChatMessage;
import com.caliberly.chat.entity.ChatRoom;
import com.caliberly.chat.entity.User;
import com.caliberly.chat.service.ChatService;
import com.caliberly.chat.service.RoomService;
import com.caliberly.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody ChatMessage message) {
        User user = userService.getUserByUsername(message.getSender());
        ChatRoom chatRoom = roomService.getChatRoomById(1l);
        message.setChatRoom(chatRoom);
        message.setUser(user);
        chatService.sendMessage(message);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<List<ChatMessage>> getMessage(@PathVariable String username) {
        List<ChatMessage> chatMessages = chatService.findByUsername(username);
        return ResponseEntity.ok(chatMessages);
    }

    @GetMapping("/history")
    public ResponseEntity<List<ChatMessage>> getChatHistory() {
        List<ChatMessage> history = chatService.getChatHistory();
        return ResponseEntity.ok(history);
    }
}
