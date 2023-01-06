package com.m2i.calendar.controller.dto;

import com.m2i.calendar.repository.entity.Calendar;
import com.m2i.calendar.repository.entity.UserCalendarRights;

import java.util.List;

public class CalendarRequest {

    private String name;
    private boolean isMainCalendar;
    private List<UserCalendarRights> calendarUserRightsList;

    public static CalendarRequest from(Calendar c){
        CalendarRequest dto = new CalendarRequest();
        dto.setMainCalendar(c.isMainCalendar());
        dto.setCalendarUserRightsList(c.getCalendarUserRightsList());
        return dto;
    }

    public CalendarRequest(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMainCalendar() {
        return isMainCalendar;
    }

    public void setMainCalendar(boolean mainCalendar) {
        isMainCalendar = mainCalendar;
    }

    public List<UserCalendarRights> getCalendarUserRightsList() {
        return calendarUserRightsList;
    }

    public void setCalendarUserRightsList(List<UserCalendarRights> calendarUserRightsList) {
        this.calendarUserRightsList = calendarUserRightsList;
    }




}
