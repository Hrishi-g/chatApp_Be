package com.app.chatApp.dto;

import java.time.LocalDateTime;

import com.app.chatApp.vo.enums.MessageStatus;

public class ChatDto {
    String sender;
    String receiver;
    String msg;
    MessageStatus status;
    LocalDateTime sentTime;
    LocalDateTime delieverdTime;
    LocalDateTime receiverTime;

    public ChatDto(String sender, String receiver, String msg, MessageStatus status, LocalDateTime sentTime,
            LocalDateTime delieverdTime, LocalDateTime receiverTime) {
        this.sender = sender;
        this.receiver = receiver;
        this.msg = msg;
        this.status = status;
        this.sentTime = sentTime;
        this.delieverdTime = delieverdTime;
        this.receiverTime = receiverTime;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public void setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
    }

    public LocalDateTime getDelieverdTime() {
        return delieverdTime;
    }

    public void setDelieverdTime(LocalDateTime delieverdTime) {
        this.delieverdTime = delieverdTime;
    }

    public LocalDateTime getReceiverTime() {
        return receiverTime;
    }

    public void setReceiverTime(LocalDateTime receiverTime) {
        this.receiverTime = receiverTime;
    }
}
