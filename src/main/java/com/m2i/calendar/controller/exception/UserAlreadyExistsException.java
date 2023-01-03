package com.m2i.calendar.controller.exception;

public class UserAlreadyExistsException extends Exception {


    public UserAlreadyExistsException(String username) {
        super(username + "already exists in the database");
    }

}
