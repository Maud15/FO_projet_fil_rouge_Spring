package com.m2i.calendar.controller.dto;

public class EventRequest {
    private Long id;
    private String title;
    private String startDate;
    private String endDate;
    private boolean fullDay;
    private Long calendarId;


    public EventRequest(){}

    public EventRequest(Long id, String title, String startDate, String endDate, boolean fullDay, Long calendarId) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fullDay = fullDay;
        this.calendarId = calendarId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isFullDay() {
        return fullDay;
    }
    public void setFullDay(boolean fullDay) {
        this.fullDay = fullDay;
    }

    public Long getCalendarId() {
        return calendarId;
    }
    public void setCalendarId(Long calendarId) {
        this.calendarId = calendarId;
    }

}
