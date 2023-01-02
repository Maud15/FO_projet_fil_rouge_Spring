package com.m2i.calendar.security.jwt;

public class JwtResponse {

    private String pseudo;
    private String token;

    public JwtResponse(String pseudo, String token) {
        this.pseudo = pseudo;
        this.token = token;
    }

    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

}
