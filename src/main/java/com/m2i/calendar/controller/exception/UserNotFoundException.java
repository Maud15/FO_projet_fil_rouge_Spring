package com.m2i.calendar.controller.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String username){ super(username+"doesn't exist in the database");}
}
