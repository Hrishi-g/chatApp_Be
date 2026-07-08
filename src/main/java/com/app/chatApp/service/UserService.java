package com.app.chatApp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.chatApp.repository.MessagesRepo;
import com.app.chatApp.repository.RegisteredUsersRepo;
import com.app.chatApp.vo.Messages;

@Service
public class UserService {

    private MessagesRepo messagesRepo;
    private RegisteredUsersRepo registeredUsersRepo;

    UserService(MessagesRepo messagesRepo, RegisteredUsersRepo registeredUsersRepo) {
        this.messagesRepo = messagesRepo;
        this.registeredUsersRepo = registeredUsersRepo;
    }

    public ResponseEntity<?> getChats(String mobNO) {
        List<Messages> messages = messagesRepo.findAllBySenderMobNO(mobNO);
        System.out.println(messages);
        return ResponseEntity.ok(mobNO);
    }

    public String getNewUser(String mobNO) {
        String receiver = registeredUsersRepo.findByMblNo(mobNO).orElse("No User Found");
        return receiver;
    }
}
