package com.app.chatApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatApp.dto.LoginDto;
import com.app.chatApp.dto.SignupDto;
import com.app.chatApp.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/user")
public class UserController {

    @Autowired
    private UserService userSrc;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignupDto userDto) {
        return userSrc.signUp(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestHeader("X-Client-Type") String clientType,
            @RequestBody LoginDto userDto, HttpServletResponse httpResponse) {
        System.out.println("X-Client-Type :" + clientType);
        return userSrc.login(userDto, clientType, httpResponse);
    }
}
