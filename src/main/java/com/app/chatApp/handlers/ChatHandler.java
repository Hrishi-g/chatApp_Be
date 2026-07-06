package com.app.chatApp.handlers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.app.chatApp.dto.MessageDto;
import com.app.chatApp.service.OneTimeTicketService;

import tools.jackson.databind.ObjectMapper;

@Component
public class ChatHandler extends TextWebSocketHandler {

    private final OneTimeTicketService ticketService;

    ChatHandler(OneTimeTicketService ticketService) {
        this.ticketService = ticketService;
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
        MessageDto msg = mapper.readValue(json, MessageDto.class);
        String sender = msg.getSender();
        String receiver = msg.getReceiver();
        String sessionMblNo = (String) session.getAttributes().get("mblNo");
        if (sessionMblNo != null && sessionMblNo.equals(sender)) {
            WebSocketSession receiverSession = users.get(receiver);
            if (receiverSession != null && receiverSession.isOpen()) {
                receiverSession.sendMessage(new TextMessage(mapper.writeValueAsString(msg)));
                System.out.println(mapper.writeValueAsString(msg));
            } else {
                System.out.println("receiver not found");
            }
        } else {
            System.out.println("Sender mismatch or unauthorized: msg sender=" + sender + ", session=" + sessionMblNo);
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
