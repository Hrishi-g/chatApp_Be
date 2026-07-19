package com.app.chatApp.dto;

import java.time.LocalDateTime;

import com.app.chatApp.vo.enums.MessageStatus;

public class LastMsgChatDTo {
    String sender;
    String receiver;
    String lastMsg;
    MessageStatus status;
    LocalDateTime lastMessageTime;

    public LastMsgChatDTo(String sender, String receiver, String lastMsg, MessageStatus status,
            LocalDateTime lastMessageTime) {
        this.sender = sender;
        this.receiver = receiver;
        this.lastMsg = lastMsg;
        this.status = status;
        this.lastMessageTime = lastMessageTime;
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
