package com.app.chatApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.chatApp.service.OneTimeTicketService;

@RestController
@RequestMapping("/secure")
public class OneTimeTicketController {

    private final OneTimeTicketService oneTimeTicketService;

    OneTimeTicketController(OneTimeTicketService oneTimeTicketService) {
        this.oneTimeTicketService = oneTimeTicketService;
    }

    @PostMapping("/ws-ticket")
    public ResponseEntity<String> generateOneTimeTicket() {
        String ticket = oneTimeTicketService.generateOneTimeTicket();
        return ResponseEntity.ok(ticket);
    }
}
