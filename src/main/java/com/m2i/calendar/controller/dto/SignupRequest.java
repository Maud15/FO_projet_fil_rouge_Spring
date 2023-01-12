package com.m2i.calendar.controller.dto;

public class SignupRequest {

    private String email;
    private String pseudo;
    private String password;
    private String firstname;
    private String lastname;
    private String city;

    public SignupRequest() {
    }

    public String getEmail() {
        return email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCity() {
        return city;
    }
}
