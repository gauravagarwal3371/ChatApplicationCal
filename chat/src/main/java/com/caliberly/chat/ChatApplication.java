package com.caliberly.chat;

import com.caliberly.chat.entity.ChatRoom;
import com.caliberly.chat.repository.ChatRoomRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ChatApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(ChatApplication.class, args);

		// Create a single chat room upon server startup
		ChatRoomRepository chatRoomRepository = context.getBean(ChatRoomRepository.class);
		if (chatRoomRepository.count() == 0) {
			ChatRoom chatRoom = new ChatRoom();
			chatRoom.setName("Main Chat Room");
			chatRoomRepository.save(chatRoom);
		}
	}

}
