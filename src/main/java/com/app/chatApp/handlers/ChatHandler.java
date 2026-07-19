package com.app.chatApp.handlers;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.app.chatApp.dto.TransientMessageDto;
import com.app.chatApp.repository.HomeMessageListRepo;
import com.app.chatApp.repository.MessagesRepo;
import com.app.chatApp.service.OneTimeTicketService;
import com.app.chatApp.vo.HomeMessageList;
import com.app.chatApp.vo.Messages;
import com.app.chatApp.vo.enums.MessageStatus;

import tools.jackson.databind.ObjectMapper;

@Component
public class ChatHandler extends TextWebSocketHandler {

    private final OneTimeTicketService ticketService;
    private MessagesRepo messagesRepo;
    private HomeMessageListRepo homeMessageListRepo;

    ChatHandler(OneTimeTicketService ticketService, MessagesRepo messagesRepo,
            HomeMessageListRepo homeMessageListRepo) {
        this.ticketService = ticketService;
        this.messagesRepo = messagesRepo;
        this.homeMessageListRepo = homeMessageListRepo;
    }

    Map<String, WebSocketSession> users = new ConcurrentHashMap<>();

    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String ticket = session.getUri().getQuery().split("=")[1];

        String mblNo = ticketService.redeemTicket(ticket);
        if (mblNo == null) {
            System.out.println("Rejected WebSocket connection: Invalid, expired, or reused ticket: " + ticket);
            session.close(org.springframework.web.socket.CloseStatus.BAD_DATA);
            return;
        }

        session.getAttributes().put("mblNo", mblNo);

        users.put(mblNo, session);
        System.out.println(users.toString());
    }

    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String json = message.getPayload();
        ObjectMapper mapper = new ObjectMapper();
        TransientMessageDto msg = mapper.readValue(json, TransientMessageDto.class);

        String sessionMblNo = (String) session.getAttributes().get("mblNo");
        if (sessionMblNo != null && sessionMblNo.equals(msg.getSender())) {
            WebSocketSession receiverSession = users.get(msg.getReceiver());
            // Both Online
            if (receiverSession != null && receiverSession.isOpen()) {

                receiverSession.sendMessage(new TextMessage(mapper.writeValueAsString(msg)));

                Messages storingMsg = new Messages();
                storingMsg.setSender(msg.getSender());
                storingMsg.setReceiver(msg.getReceiver());
                storingMsg.setMsg(msg.getMessage());
                storingMsg.setStatus(MessageStatus.DELIVERED);
                storingMsg.setSentTime(LocalDateTime.now());
                storingMsg.setDelieverdTime(LocalDateTime.now());
                // storingMsg.setReceiverTime();
                messagesRepo.save(storingMsg);

                Optional<HomeMessageList> existingChat = homeMessageListRepo
                        .checkIfUserExistInHomeMessageChat(msg.getSender(), msg.getReceiver());
                if (existingChat.isEmpty()) {
                    HomeMessageList homeMessageList = new HomeMessageList();
                    homeMessageList.setSender(msg.getSender());
                    homeMessageList.setReceiver(msg.getReceiver());
                    homeMessageList.setLastMsg(msg.getMessage());
                    homeMessageList.setStatus(MessageStatus.DELIVERED);
                    homeMessageListRepo.save(homeMessageList);
                } else {
                    HomeMessageList homeMessageList = existingChat.get();
                    homeMessageList.setLastMsg(msg.getMessage());
                    homeMessageList.setStatus(MessageStatus.DELIVERED);
                    homeMessageList.setLastMessageTime(LocalDateTime.now());
                    homeMessageListRepo.save(homeMessageList); // UPDATE
                }

                System.out.println(storingMsg);
            }
            // sender Online , Receiver Offline
            else {
                Messages storingMsg = new Messages();
                storingMsg.setSender(msg.getSender());
                storingMsg.setReceiver(msg.getReceiver());
                storingMsg.setMsg(msg.getMessage());
                storingMsg.setStatus(MessageStatus.SENT);
                storingMsg.setSentTime(LocalDateTime.now());
                storingMsg.setDelieverdTime(LocalDateTime.now());

                messagesRepo.save(storingMsg);
                Optional<HomeMessageList> existingChat = homeMessageListRepo
                        .checkIfUserExistInHomeMessageChat(msg.getSender(), msg.getReceiver());
                if (existingChat.isEmpty()) {
                    HomeMessageList homeMessageList = new HomeMessageList();
                    homeMessageList.setSender(msg.getSender());
                    homeMessageList.setReceiver(msg.getReceiver());
                    homeMessageList.setLastMsg(msg.getMessage());
                    homeMessageList.setStatus(MessageStatus.SENT);
                    homeMessageListRepo.save(homeMessageList);
                } else {
                    HomeMessageList homeMessageList = existingChat.get();
                    homeMessageList.setLastMsg(msg.getMessage());
                    homeMessageList.setStatus(MessageStatus.SENT);
                    homeMessageList.setLastMessageTime(LocalDateTime.now());
                    homeMessageListRepo.save(homeMessageList); // UPDATE
                }
                System.out.println(storingMsg);
                System.out.println("Reciever Offline");
            }
        } else {
            System.out.println(
                    "Sender mismatch or unauthorized: msg sender=" + msg.getSender() + ", session=" + sessionMblNo);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status)
            throws Exception {
        String mblNo = (String) session.getAttributes().get("mblNo");
        if (mblNo != null) {
            users.remove(mblNo);
            System.out.println("Connection closed. Removed user: " + mblNo + ". Remaining active: " + users.keySet());
        }
    }
}
