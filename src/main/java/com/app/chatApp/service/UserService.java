package com.app.chatApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.chatApp.dto.ChatDto;
import com.app.chatApp.dto.LastMsgChatDTo;
import com.app.chatApp.repository.HomeMessageListRepo;
import com.app.chatApp.repository.MessagesRepo;
import com.app.chatApp.repository.RegisteredUsersRepo;

@Service
public class UserService {

    private MessagesRepo messagesRepo;
    private RegisteredUsersRepo registeredUsersRepo;
    private HomeMessageListRepo homeMessageListRepo;

    UserService(MessagesRepo messagesRepo, RegisteredUsersRepo registeredUsersRepo,
            HomeMessageListRepo homeMessageListRepo) {
        this.messagesRepo = messagesRepo;
        this.registeredUsersRepo = registeredUsersRepo;
        this.homeMessageListRepo = homeMessageListRepo;
    }

    public ResponseEntity<List<ChatDto>> getChats(String mobNO) {
        List<ChatDto> messages = messagesRepo.findAllBySenderMobNO(mobNO);
        return ResponseEntity.ok(messages);
    }

    public ResponseEntity<?> getHomeMessageChat(String mobNO) {
        List<LastMsgChatDTo> messages = homeMessageListRepo.findLastMsgChatList(mobNO);
        if (!messages.isEmpty()) {
            return ResponseEntity.ok(messages);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
    }

    public ResponseEntity<String> getNewUser(String mobNO) {
        Optional<String> receiver = registeredUsersRepo.findByMblNo(mobNO);
        if (receiver.isPresent()) {
            return ResponseEntity.ok(receiver.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
    }
}
