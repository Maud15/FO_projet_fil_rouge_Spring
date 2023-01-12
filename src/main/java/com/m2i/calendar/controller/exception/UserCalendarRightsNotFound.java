package com.m2i.calendar.controller.exception;

public class UserCalendarRightsNotFound extends Exception {
    public UserCalendarRightsNotFound(){
        super("Error while trying to get userCalendarRights");
    }
}
