package com.m2i.calendar.controller.dto;


import com.m2i.calendar.repository.entity.Calendar;

import java.time.LocalDate;
import java.util.List;

public class EventRequest {
    private String title;
    private LocalDate startDate;

    private LocalDate endDate;

    private boolean isFullDay;


    private Calendar calendar;


    public EventRequest(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isFullDay() {
        return isFullDay;
    }

    public void setFullDay(boolean fullDay) {
        isFullDay = fullDay;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }



}
