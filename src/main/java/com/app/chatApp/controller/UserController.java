package com.app.chatApp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatApp.dto.ChatDto;
import com.app.chatApp.dto.GetChatsRequestDto;
import com.app.chatApp.dto.SecurityContextDto;
import com.app.chatApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/chats")
    public ResponseEntity<List<ChatDto>> getChats(@AuthenticationPrincipal SecurityContextDto userData,
            @RequestBody GetChatsRequestDto req) {
        System.out.println("req " + req.getReceiver());
        return userService.getChatsBtwnUsers(userData.getMblNo(), req.getReceiver());
    }

    @PostMapping("/getNewUser")
    public ResponseEntity<String> getNewUser(@RequestBody GetChatsRequestDto req) {
        return userService.getNewUser(req.getReceiver());
    }

    @GetMapping("/allHomeChats")
    public ResponseEntity<?> getAllHomeChats(@AuthenticationPrincipal SecurityContextDto userData) {
        return userService.getHomeMessageChat(userData.getMblNo());
    }
}