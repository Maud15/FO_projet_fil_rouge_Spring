package com.m2i.calendar.repository.entity;

import jakarta.persistence.*;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Column(name = "end_date", nullable = false)
    private String endDate;

    @Column (name = "full_day", nullable = false)
    private boolean fullDay;

    @ManyToOne
    @JoinColumn(name="calendar_id")
    private Calendar calendar;


    public Event(){}

    public Event( String title, String startDate, String endDate, boolean fullDay, Calendar calendar) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fullDay = fullDay;
        this.calendar = calendar;
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

    public Calendar getCalendar() {
        return calendar;
    }
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
