package com.app.chatApp.dto;

public class LoginDto {
    String mblNo;
    String pass;

    public LoginDto() {
    }

    public LoginDto(String mblNo, String pass) {
        this.mblNo = mblNo;
        this.pass = pass;
    }

    public String getMblNo() {
        return mblNo;
    }

    public void setMblNo(String mblNo) {
        this.mblNo = mblNo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "LoginDto [mblNo=" + mblNo + ", pass=" + pass + "]";
    }

}
