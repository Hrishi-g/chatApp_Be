package com.app.chatApp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatApp.dto.ChatDto;
import com.app.chatApp.dto.SecurityContextDto;
import com.app.chatApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/chats")
    public ResponseEntity<List<ChatDto>> getChats(@AuthenticationPrincipal SecurityContextDto userData) {
        return userService.getChats(userData.getMblNo());
    }

    @GetMapping("/getNewUser")
    public ResponseEntity<String> getNewUser(@RequestParam String mob) {
        return userService.getNewUser(mob);
    }

    @GetMapping("/allHomeChats")
    public ResponseEntity<?> getAllHomeChats(@AuthenticationPrincipal SecurityContextDto userData) {
        return userService.getHomeMessageChat(userData.getMblNo());
    }
}