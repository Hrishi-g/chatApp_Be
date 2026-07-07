package com.app.chatApp.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class CookieUtil {
    public void addJwtCookie(HttpServletResponse response, String jwtToken) {
        ResponseCookie cookie = ResponseCookie.from("jwt", jwtToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .sameSite("Lax")
                .maxAge(3600)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

    }
}

// testinging
