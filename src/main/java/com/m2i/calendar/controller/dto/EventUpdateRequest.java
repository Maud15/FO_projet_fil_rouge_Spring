package com.m2i.calendar.controller.dto;

public class EventUpdateRequest {
    private String title;
    private String startDate;
    private String endDate;
    private boolean fullDay;


    public EventUpdateRequest() {}

    public EventUpdateRequest(String title, String startDate, String endDate, boolean fullDay) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fullDay = fullDay;
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
}
