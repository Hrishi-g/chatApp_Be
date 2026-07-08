package com.app.chatApp.dto;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityContextDto implements UserDetails {
    private String name;
    private String mblNo;

    public SecurityContextDto() {
    }

    public SecurityContextDto(String name, String mblNo) {
        this.name = name;
        this.mblNo = mblNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMblNo() {
        return mblNo;
    }

    public void setMblNo(String mblNo) {
        this.mblNo = mblNo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return mblNo;
    }

    @Override
    public String toString() {
        return "SecurityContextDto [name=" + name + ", mblNo=" + mblNo + "]";
    }
}

