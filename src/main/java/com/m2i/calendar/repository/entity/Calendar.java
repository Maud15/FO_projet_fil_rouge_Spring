package com.m2i.calendar.repository.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQuery(
        name="Calendar.findByUserAndRights",
        query= """
                    select c from Calendar c
                    join fetch user_calendar_rights ucr join fetch User u
                    where c.id = ucr.userCalendarRightsId.calendarId
                    and ucr.userCalendarRightsId.userId = :userId
                    and ucr.rights = :rights
"""
)
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id")
    private Long id;


//    private String name;

    @Column(name = "main_calendar", nullable = false)
    private boolean mainCalendar;

    @OneToMany(mappedBy = "calendar")
    private List<UserCalendarRights> calendarUserRightsList;

    @OneToMany(mappedBy = "calendar")
    private List<Event> eventsList;

    public Calendar(){}
    public Calendar(Long id){
        this.id = id;
    }
    
    public Calendar(boolean mainCalendar){
        this.mainCalendar = mainCalendar;
    }
    public Calendar(Long id,  boolean mainCalendar){
        this.id = id;
        this.mainCalendar = mainCalendar;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public boolean isMainCalendar() {
        return mainCalendar;
    }
    public void setMainCalendar(boolean mainCalendar) {
        this.mainCalendar = mainCalendar;
    }

    public List<UserCalendarRights> getCalendarUserRightsList() {
        return calendarUserRightsList;
    }
    public void setCalendarUserRightsList(List<UserCalendarRights> calendarUserRightsList) {
        this.calendarUserRightsList = calendarUserRightsList;
    }

    public List<Event> getEventsList() {
        return eventsList;
    }
    public void setEventsList(List<Event> eventsList) {
        this.eventsList = eventsList;
    }
}
