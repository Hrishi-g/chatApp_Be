package com.app.chatApp.vo;

import java.time.LocalDateTime;

import com.app.chatApp.vo.enums.MessageStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long msgId;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    RegisteredUsers sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    RegisteredUsers receiver;
    String msg;
    @Enumerated(EnumType.STRING)
    MessageStatus status;
    LocalDateTime createdTime = LocalDateTime.now();
    LocalDateTime sentTime;
    LocalDateTime delieverdTime;
    LocalDateTime receiverTime;

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public RegisteredUsers getSenderId() {
        return sender;
    }

    public void setSenderId(RegisteredUsers senderId) {
        this.sender = senderId;
    }

    public RegisteredUsers getReceiverId() {
        return receiver;
    }

    public void setReceiverId(RegisteredUsers receiverId) {
        this.receiver = receiverId;
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

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
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

    @Override
    public String toString() {
        return "Messages [msgId=" + msgId + ", senderId=" + sender + ", receiverId=" + receiver + ", msg=" + msg
                + ", status=" + status + ", createdTime=" + createdTime + ", sentTime=" + sentTime + ", delieverdTime="
                + delieverdTime + ", receiverTime=" + receiverTime + "]";
    }
}
