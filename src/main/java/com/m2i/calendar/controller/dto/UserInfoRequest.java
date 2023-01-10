package com.m2i.calendar.controller.dto;

import com.m2i.calendar.repository.entity.City;
import com.m2i.calendar.repository.entity.Role;
import com.m2i.calendar.repository.entity.UserCalendarRights;

import java.util.List;

public class UserInfoRequest {

    private String pseudo;
    private String email;
    private String firstname;
    private String lastname;
    private City city;
    private List<Role> roleList;
    private List<UserCalendarRights> calendarRightsList;

    public UserInfoRequest(){

    }

    public UserInfoRequest(String pseudo, String email, String firstname, String lastname, City city, List<Role> roleList, List<UserCalendarRights> calendarRightsList) {
        this.pseudo = pseudo;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.roleList = roleList;
        this.calendarRightsList = calendarRightsList;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<UserCalendarRights> getCalendarRightsList() {
        return calendarRightsList;
    }

    public void setCalendarRightsList(List<UserCalendarRights> calendarRightsList) {
        this.calendarRightsList = calendarRightsList;
    }
}
