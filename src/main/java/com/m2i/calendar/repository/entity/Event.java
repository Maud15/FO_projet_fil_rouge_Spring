package com.m2i.calendar.repository.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column (name = "full_day", nullable = false)
    private boolean fullDay;

//    @ManyToOne //TODO Demander à Freddy
//    @JoinColumn(name="calendar_id")
//    private Calendar calendar;
    @Column (name = "calendar_id", nullable = false)
    private Long calendarId;


    public Event(){}

    public Event( String title, LocalDate startDate, LocalDate endDate, boolean fullDay) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fullDay = fullDay;

    }
    /*public Event(Long id, String title, LocalDate startDate, LocalDate endDate, boolean fullDay) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fullDay = fullDay;
    }*/

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
