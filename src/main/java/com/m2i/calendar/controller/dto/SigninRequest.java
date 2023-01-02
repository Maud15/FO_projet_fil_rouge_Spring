package com.m2i.calendar.controller.dto;

public class SigninRequest {

    private String pseudo;
    private String password;

    public SigninRequest() {
    }

    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
