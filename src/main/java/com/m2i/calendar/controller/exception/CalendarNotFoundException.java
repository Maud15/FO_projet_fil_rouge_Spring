package com.m2i.calendar.controller.exception;

public class CalendarNotFoundException extends Exception{
    public CalendarNotFoundException(Long id){
        super("Calendar with this: "+ id + "is not found in the database");
    }
}
