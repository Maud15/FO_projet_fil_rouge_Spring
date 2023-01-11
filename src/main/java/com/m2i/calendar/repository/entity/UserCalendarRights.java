package com.m2i.calendar.repository.entity;

import com.m2i.calendar.repository.UserCalendarRightsId;
import jakarta.persistence.*;

@Entity(name = "user_calendar_rights")
public class UserCalendarRights {

    @EmbeddedId
    private UserCalendarRightsId userCalendarRightsId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("calendarId")
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;


    @Column(nullable = false)
    private String rights;

    public UserCalendarRights(){}

    public UserCalendarRights(UserCalendarRightsId userCalendarRightsId, User user, Calendar calendar, String rights) {
        this.userCalendarRightsId = userCalendarRightsId;
        this.rights = rights;
        this.calendar = calendar;
        this.user = user;
    }

    public UserCalendarRightsId getUserCalendarRightsId() {
        return userCalendarRightsId;
    }
    public void setUserCalendarRightsId(UserCalendarRightsId userCalendarRightsId) {
        this.userCalendarRightsId = userCalendarRightsId;
    }

    public String getRights() {
        return rights;
    }
    public void setRights(String rights) {
        this.rights = rights;
    }
}
