package com.app.chatApp.service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OneTimeTicketService {

    private static class TicketInfo {
        private final String mblNo;
        private final long expiryTime;

        public TicketInfo(String mblNo, long ttlMs) {
            this.mblNo = mblNo;
            this.expiryTime = System.currentTimeMillis() + ttlMs;
        }

        public String getMblNo() {
            return mblNo;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }

    private final Map<String, TicketInfo> tickets = new ConcurrentHashMap<>();
    private static final long TICKET_TTL_MS = 15000; // 15 seconds

    public String generateOneTimeTicket() {
        // Retrieve the authenticated user's mobile number from Spring Security context
        String mblNo = SecurityContextHolder.getContext().getAuthentication().getName();
        
        String ticket = UUID.randomUUID().toString();
        tickets.put(ticket, new TicketInfo(mblNo, TICKET_TTL_MS));
        return ticket;
    }

    public String redeemTicket(String ticketId) {
        TicketInfo info = tickets.remove(ticketId); // Remove immediately to make it one-time use
        if (info != null && !info.isExpired()) {
            return info.getMblNo();
        }
        return null;
    }
}
