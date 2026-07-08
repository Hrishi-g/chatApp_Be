package com.app.chatApp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public void getChats(@AuthenticationPrincipal SecurityContextDto userData) {
        userService.getChats(userData.getMblNo());
    }

    @GetMapping("/getNewUser")
    public String getNewUser(@RequestParam String mob) {
        return userService.getNewUser(mob);
    }

}