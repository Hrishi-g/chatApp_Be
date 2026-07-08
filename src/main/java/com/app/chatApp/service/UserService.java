package com.app.chatApp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.chatApp.repository.MessagesRepo;
import com.app.chatApp.vo.Messages;

@Service
public class UserService {

    private MessagesRepo messagesRepo;

    UserService(MessagesRepo messagesRepo) {
        this.messagesRepo = messagesRepo;
    }

    public ResponseEntity<?> getChats(String mobNO) {
        List<Messages> messages = messagesRepo.findAllBySenderMobNO(mobNO);
        System.out.println(messages);
        return ResponseEntity.ok(mobNO);
    }
}
