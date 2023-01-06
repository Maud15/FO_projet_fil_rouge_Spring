package com.m2i.calendar.repository.entity;

import com.m2i.calendar.repository.UserCalendarRightsId;
import jakarta.persistence.*;

@Entity
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

    public UserCalendarRights(){

    }

    public UserCalendarRights(UserCalendarRightsId userCalendarRightsId, User user, Calendar calendar, String rights) {
        this.userCalendarRightsId = userCalendarRightsId;
        this.rights = rights;
        this.calendar = calendar;
        this.user = user;
    }

    //
//    public UserCalendarRights(User user, Calendar calendar, String rights){
//        this.user = user;
//        this.calendar = calendar;
//        this.rights = rights;
//    }



//    public User getUser() {
//        return user;
//    }
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Calendar getCalendar() {
//        return calendar;
//    }
//    public void setCalendar(Calendar calendar) {
//        this.calendar = calendar;
//    }


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
