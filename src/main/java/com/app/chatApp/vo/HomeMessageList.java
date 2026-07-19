package com.app.chatApp.vo;

import java.time.LocalDateTime;

import com.app.chatApp.vo.enums.MessageStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HomeMessageList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long msgId;
    String sender;
    String receiver;
    String lastMsg;
    @Enumerated(EnumType.STRING)
    MessageStatus status;
    LocalDateTime lastMessageTime = LocalDateTime.now();

    public Long getMsgId() {
        return msgId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public LocalDateTime getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(LocalDateTime lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

}
