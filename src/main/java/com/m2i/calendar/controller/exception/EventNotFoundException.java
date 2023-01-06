package com.m2i.calendar.controller.exception;

public class EventNotFoundException extends Exception {
    public EventNotFoundException(Long id){super("Event with this: "+ id + "is not found in the database");}
}
