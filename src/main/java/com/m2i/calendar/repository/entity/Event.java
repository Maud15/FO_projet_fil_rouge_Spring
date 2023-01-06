package com.m2i.calendar.repository.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column (nullable = false)
    private boolean isFullDay;

    @Column (nullable = false)
    private Long idCalendar;

//    @ManyToOne //TODO Demander à Freddy
//    @JoinColumn(name="calendar_id")
//    private Calendar calendar;


    public Event(){

    }

    public Event( String title, LocalDate startDate, LocalDate endDate, boolean isFullDay) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isFullDay = isFullDay;

    }
    public Event(Long id, String title, LocalDate startDate, LocalDate endDate, boolean isFullDay) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isFullDay = isFullDay;
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
}
